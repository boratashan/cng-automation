package cng.automation.forecourt.proto.parkercngdispenser;

import cng.automation.forecourt.CngDispenser;
import cng.automation.forecourt.DevicePollable;
import cng.automation.forecourt.GenericDevice;
import cng.automation.forecourt.proto.parkercngdispenser.request.ParkerCngStatusCngRequest;
import cng.automation.forecourt.proto.parkercngdispenser.response.ParkerCngDataCngResponse;
import cng.automation.forecourt.proto.parkercngdispenser.response.ParkerCngStatusCngResponse;
import cng.automation.generics.TupleEventListener;

import java.util.Objects;

public class ParkerCngDispenser extends GenericDevice implements  CngDispenser<ParkerCngRequest, ParkerCngResponse>,  CngDispenserActions{

    //Make it Atomic later in case concurrency involves.
    private boolean isRequestStatus = false;
    private boolean isPriceUpdate = false;
    private double priceToUpdate = 0;

    private TupleEventListener<ParkerCngDispenser, ParkerCngStatusCngResponse> onStatusEvent;
    private TupleEventListener<ParkerCngDispenser, ParkerCngDataCngResponse> onDataReportEvent;
    private TupleEventListener<ParkerCngDispenser, Exception> onErrorEvent;

    public ParkerCngDispenser(int id) {
        super(id);
        this.isRequestStatus = true;
        this.isPriceUpdate = false;
    }


    @Override
    public ParkerCngRequest getCommandToExec() {
        return new ParkerCngStatusCngRequest(this.getId());
    }

    protected void processData(ParkerCngDataCngResponse response) {
        if (Objects.nonNull(this.onDataReportEvent)) {
            this.onDataReportEvent.onEvent(this, response);
        }
    }

    @Override
    public boolean process(ParkerCngResponse response) {
        boolean isProcessed = false;
        switch (response.getType()) {
            case STATUS:
                isProcessed =  ((ParkerCngStatusCngResponse) response).dispenserId == this.getId();
                if (Objects.nonNull(this.onStatusEvent)) {
                    this.onStatusEvent.onEvent(this, (ParkerCngStatusCngResponse) response);
                }
                this.isRequestStatus = false;
                break;
            case DATA:
                isProcessed =  ((ParkerCngDataCngResponse) response).dispenserId == this.getId();
                this.processData((ParkerCngDataCngResponse) response);
                break;
            default:
                IllegalStateException e =  new IllegalStateException(String.format("Unknown response -> respose : [%s]", response.toString()));
                if (Objects.nonNull(this.onErrorEvent)) {
                    this.onErrorEvent.onEvent(this, e);
                }
                throw e;
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
}
