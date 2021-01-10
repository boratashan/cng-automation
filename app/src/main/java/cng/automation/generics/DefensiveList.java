package cng.automation.generics;

import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class DefensiveList<T extends Cloneable> {

    private List<T> list = new ArrayList<>();

    public boolean add(T t) {
        return list.add(t);
    }

    public T get(int index) throws CloneNotSupportedException {
        T obj = list.get(index);
        T result =  ObjectUtils.clone(obj);
        if (Objects.isNull(result))
            throw new CloneNotSupportedException();
        return result;
    }

    public T remove(int index) {
        return list.remove(index);
    }

    public T update(int index, T t) {
        return list.set(index, t);
    }
}
