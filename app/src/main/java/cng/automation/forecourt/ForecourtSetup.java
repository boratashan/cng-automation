package cng.automation.forecourt;

import java.util.*;

public class ForecourtSetup  {
    private Set<DeviceCluster> clusters;

    public ForecourtSetup() {
        clusters = new HashSet<>();
    }


    public void addDeviceConnector(DeviceCluster connector) {
        if (clusters.contains(connector))
            throw new IllegalArgumentException("Device connected with the same ID is already in the list!");
        clusters.add(connector);
    }

    public void addDispenser(DeviceCluster cluster, Dispenser dispenser) {
        cluster.addDispenser(dispenser);
    }


}
