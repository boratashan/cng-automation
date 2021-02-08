package cng.automation.forecourt;

import cng.automation.generics.TBD;
import cng.automation.generics.TupleEventListener;

import java.util.ArrayList;

public class DeviceCluster<T extends DevicePollable> extends GenericDevice {


    private static final long CLUSTER_DELAY_DEFAULT_LOOP = 200;
    private static final long CLUSTER_DELAY_BETWEEN_REQUEST_IN_MS = 100;

    private ArrayList<T> devices;

    private String host;
    private int port;


    private final Thread clusterThread;
    private boolean isStarted = false;

    public DeviceCluster(int id) {
        super(id);
        devices = new ArrayList<>();
        clusterThread = new Thread(new ClusterTask<T>(this));
        clusterThread.start();

    }


    public void addDevice(T device) {
        if (devices.contains(device))
            throw new IllegalArgumentException("Device connected with the same ID is already in the list!");
        devices.add(device);
    }


    private class ClusterTask<T extends DevicePollable> implements Runnable {

        private final DeviceCluster<T> parent;

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
                        for (T device : this.parent.devices) {
                            PackageRequest cmdToExec = device.getCommandToExec();


                            //Send command by Transporter
                            //Read packet from Transporter
                            //Decode packet and convert to packet
                            //send dispensers to process
                            boolean isPkgProcessed = false;
                            PackageResponse response = null;
                            isPkgProcessed = device.process(response);
                            if (!isPkgProcessed) {
                                for (T d : this.parent.devices) {
                                    isPkgProcessed = d.process(response);
                                    if (isPkgProcessed)
                                        break;
                                }
                            }
                            if (!isPkgProcessed) {
                                //Todo Packet is not processed, log it and create an alarm to get insight about
                            }
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
