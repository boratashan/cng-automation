package cng.automation.forecourt.proto.parkercngdispenser.response;

import cng.automation.forecourt.proto.parkercngdispenser.ParkerCngResponse;
import cng.automation.forecourt.proto.parkercngdispenser.ParkerCngResponseType;

public class ParkerCngDataCngResponse extends ParkerCngResponse {
    //Todo refactor below section and implement getter/setter s.
    public int dispenserId;

    public float lastFillQuantityInKgArm01;
    public float lastFillAmountInRsArm01;
    public float flowMeterMassTotalArm01;
    public float dispenserMeteredTotalArm01;
    public float gasRateArm01;
    public float currentFillQtyArm01;
    public float currentFillPrcArm01;
    public float pressure01Arm01;
    public float pressure02Arm01;
    public float gasFlowRateArm01;

    public float lastFillQuantityInKgArm02;
    public float lastFillAmountInRsArm02;
    public float flowMeterMassTotalArm02;
    public float dispenserMeteredTotalArm02;
    public float gasRateArm02;
    public float currentFillQtyArm02;
    public float currentFillPrcArm02;
    public float pressure01Arm02;
    public float pressure02Arm02;
    public float gasFlowRateArm02;



    @Override
    public ParkerCngResponseType getType() {
        return ParkerCngResponseType.DATA;
    }
}
