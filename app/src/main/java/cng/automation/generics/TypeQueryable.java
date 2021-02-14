package cng.automation.generics;

public interface TypeQueryable<T extends Enum<?>> {
    T getType();
}
