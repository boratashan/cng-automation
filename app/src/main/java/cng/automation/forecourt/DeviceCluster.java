package cng.automation.forecourt;

import cng.automation.generics.TupleEventListener;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class DeviceCluster<T extends DevicePollable> extends GenericDevice {


    private static final long CLUSTER_DELAY_DEFAULT_LOOP = 200 * 5;
    private static final long CLUSTER_DELAY_BETWEEN_REQUEST_IN_MS = 100 * 10;
    private final ArrayList<T> devices;
    private final Transporter<?, ?> transporter;
    private final AtomicBoolean startFlag;
    private Thread clusterThread;
    private TupleEventListener<DeviceCluster<?>, PackageResponse> onPacketProcessErrorEvent;
    private TupleEventListener<DeviceCluster<?>, PackageRequest> onSendPacketEvent;
    private TupleEventListener<DeviceCluster<?>, PackageResponse> onReceivePacketEvent;

    public DeviceCluster(int id, Transporter<?, ?> transporter) {
        super(id);
        this.startFlag = new AtomicBoolean(false);
        this.transporter = transporter;
        devices = new ArrayList<>();
    }


    public void addDevice(T device) {
        if (devices.contains(device))
            throw new IllegalArgumentException("Device connected with the same ID is already in the list!");
        devices.add(device);
    }

    public void start() {
        startFlag.set(true);
        clusterThread = new Thread(new ClusterTask<T>(this));
        clusterThread.start();
    }

    public void stop() {
        startFlag.set(false);
        clusterThread.interrupt();
    }

    public void onPackageProcessError(TupleEventListener<DeviceCluster<?>, PackageResponse> event) {
        this.onPacketProcessErrorEvent = event;
    }

    public void onPackageSentEvent(TupleEventListener<DeviceCluster<?>, PackageRequest> event) {
        this.onSendPacketEvent = event;
    }

    public void onPackageReceived(TupleEventListener<DeviceCluster<?>, PackageResponse> event) {
        this.onReceivePacketEvent = event;
    }

    private class ClusterTask<S extends DevicePollable> implements Runnable {

        private final DeviceCluster<S> parent;
        private final Transporter transporter;

        public ClusterTask(DeviceCluster parent) {
            this.parent = parent;
            transporter = parent.transporter;
        }

        @Override
        public void run() {
            try {
                while (parent.startFlag.get()) {
                    Thread.sleep(CLUSTER_DELAY_DEFAULT_LOOP);
                    if (!transporter.isConnected()) {
                        transporter.connect();
                    }
                    for (S device : this.parent.devices) {
                        PackageRequest cmdToExec = device.getCommandToExec();
                        if (Objects.nonNull(this.parent.onSendPacketEvent)) {
                            this.parent.onSendPacketEvent.onEvent(this.parent, cmdToExec);
                        }
                        PackageResponse response = transporter.send(cmdToExec);
                        if (Objects.nonNull(this.parent.onReceivePacketEvent)) {
                            this.parent.onReceivePacketEvent.onEvent(this.parent, response);
                        }
                        boolean isPkgProcessed = device.process(response);
                        if (!isPkgProcessed) {
                            for (S d : this.parent.devices) {
                                if (device.equals(d))
                                    continue;
                                isPkgProcessed = d.process(response);
                                if (isPkgProcessed)
                                    break;
                            }
                        }
                        if (!isPkgProcessed) {
                            if (Objects.nonNull(this.parent.onPacketProcessErrorEvent)) {
                                this.parent.onPacketProcessErrorEvent.onEvent(this.parent, response);
                            }
                        }
                    }
                }
            } catch (InterruptedException | ModbusIOException e) {
                /**/
            } finally {
                if (transporter.isConnected()) {
                    try {
                        transporter.disconnect();
                    } catch (ModbusIOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
