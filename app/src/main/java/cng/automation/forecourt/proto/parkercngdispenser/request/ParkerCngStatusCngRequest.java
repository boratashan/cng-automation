package cng.automation.forecourt.proto.parkercngdispenser.request;

import cng.automation.forecourt.proto.parkercngdispenser.ParkerCngRequest;

public class ParkerCngStatusCngRequest extends ParkerCngRequest {
    private int dispenserId;

    public ParkerCngStatusCngRequest(int dispenserId) {
        this.dispenserId = dispenserId;
    }
}
