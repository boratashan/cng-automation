package cng.automation.forecourt;

import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;

public interface Transporter<T extends PackageRequest, R extends PackageResponse> {
    R send(T t);

    void connect() throws ModbusIOException;

    void disconnect() throws ModbusIOException;

    boolean isConnected();
}
