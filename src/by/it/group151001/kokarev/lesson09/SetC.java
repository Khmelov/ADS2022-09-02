package by.it.group151001.kokarev.lesson09;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T>{
    private HashMap<T, Object> HMap;
    private static final Boolean NOT_NULL = true;
    public SetC(){
        HMap = new HashMap<>();
    }

    @Override
    public int size() {
        return HMap.size();
    }

    @Override
    public boolean isEmpty() {
        return HMap.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return HMap.containsKey(o);
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
        return (HMap.put(t,NOT_NULL) != NOT_NULL);
    }

    @Override
    public boolean remove(Object o) {
        return (HMap.remove(o) == NOT_NULL);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object item:c){
            if (!HMap.containsKey(item)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean ischanged = false;
        for (T item:c){
            if (add(item)) ischanged = true;
        }
        return ischanged;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean ischanged = false;
        for (Object item: c){
            if (HMap.remove(item) != null) ischanged = true;
        }
        return ischanged;
    }

    @Override
    public void clear() {
        HMap.clear();
    }

    public String toString() {
        if (size() == 0) return "[]";
        else {
            StringBuilder res = new StringBuilder();
            res.append("[");

            for (T item: HMap.keySet()) {
                res.append(item).append(", ");
            }

            res.delete(res.length() - 2,res.length());
            res.append("]");
            return res.toString();
            }
    }
}
