package cng.automation.forecourt;

import cng.automation.generics.TBD;

import javax.annotation.CheckForNull;
import javax.annotation.Nonnull;

public interface DevicePollable<T extends PackageRequest, R extends  PackageResponse> {

    T getCommandToExec();
    boolean process(R response);

}
