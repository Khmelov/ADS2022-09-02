package by.it.group151003.shafarenko.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T> {
    ListB<T> list = new ListB<>();

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
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        list = new ListB<>();
    }

    //Implementation
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (var elem:c)
            if (!contains(elem))
                return false;
        return true;
    }

    @Override
    public boolean add(T e) {
        if (!list.contains(e)) {
            list.add(e);
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean added = false;
        for (T elem:c){
            if (add(elem))
                added = true;
        }
        return added;
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (var elem : c)
            remove(elem);
        return true;
    }

}
