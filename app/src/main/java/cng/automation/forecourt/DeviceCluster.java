package cng.automation.forecourt;

import cng.automation.generics.TBD;
import cng.automation.generics.TupleEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class DeviceCluster extends GenericDevice implements DispenserActions{


    private static final long CLUSTER_DELAY_DEFAULT_LOOP = 200;
    private static final long CLUSTER_DELAY_BETWEEN_REQUEST_IN_MS = 100;

    private ArrayList<Dispenser> dispensers;

    private String host;
    private int port;


    private final Thread clusterThread;
    private boolean isStarted = false;
    private TupleEventListener<Dispenser, TBD> onDispenserStatus;
    private TupleEventListener<Dispenser, TBD> onTransactionCompleted;
    private TupleEventListener<Dispenser, TBD> onError;


    public DeviceCluster(int id) {
        super(id);
        dispensers = new ArrayList<>();
        clusterThread = new Thread(new ClusterTask(this));
        clusterThread.start();

    }


    public void addDispenser(Dispenser dispenser) {
        if (dispensers.contains(dispenser))
            throw new IllegalArgumentException("Device connected with the same ID is already in the list!");
        dispensers.add(dispenser);
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



    private class ClusterTask implements Runnable {

        private final DeviceCluster parent;

        public ClusterTask(DeviceCluster parent) {
            this.parent = parent;
        }

        @Override
        public void run() {
            try {
                int terminalToExec = -1;
                while (true) {
                    Thread.sleep(CLUSTER_DELAY_DEFAULT_LOOP);
                    if (!isStarted) continue;
                try {
                        //TODO : Open connection to channel
                        for (Dispenser dispenser : this.parent.dispensers) {
                            dispenser.process();
                            Thread.sleep(CLUSTER_DELAY_BETWEEN_REQUEST_IN_MS);
                        }
                        //Todo Implement devices communication and dispatching here
                    } catch (Exception e) {
                        //Todo Close Connection in case of error
                    }
                }
            } catch (InterruptedException e) {
                //Todo cleanup
            }

        }
    }




    public static final class Builder {
        private String host;
        private int port;
        private int id;

        private Builder() {
        }

        public static Builder aDeviceConnector() {
            return new Builder();
        }

        public Builder withHost(String host) {
            this.host = host;
            return this;
        }

        public Builder withPort(int port) {
            this.port = port;
            return this;
        }

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public DeviceCluster build() {
            DeviceCluster deviceCluster = new DeviceCluster(id);
            deviceCluster.host = this.host;
            deviceCluster.port = this.port;
            return deviceCluster;
        }
    }

}
