package cng.automation.forecourt;

import cng.automation.generics.TBD;
import cng.automation.generics.TupleEventListener;

public class Dispenser extends GenericDevice implements DispenserActions{
    private TupleEventListener<Dispenser, TBD> onDispenserStatus;
    private TupleEventListener<Dispenser, TBD> onTransactionCompleted;
    private TupleEventListener<Dispenser, TBD> onError;

    public Dispenser(int id) {
        super(id);
    }

    public void process() {
        //Todo Implement dispenser logic here
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
