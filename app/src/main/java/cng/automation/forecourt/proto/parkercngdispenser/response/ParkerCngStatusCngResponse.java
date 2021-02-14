package cng.automation.forecourt.proto.parkercngdispenser.response;

import cng.automation.forecourt.proto.parkercngdispenser.ParkerCngResponse;
import cng.automation.forecourt.proto.parkercngdispenser.ResponseType;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

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
    private static final int FILLING_STATUS_ONOFF = 0x1;
    private static final int LOW_BANK_COMMAND_OPENCLOSE = 0x2;
    public boolean pt01Arm01;
    public boolean pt02Arm01;
    public boolean pt01Arm02;
    public boolean pt02Arm02;
    public boolean sensorPowerArm01;
    public boolean sensorPowerArm02;

    /*
    Registers Functional Map

    Dispenser Status
    ARM A:
    Bit 0 Filling Status ON/OFF
    Bit 1 Low Bank Command OPEN/CLOSE
    Bit 2 Medium Bank Command OPEN/CLOSE
    Bit 3 High Bank Command OPEN/CLOSE
    Bit 4 Output Valve Command OPEN/CLOSE
    Bit 5 PT1 OK/FAIL
    Bit 6 PT2 OK/FAIL
    Bit 7 Sensor Power OK/FAIL
    AR ARM B:
    Bit 8 Filling Status ON/OFF
    Bit 9 Low Bank Command OPEN/CLOSE
    Bit 10 Medium Bank Command OPEN/CLOSE
    Bit 11 High Bank Command OPEN/CLOSE
    Bit 12 Output Valve Command OPEN/CLOSE
    Bit 13 PT1 OK/FAIL
    Bit 14 PT2 OK/FAIL
    Bit 15 Sensor Power OK/FAIL
     */
    private static final int MEDIUM_BANK_COMMAND_OPENCLOSE = 0x4;
    private static final int HIGH_BANK_COMMAND_OPENCLOSE = 0x8;
    private static final int OUTPUT_BANK_COMMAND_OPENCLOSE = 0x16;
    private static final int PT1_OKFAIL = 0x32;
    private static final int PT2_OKFAIL = 0x64;
    private static final int SENSOR_POWER_OKFAIL = 0x128;
    public boolean outputValveCommandArm01;
    public boolean outputValveCommandArm02;

    public static ParkerCngStatusCngResponse valueOf(int dispenserId, int[] inputRegisters) {
        Objects.requireNonNull(inputRegisters);
        if (inputRegisters.length != 2) {
            throw new IllegalArgumentException(String.format("Input registers map size should equal to 2 but it is %d, Registers is %s", inputRegisters.length, Arrays.toString(inputRegisters)));
        }
        ParkerCngStatusCngResponse response = new ParkerCngStatusCngResponse();
        response.dispenserId = dispenserId;
        response.fillingStatusArm01 = ((inputRegisters[0] & FILLING_STATUS_ONOFF) == FILLING_STATUS_ONOFF);
        response.fillingStatusArm02 = ((inputRegisters[1] & FILLING_STATUS_ONOFF) == FILLING_STATUS_ONOFF);
        response.lowBankCommandArm01 = ((inputRegisters[0] & LOW_BANK_COMMAND_OPENCLOSE) == LOW_BANK_COMMAND_OPENCLOSE);
        response.lowBankCommandArm02 = ((inputRegisters[1] & LOW_BANK_COMMAND_OPENCLOSE) == LOW_BANK_COMMAND_OPENCLOSE);
        response.mediumBankCommandArm01 = ((inputRegisters[0] & MEDIUM_BANK_COMMAND_OPENCLOSE) == MEDIUM_BANK_COMMAND_OPENCLOSE);
        response.mediumBankCommandArm02 = ((inputRegisters[1] & MEDIUM_BANK_COMMAND_OPENCLOSE) == MEDIUM_BANK_COMMAND_OPENCLOSE);
        response.highBankCommandArm01 = ((inputRegisters[0] & HIGH_BANK_COMMAND_OPENCLOSE) == HIGH_BANK_COMMAND_OPENCLOSE);
        response.highBankCommandArm02 = ((inputRegisters[1] & HIGH_BANK_COMMAND_OPENCLOSE) == HIGH_BANK_COMMAND_OPENCLOSE);
        response.outputValveCommandArm01 = ((inputRegisters[0] & OUTPUT_BANK_COMMAND_OPENCLOSE) == OUTPUT_BANK_COMMAND_OPENCLOSE);
        response.outputValveCommandArm02 = ((inputRegisters[1] & OUTPUT_BANK_COMMAND_OPENCLOSE) == OUTPUT_BANK_COMMAND_OPENCLOSE);
        response.pt01Arm01 = ((inputRegisters[0] & PT1_OKFAIL) == PT1_OKFAIL);
        response.pt01Arm02 = ((inputRegisters[1] & PT1_OKFAIL) == PT1_OKFAIL);
        response.pt02Arm01 = ((inputRegisters[0] & PT2_OKFAIL) == PT2_OKFAIL);
        response.pt02Arm02 = ((inputRegisters[1] & PT2_OKFAIL) == PT2_OKFAIL);
        response.sensorPowerArm01 = ((inputRegisters[0] & SENSOR_POWER_OKFAIL) == SENSOR_POWER_OKFAIL);
        response.sensorPowerArm02 = ((inputRegisters[1] & SENSOR_POWER_OKFAIL) == SENSOR_POWER_OKFAIL);
        return response;
    }


    @Override
    public ResponseType getType() {
        return ResponseType.STATUS;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", ParkerCngStatusCngResponse.class.getSimpleName() + "[", "]")
                .add("dispenserId=" + dispenserId)
                .add("fillingStatusArm01=" + fillingStatusArm01)
                .add("fillingStatusArm02=" + fillingStatusArm02)
                .add("lowBankCommandArm01=" + lowBankCommandArm01)
                .add("lowBankCommandArm02=" + lowBankCommandArm02)
                .add("mediumBankCommandArm01=" + mediumBankCommandArm01)
                .add("mediumBankCommandArm02=" + mediumBankCommandArm02)
                .add("highBankCommandArm01=" + highBankCommandArm01)
                .add("highBankCommandArm02=" + highBankCommandArm02)
                .add("pt01Arm01=" + pt01Arm01)
                .add("pt02Arm01=" + pt02Arm01)
                .add("pt01Arm02=" + pt01Arm02)
                .add("pt02Arm02=" + pt02Arm02)
                .add("sensorPowerArm01=" + sensorPowerArm01)
                .add("sensorPowerArm02=" + sensorPowerArm02)
                .toString();
    }
}
