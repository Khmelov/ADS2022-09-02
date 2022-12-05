package by.it.group151001.beryozkin.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetC <T> implements Set<T> {
    int arraySize = 11;
    Object[] array = new Object[arraySize];
    int ind = 0;

    public String toString() {
        String result = "[";
        for (int i = 0; i < ind - 1; i++) {
            result += array[i] + ", ";
        }
        if (ind!=0) result += array[ind - 1] + "]";
        return result;
    }
    @Override
    public int size() {
        return ind;
    }

    @Override
    public boolean isEmpty() {
        if (ind == 0) return true;
        return false;
    }

    @Override
    public boolean contains(Object o) {

        for (Object elem : array){
            if (elem == o) return true;
        }
        return false;

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
        if (contains(t)) return false;
            if (ind == array.length) {
                Object[] newArray = new Object[arraySize*2];
                System.arraycopy(array, 0, newArray, 0, array.length);
                array = newArray;
            }
            array[ind++] = t;
            return true;
    }

    @Override
    public boolean remove(Object o) {

        if (!contains(o)) return false;
        int index = -1;
        for (int i=0; i<ind; i++){
            if (array[i] == o){
                index = i;
            }
        }
       if (index == -1) return false;
        for (int i = index; i < ind - 1; i++) {
            array[i] = array[i + 1];
        }
        array[ind] = null;
        ind--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object elem:c){
            if (!contains(elem)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean added = false;
        for (T elem:c){
            if (add(elem)) added = true;
        }
        return added;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean removed = false;
        for (Object elem:c){
            if (remove(elem)) removed = true;
        }
        return removed;
    }

    @Override
    public void clear() {
      array = new Object[0];
      ind = 0;
    }

}
