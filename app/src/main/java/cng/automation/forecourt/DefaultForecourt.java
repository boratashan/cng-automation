package cng.automation.forecourt;

import java.util.Objects;


public class DefaultForecourt implements Forecourt {

    private ForecourtSetup setup;


    @Override
    public ForecourtSetup setup() {
        return this.setup;
    }

    @Override
    public ForecourtSetup setSetup(ForecourtSetup setup) {
        this.setup = setup;
        return this.setup;
    }

    @Override
    public void start() {
        Objects.requireNonNull(this.setup, "Forecourt can not start without setup!");
        this.setup.getClusters().forEach(deviceCluster -> deviceCluster.start());
    }

    @Override
    public void stop() {
        this.setup.getClusters().forEach(deviceCluster -> deviceCluster.stop());
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



}