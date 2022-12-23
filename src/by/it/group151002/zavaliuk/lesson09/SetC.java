package by.it.group151002.zavaliuk.lesson09;

import java.util.*;

public class SetC<T> implements Set<T> {
    private HashMap<T, Boolean> map;

    public SetC() {
        map = new HashMap<>();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        Boolean prev = map.put(t, true);
        return prev != null;
    }

    @Override
    public boolean remove(Object o) {
        Boolean prev = map.remove(o);
        return prev != null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object el : c) {
            if (!map.containsKey(el)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        c.forEach((el) -> map.put(el, true));
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        c.forEach((el) -> map.remove(el));
        return true;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Spliterator<T> spliterator() {
        return Set.super.spliterator();
    }
}

