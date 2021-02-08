package cng.automation.forecourt.proto.parkercngdispenser.response;

import cng.automation.forecourt.proto.parkercngdispenser.ParkerCngResponse;
import cng.automation.forecourt.proto.parkercngdispenser.ParkerCngResponseType;

public class ParkerCngStatusCngResponse
        extends ParkerCngResponse {
    //Todo refactor below section and implement getter/setter s.
    public int dispenserId;
    public boolean fillingStatusArm01;
    public boolean fillingStatusArm02;
    public boolean lowBankCommandArm01;
    public boolean lowBankCommandArm02;
    public boolean mediumBankCommandArm01;
    public boolean mediumBankCommandArm02;
    public boolean highBankCommandArm01;
    public boolean highBankCommandArm02;
    public boolean pt01Arm01;
    public boolean pt02Arm01;
    public boolean pt01Arm02;
    public boolean pt02Arm02;
    public boolean sensorPowerArm01;
    public boolean sensorPowerArm02;

    @Override
    public ParkerCngResponseType getType() {
        return ParkerCngResponseType.STATUS;
    }
}
