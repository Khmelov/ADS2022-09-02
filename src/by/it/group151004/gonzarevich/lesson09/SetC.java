package by.it.group151004.gonzarevich.lesson09;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T> {
    private final HashMap<T,Object> hashMap = new HashMap<>();
    private final Object dummy = new Object();

    @Override
    public String toString(){
        return hashMap.keySet().toString();
    }

    @Override
    public int size() {
        return hashMap.size();
    }


    @Override
    public boolean contains(Object o) {
        return hashMap.keySet().contains(o);
    }

    @Override
    public boolean add(T t) {
        hashMap.put(t,dummy);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        boolean result = hashMap.keySet().contains(o);
        hashMap.remove(o);
        return result;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return hashMap.keySet().containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        c.forEach(x->hashMap.put(x,dummy));
        return true;
    }


    @Override
    public boolean isEmpty() {
        return hashMap.isEmpty();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = hashMap.keySet().stream().anyMatch(c::contains);
        c.forEach(hashMap::remove);
        return result;
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
    public boolean retainAll(Collection<?> c) {
        return false;
    }



    @Override
    public void clear() {
        hashMap.clear();
    }
}
