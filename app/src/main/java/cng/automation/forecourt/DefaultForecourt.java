package cng.automation.forecourt;

import cng.automation.forecourt.Forecourt;
import cng.automation.generics.SingleEventListener;
import cng.automation.generics.TBD;
import cng.automation.generics.TupleEventListener;

public class DefaultForecourt implements Forecourt {

    private ForecourtSetup setup;
    private TupleEventListener<Dispenser, TBD> onDispenserStatus;
    private TupleEventListener<Dispenser, TBD> onTransactionCompleted;
    private TupleEventListener<Dispenser, TBD> onError;


    public ForecourtSetup setSetup(ForecourtSetup setup) {
        this.setup = setup;
        return this.setup;
    }

    @Override
    public ForecourtSetup setup() {
        return setup;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void startDispenser(int dispenserNo) {

    }

    @Override
    public void stopDispenser(int dispenserNo) {

    }

    @Override
    public void setPrice(int dispenserNo, double price) {

    }

    public void onDispenserStatus(TupleEventListener<Dispenser, TBD> event) {
        this.onDispenserStatus = event;
    }

    public void onTransactionCompleted(TupleEventListener<Dispenser, TBD> event) {
        this.onTransactionCompleted = event;
    }

    public void onError(TupleEventListener<Dispenser, TBD> event) {
        this.onError = event;
    }

}