package cng.automation.forecourt;

import cng.automation.generics.TBD;
import cng.automation.generics.TupleEventListener;

import java.util.stream.Stream;

public class DefaultForecourt implements Forecourt {

    private ForecourtSetup setup;



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

    @Override
    public Stream<CngDispenser> getDispensers() {
        return null;
    }


}