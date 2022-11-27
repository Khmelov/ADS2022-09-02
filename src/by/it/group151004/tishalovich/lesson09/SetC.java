package by.it.group151004.tishalovich.lesson09;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class SetC<E> implements Set<E> {

    HashMap<E, Object> map;
    private static final Object THERE_IS = new Object();

    public SetC() {
        map = new HashMap<>();
    }

    @Override
    public int size() {
        return map.keySet().size();
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
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        return map.put(e, THERE_IS) == null;
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) != null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element :
                c) {
            if (!contains(element)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E element:
                c) {
            add(element);
            result = true;
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        for (Object element :
                c) {
            result = remove(element) || result;
        }
        return result;
    }

    @Override
    public void clear() {
        map = new HashMap<>();
    }

    @Override
    public String toString() {
        return map.keySet().toString();
    }
}