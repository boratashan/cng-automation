package cng.automation.forecourt.proto.parkercngdispenser.response;

import cng.automation.forecourt.proto.parkercngdispenser.ParkerCngResponse;
import cng.automation.forecourt.proto.parkercngdispenser.ResponseType;

import java.util.StringJoiner;

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
    public ResponseType getType() {
        return ResponseType.DATA;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ParkerCngDataCngResponse.class.getSimpleName() + "[", "]")
                .add("dispenserId=" + dispenserId)
                .add("lastFillQuantityInKgArm01=" + lastFillQuantityInKgArm01)
                .add("lastFillAmountInRsArm01=" + lastFillAmountInRsArm01)
                .add("flowMeterMassTotalArm01=" + flowMeterMassTotalArm01)
                .add("dispenserMeteredTotalArm01=" + dispenserMeteredTotalArm01)
                .add("gasRateArm01=" + gasRateArm01)
                .add("currentFillQtyArm01=" + currentFillQtyArm01)
                .add("currentFillPrcArm01=" + currentFillPrcArm01)
                .add("pressure01Arm01=" + pressure01Arm01)
                .add("pressure02Arm01=" + pressure02Arm01)
                .add("gasFlowRateArm01=" + gasFlowRateArm01)
                .add("lastFillQuantityInKgArm02=" + lastFillQuantityInKgArm02)
                .add("lastFillAmountInRsArm02=" + lastFillAmountInRsArm02)
                .add("flowMeterMassTotalArm02=" + flowMeterMassTotalArm02)
                .add("dispenserMeteredTotalArm02=" + dispenserMeteredTotalArm02)
                .add("gasRateArm02=" + gasRateArm02)
                .add("currentFillQtyArm02=" + currentFillQtyArm02)
                .add("currentFillPrcArm02=" + currentFillPrcArm02)
                .add("pressure01Arm02=" + pressure01Arm02)
                .add("pressure02Arm02=" + pressure02Arm02)
                .add("gasFlowRateArm02=" + gasFlowRateArm02)
                .toString();
    }
}
