package cng.automation.forecourt.proto.parkercngdispenser.request;

import cng.automation.forecourt.proto.parkercngdispenser.ParkerCngRequest;
import cng.automation.forecourt.proto.parkercngdispenser.RequestType;

public class ParkerCngStatusCngRequest extends ParkerCngRequest {


    private final int dispenserId;

    public ParkerCngStatusCngRequest(int dispenserId) {
        this.dispenserId = dispenserId;
    }

    public int getDispenserId() {
        return dispenserId;
    }

    @Override
    public RequestType getType() {
        return RequestType.GETSTATUS_REQUEST;
    }
}
