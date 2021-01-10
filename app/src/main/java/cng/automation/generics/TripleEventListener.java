package cng.automation.generics;

@FunctionalInterface
public interface TripleEventListener<T, R, V> {
    void onEvent(T t, R r, V v);

}
