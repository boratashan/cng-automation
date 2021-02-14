package cng.automation.forecourt.proto.parkercngdispenser;

import cng.automation.forecourt.CngDispenser;
import cng.automation.forecourt.GenericDevice;
import cng.automation.forecourt.proto.parkercngdispenser.request.ParkerCngGetDataCngRequest;
import cng.automation.forecourt.proto.parkercngdispenser.request.ParkerCngPriceUpdateRequest;
import cng.automation.forecourt.proto.parkercngdispenser.request.ParkerCngStatusCngRequest;
import cng.automation.forecourt.proto.parkercngdispenser.response.ParkerCngDataCngResponse;
import cng.automation.forecourt.proto.parkercngdispenser.response.ParkerCngStatusCngResponse;
import cng.automation.forecourt.proto.parkercngdispenser.response.ParkerCngTransactionCreatedResponse;
import cng.automation.generics.TripleEventListener;
import cng.automation.generics.TupleEventListener;

import java.util.Objects;
import java.util.StringJoiner;


/*
How to control dispenser
Ask status,
if filling status is off,
    Update prices;
if filling status is on
    Report on going transaction
if filling status goes off,
    Read data and report

 */
public class ParkerCngDispenser extends GenericDevice implements CngDispenser<ParkerCngRequest, ParkerCngResponse>, CngDispenserActions {

    //Make it Atomic later in case concurrency involves.
    private boolean isRequestStatus = false;
    private boolean isPriceUpdate = false;
    //No need to recreate the same status object each time it is needed.
    //Create one default and keep it as a class variable
    private final ParkerCngStatusCngRequest cachedStatusRequest;
    private double priceToUpdate = 0;

    private TupleEventListener<ParkerCngDispenser, ParkerCngStatusCngResponse> onStatusEvent;
    private TupleEventListener<ParkerCngDispenser, ParkerCngDataCngResponse> onDataReportEvent;
    private TupleEventListener<ParkerCngDispenser, Exception> onErrorEvent;
    private final ParkerCngGetDataCngRequest cachedDataRequest;
    private boolean isRequestData = false;
    private TripleEventListener<ParkerCngDispenser, Boolean, Boolean> onFillingStatusChanged;
    private TupleEventListener<ParkerCngDispenser, ParkerCngTransactionCreatedResponse> onTransactionCreated;


    /* State management */
    private boolean fillingStatus_arm01 = false;
    private boolean fillingStatus_arm02 = false;
    private boolean createTxnForArm01 = false;
    private boolean createTxnForArm02 = false;

    public ParkerCngDispenser(int id) {
        super(id);
        this.isRequestStatus = true;
        this.isPriceUpdate = false;
        this.isRequestData = false;
        this.cachedStatusRequest = new ParkerCngStatusCngRequest(id);
        this.cachedDataRequest = new ParkerCngGetDataCngRequest(id);
    }


    @Override
    public ParkerCngRequest getCommandToExec() {
        if (isPriceUpdate)
            return new ParkerCngPriceUpdateRequest(this.getId(), this.priceToUpdate);
        if (isRequestStatus)
            return cachedStatusRequest;
        if (isRequestData)
            return cachedDataRequest;
        //Default request is Status request
        return cachedStatusRequest;
    }

    protected void processData(ParkerCngDataCngResponse response) {
        if (Objects.nonNull(this.onDataReportEvent)) {
            this.onDataReportEvent.onEvent(this, response);
        }
        if (this.createTxnForArm01 || this.createTxnForArm02) {
            if (Objects.nonNull(this.onTransactionCreated)) {
                if (this.createTxnForArm01) {
                    this.onTransactionCreated.onEvent(this, ParkerCngTransactionCreatedResponse.buildFrom(true, response));
                }
                if (this.createTxnForArm02) {
                    this.onTransactionCreated.onEvent(this, ParkerCngTransactionCreatedResponse.buildFrom(false, response));
                }
            }
            this.createTxnForArm01 = false;
            this.createTxnForArm02 = false;
        }
        this.isRequestData = false;
    }

    @Override
    public boolean process(ParkerCngResponse response) {
        boolean isProcessed = false;
        switch (response.getType()) {
            case STATUS:
                isProcessed = ((ParkerCngStatusCngResponse) response).dispenserId == this.getId();
                if (!isProcessed) break;
                ParkerCngStatusCngResponse r = (ParkerCngStatusCngResponse) response;
                if (Objects.nonNull(this.onStatusEvent)) {
                    this.onStatusEvent.onEvent(this, (ParkerCngStatusCngResponse) response);
                }
                this.isRequestStatus = false;
                break;
            case DATA:
                isProcessed = ((ParkerCngDataCngResponse) response).dispenserId == this.getId();
                if (!isProcessed) break;
                this.processData((ParkerCngDataCngResponse) response);
                break;
            default:
                IllegalStateException e = new IllegalStateException(String.format("Unknown response -> respose : [%s]", response.toString()));
                if (Objects.nonNull(this.onErrorEvent)) {
                    this.onErrorEvent.onEvent(this, e);
                }
                // throw e;
                // Todo Overriding Exception here is reasonable however programmer must be aware of it.
                break;
        }
        return isProcessed;
    }


    @Override
    public void requestStatus() {
        this.isRequestStatus = true;
    }

    @Override
    public void updatePrice(Double price) {
        priceToUpdate = price;
        this.isPriceUpdate = true;
    }

    @Override
    public void onDispenserStatus(TupleEventListener<ParkerCngDispenser, ParkerCngStatusCngResponse> event) {
        this.onStatusEvent = event;
    }

    @Override
    public void onDispenserDataReport(TupleEventListener<ParkerCngDispenser, ParkerCngDataCngResponse> event) {
        this.onDataReportEvent = event;
    }

    @Override
    public void onError(TupleEventListener<ParkerCngDispenser, Exception> event) {
        this.onErrorEvent = event;
    }

    public void onFillingStatusChanged(TripleEventListener<ParkerCngDispenser, Boolean, Boolean> event) {
        this.onFillingStatusChanged = event;
    }

    @Override
    public void onTransactionCreated(TupleEventListener<ParkerCngDispenser, ParkerCngTransactionCreatedResponse> event) {
        this.onTransactionCreated = event;
    }


    private void updateFillingStatusFlags(ParkerCngStatusCngResponse response) {
        boolean changed = false;
        changed = changed | (fillingStatus_arm01 == response.fillingStatusArm01);
        changed = changed | (fillingStatus_arm02 == response.fillingStatusArm02);

        this.createTxnForArm01 = (!fillingStatus_arm01) & response.fillingStatusArm01;
        this.createTxnForArm02 = (!fillingStatus_arm02) & response.fillingStatusArm02;

        if (changed) {
            fillingStatus_arm01 = response.fillingStatusArm01;
            fillingStatus_arm02 = response.fillingStatusArm02;
            if (Objects.nonNull(this.onFillingStatusChanged)) {
                this.onFillingStatusChanged.onEvent(this, fillingStatus_arm01, fillingStatus_arm02);
            }
            this.isRequestData = true;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ParkerCngDispenser.class.getSimpleName() + "[", "]")
                .add("id =" + this.getId())
                .add("address =" + this.getAddress())
                .toString();
    }
}
