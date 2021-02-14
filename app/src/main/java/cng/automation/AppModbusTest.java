package cng.automation;

import com.intelligt.modbus.jlibmodbus.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusNumberException;
import com.intelligt.modbus.jlibmodbus.exception.ModbusProtocolException;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;

import java.util.Arrays;

public class AppModbusTest {
    public static void main(String[] args) throws ModbusIOException, ModbusNumberException, ModbusProtocolException {
        TcpParameters parameters = new TcpParameters("127.0.0.1", 502, true);
        ModbusMaster masterTCP = ModbusMasterFactory.createModbusMasterTCP(parameters);

        masterTCP.connect();
        boolean[] coils = masterTCP.readCoils(1, 0x0000, 16);
        int[] registers = masterTCP.readHoldingRegisters(1, 0x1234, 2);
        System.out.println(Arrays.toString(registers));
        masterTCP.writeMultipleRegisters(1, 0x1234, new int[]{193});
        registers = masterTCP.readHoldingRegisters(1, 0x1236, 40);
        System.out.println(Arrays.toString(registers));


    }
}
