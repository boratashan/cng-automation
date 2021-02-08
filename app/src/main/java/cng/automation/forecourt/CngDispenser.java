package cng.automation.forecourt;

import cng.automation.forecourt.DevicePollable;

public interface CngDispenser<T extends PackageRequest, R extends  PackageResponse> extends DevicePollable<T, R>  {
    void requestStatus();
    void updatePrice(Double price);
}
