package cng.automation.generics;

@FunctionalInterface
public interface TupleEventListener<T, R> {
    void onEvent(T t, R r);

}
