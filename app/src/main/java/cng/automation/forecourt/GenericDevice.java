package cng.automation.forecourt;

import java.util.Objects;

public abstract class GenericDevice {
    private int id;

    public int getId() {
        return id;
    }

    private GenericDevice() {
        throw new IllegalStateException("Generic device should not be constructed without ID.");
    }

    public GenericDevice(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GenericDevice)) return false;
        GenericDevice that = (GenericDevice) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
