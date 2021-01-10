package cng.automation.generics;

@FunctionalInterface
public interface SingleEventListener<T> {
    void onEvent(T object);
}
