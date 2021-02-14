package cng.automation.forecourt.proto.parkercngdispenser;

import cng.automation.forecourt.Transporter;
import cng.automation.forecourt.proto.parkercngdispenser.request.ParkerCngGetDataCngRequest;
import cng.automation.forecourt.proto.parkercngdispenser.request.ParkerCngStatusCngRequest;
import cng.automation.forecourt.proto.parkercngdispenser.response.ParkerCngDataCngResponse;
import cng.automation.forecourt.proto.parkercngdispenser.response.ParkerCngStatusCngResponse;
import com.intelligt.modbus.jlibmodbus.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.exception.ModbusIOException;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;
import org.apache.commons.lang3.NotImplementedException;

public class ParkerCngModbusRtuTransporter implements Transporter<ParkerCngRequest, ParkerCngResponse> {

    private final String host;
    private final int port;


    private boolean connected = false;

    private ModbusMaster modbusMaster;

    public ParkerCngModbusRtuTransporter(String host, int port) {
        this.host = host;
        this.port = port;
        TcpParameters parameters = new TcpParameters(host, port, true);
        //modbusMaster = ModbusMasterFactory.createModbusMasterTCP(parameters);

    }

    public ParkerCngResponse send(ParkerCngRequest request) {
        switch (request.getType()) {
            case GETDATA_REQUEST:
                return this.sendDataRequest((ParkerCngGetDataCngRequest) request);
            case GETSTATUS_REQUEST:
                return this.sendStatusRequest((ParkerCngStatusCngRequest) request);
        }
        throw new NotImplementedException();
    }

    @Override
    public void connect() throws ModbusIOException {
      /*  if (modbusMaster.isConnected()) {
                modbusMaster.disconnect();
        }
        modbusMaster.connect();*/
        this.connected = true;
    }

    @Override
    public void disconnect() throws ModbusIOException {
   /*     if (modbusMaster.isConnected()) {
            modbusMaster.disconnect();
        }*/
        this.connected = false;
    }

    @Override
    public boolean isConnected() {
        //return modbusMaster.isConnected();
        return this.connected;
    }

    private ParkerCngDataCngResponse sendDataRequest(ParkerCngGetDataCngRequest request) {
        //int[] map = modbusMaster.readHoldingRegisters(request.getDispenserId(), 0x1236, 40);

        ParkerCngDataCngResponse response = new ParkerCngDataCngResponse();
        response.dispenserId = request.getDispenserId();
        return response;
    }

    private ParkerCngStatusCngResponse sendStatusRequest(ParkerCngStatusCngRequest request) {
//            int[] map = modbusMaster.readHoldingRegisters(request.getDispenserId(), 0x1234, 2);
        //return  ParkerCngStatusCngResponse.valueOf(request.getDispenserId(), map);
        return new ParkerCngStatusCngResponse();
    }


}
