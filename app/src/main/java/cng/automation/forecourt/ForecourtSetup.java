package cng.automation.forecourt;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class ForecourtSetup  {
    private final Set<DeviceCluster> clusters;

    public ForecourtSetup() {
        clusters = new HashSet<>();
    }


    public void addDeviceConnector(DeviceCluster connector) {
        if (clusters.contains(connector))
            throw new IllegalArgumentException("Device connected with the same ID is already in the list!");
        clusters.add(connector);
    }

    public void addDispenser(DeviceCluster cluster, CngDispenser dispenser) {
        cluster.addDevice(dispenser);
    }

    public Stream<DeviceCluster> getClusters() {
        return this.clusters.stream();
    }


}
