package cng.automation.forecourt;

import cng.automation.generics.SingleEventListener;
import cng.automation.generics.TBD;
import cng.automation.generics.TupleEventListener;

import java.util.stream.Stream;


public interface Forecourt  {
    ForecourtSetup setup();
    ForecourtSetup setSetup(ForecourtSetup setup);
    /*Important
            Station is to be managed via asynchronous way since all the dispensers are connected
            through serial network and can block all the processes easily in case connection or
            device errors.
            Thus, site manager will send message to station and consider process is accomplished or failed
            whereupon response event is captured.
     */
    /* Methods to control station*/
    void start();
    void stop();
    void startDispenser(int dispenserNo);
    void stopDispenser(int dispenserNo);
    void setPrice(int dispenserNo, double price);

    Stream<CngDispenser> getDispensers();





}
