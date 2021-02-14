package cng.automation.forecourt.proto.parkercngdispenser.response;

import cng.automation.forecourt.proto.parkercngdispenser.ParkerCngResponse;
import cng.automation.forecourt.proto.parkercngdispenser.ResponseType;

public class ParkerCngTransactionCreatedResponse extends ParkerCngResponse {

    public boolean arm01;
    public boolean arm02;
    public float quantityInKg;
    public float amountInKg;
    public float dispenserMeteredTotal;

    public static ParkerCngTransactionCreatedResponse buildFrom(boolean isArm01, ParkerCngDataCngResponse source) {
        ParkerCngTransactionCreatedResponse response = new ParkerCngTransactionCreatedResponse();
        response.arm01 = false;
        response.arm02 = false;

        if (isArm01) {
            response.arm01 = true;
            response.quantityInKg = source.lastFillQuantityInKgArm01;
            response.amountInKg = source.lastFillAmountInRsArm01;
            response.dispenserMeteredTotal = source.dispenserMeteredTotalArm01;
        } else {
            response.arm02 = true;
            response.quantityInKg = source.lastFillQuantityInKgArm02;
            response.amountInKg = source.lastFillAmountInRsArm02;
            response.dispenserMeteredTotal = source.dispenserMeteredTotalArm02;
        }
        return response;
    }


    @Override
    public ResponseType getType() {
        return ResponseType.TRANSACTION;
    }
}
