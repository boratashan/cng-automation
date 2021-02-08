package cng.automation.forecourt.proto.parkercngdispenser.request;

import cng.automation.forecourt.proto.parkercngdispenser.ParkerCngRequest;

public class ParkerCngGetDataCngRequest extends ParkerCngRequest {
    private int dispenserId;

    public ParkerCngGetDataCngRequest(int dispenserId) {
        this.dispenserId = dispenserId;
    }
}
