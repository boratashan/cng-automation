package cng.automation.forecourt.proto.parkercngdispenser.request;

import cng.automation.forecourt.proto.parkercngdispenser.ParkerCngRequest;
import cng.automation.forecourt.proto.parkercngdispenser.RequestType;

public class ParkerCngGetDataCngRequest extends ParkerCngRequest {


    private final int dispenserId;

    public ParkerCngGetDataCngRequest(int dispenserId) {
        this.dispenserId = dispenserId;
    }

    public int getDispenserId() {
        return dispenserId;
    }


    @Override
    public RequestType getType() {
        return RequestType.GETDATA_REQUEST;
    }
}
