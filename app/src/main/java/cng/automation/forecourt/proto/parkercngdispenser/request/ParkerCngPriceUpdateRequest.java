package cng.automation.forecourt.proto.parkercngdispenser.request;

import cng.automation.forecourt.proto.parkercngdispenser.ParkerCngRequest;
import cng.automation.forecourt.proto.parkercngdispenser.RequestType;

public class ParkerCngPriceUpdateRequest extends ParkerCngRequest {
    private final int dispenserId;

    public ParkerCngPriceUpdateRequest(int dispenserId, double Price) {
        this.dispenserId = dispenserId;
    }

    @Override
    public RequestType getType() {
        return RequestType.UPDATE_PRICES;
    }
}
