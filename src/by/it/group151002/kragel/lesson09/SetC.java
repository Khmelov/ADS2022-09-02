package by.it.group151002.kragel.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SetC<T> implements Set<T> {

    List<T>[] table;
    int length;

    @SuppressWarnings("unchecked")
    SetC(){
        length = 64;
        table = new ListB[length];
        for (int i = 0; i < length; i++) {
            table[i] = new ListB<>();
        }
    }
    int hash(Object o){
        if (o == null)
            return 0;
        int hash = o.hashCode() % length;
        return hash < 0 ? -hash : hash;
    }
    @Override
    public int size() {
        int size = 0;
        for (List<T> l: table) {
            size += l.size();
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        boolean res = true;
        for (List<T> l: table) {
            res &= l.isEmpty();
        }
        return res;
    }

    @Override
    public boolean add(T t) {
        int h = hash(t);
        if (table[h].contains(t))
            return false;
        return table[h].add(t);
    }

    @Override
    public boolean remove(Object o) {
        int h = hash(o);
        if (!table[h].contains(o))
            return false;
        table[h].remove(o);
        return true;
    }

    @Override
    public boolean contains(Object o) {
        return table[hash(o)].contains(o);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean res = false;
        for (T t : c) {
            res |= add(t);
        }
        return res;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean res = false;
        for (Object o : c) {
            res |= table[hash(o)].contains(o);
        }
        return res;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = false;
        for(Object o : c){
           res |= remove(o);
        }
        return res;
    }

    @Override
    public void clear() {
        for (List<T> l : table){
            l.clear();
        }
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
    public String toString() {
        String result = "  ";
        boolean stop = false;
        int i = 0;
        while (!stop){
            stop = true;
            for (int j = 0; j < length; j++) {
                if (i < table[j].size()) {
                    result += ", " + (table[j].get(i) == null ? "null" : table[j].get(i).toString());
                    stop = false;
                }
            }
            i++;
        }
        return "[" + result.substring(2) + "]";
    }
}
