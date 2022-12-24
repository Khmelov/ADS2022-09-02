package by.it.group151001.matsiushenko.lesson09;
import java.util.*;

public class SetC<E> implements Set<E> {
    HashMap<E, Object> map;
    private static final Object capObj = new Object();

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
        return map.put(e, capObj) == null;
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o) != null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object obj : c) {
            if (!contains(obj)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean res = false;
        for (E obj : c) {
            add(obj);
            res = true;
        }
        return res;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = false;
        for (Object obj : c) {
            remove(obj);
            res = true;
        }
        return res;
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