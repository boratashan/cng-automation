package cng.automation.forecourt;

import cng.automation.generics.TBD;
import cng.automation.generics.TupleEventListener;

public interface DispenserActions {
    public void onDispenserStatus(TupleEventListener<Dispenser, TBD> event);

    public void onTransactionCompleted(TupleEventListener<Dispenser, TBD> event);

    public void onError(TupleEventListener<Dispenser, TBD> event);
}
