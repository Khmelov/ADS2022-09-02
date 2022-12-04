package by.it.group151004.prokopchuk.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T> {
    private final int START_SIZE = 10;
    private Object[] array = new Object[5];
    private int currSize = 0;

    @Override
    public boolean remove(Object o) {
        if (!contains(o)){
            return false;
        }

        int pos = -1;
        for (int i = 0; i < currSize; i++) {
            if (o == array[i])
                pos = i;
        }
        if (pos == -1) {
            return false;
        }
        for (int i = pos; i < currSize; i++) {
            array[i] = array[i+1];
        }
        array[currSize] = null;
        currSize--;
        return true;
    }

    @Override
    public boolean add(T e) {
        if (!contains(e)) {
            if (currSize == array.length - 1) {
                Object[] new_arr = new Object[array.length + START_SIZE/2];
                System.arraycopy(array,0,new_arr,0,currSize);
                array = new_arr;
            }
            array[currSize++] = e;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder new_str = new StringBuilder();
        new_str.append("[");
        for (int i = 0; i < currSize; i++) {
            new_str.append(String.valueOf(array[i]));
            new_str.append(", ");
        }
        if (currSize != 0) {
            new_str.delete(new_str.length() - 2, new_str.length());
            new_str.append("]");
        } else {
            new_str.append("]");
        }
        return new_str.toString();
    }

    @Override
    public int size() {
        return currSize;
    }

    @Override
    public boolean isEmpty() {
        return currSize==0;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean mark = true;
        for (Object el : c) {
            remove(el);
        }
        return mark;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        int add_size = 0;
        for (int i =0 ; i < currSize ; i++) {
            if (!contains(array[i])) {
                add_size++;
            }
        }
        Object[] new_arr = new Object[currSize + add_size + START_SIZE/2];
        System.arraycopy(array,0,new_arr,0,currSize);
        array = new_arr;
        for (Object el : c) {
            if (!contains(el))
                array[currSize++] = el;
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        for (Object el : array) {
            if (el == o) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object el : c) {
            if (!contains(el)) {
                return false;
            }
        }
        return true;
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
        currSize = 0;
        array = new Object[currSize];
    }
}
