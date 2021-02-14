package cng.automation.forecourt.proto.parkercngdispenser;

import cng.automation.forecourt.proto.parkercngdispenser.response.ParkerCngDataCngResponse;
import cng.automation.forecourt.proto.parkercngdispenser.response.ParkerCngStatusCngResponse;
import cng.automation.forecourt.proto.parkercngdispenser.response.ParkerCngTransactionCreatedResponse;
import cng.automation.generics.TripleEventListener;
import cng.automation.generics.TupleEventListener;

public interface CngDispenserActions {
    void onDispenserStatus(TupleEventListener<ParkerCngDispenser, ParkerCngStatusCngResponse> event);

    void onDispenserDataReport(TupleEventListener<ParkerCngDispenser, ParkerCngDataCngResponse> event);

    void onError(TupleEventListener<ParkerCngDispenser, Exception> event);

    void onFillingStatusChanged(TripleEventListener<ParkerCngDispenser, Boolean, Boolean> event);

    void onTransactionCreated(TupleEventListener<ParkerCngDispenser, ParkerCngTransactionCreatedResponse> event);
}
