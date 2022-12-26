package by.it.group151002.kuvshinov.lesson09;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T> {
    private int SET_SIZE = 10;
    private Object[] array = new Object[SET_SIZE];
    private int size = 0;

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        if (size != 0) {
            for (int i = 0; i < size - 1; i++) {
                res.append(array[i]);
                res.append(", ");
            }
            res.append(array[size - 1]);
        }

        res.append("]");
        return res.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public boolean contains(Object o) {
        for (Object elem: array)
            if (elem == o)
                return true;
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
    public boolean add(T t) {
        if (!contains(t)) {
            if (size == array.length - 1) {
                Object[] newArr = new Object[array.length * 2];
                System.arraycopy(array, 0, newArr, 0, size);
                array = newArr;
            }
            array[size++] = t;
            return true;
        }
        return false;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o))
            return false;
        int index = -1;
        for (int i = 0; i < size; i++)
            if (o == array[i])
                index = i;
        if (index == -1)
            return false;
        for (int i = index; i < size; i++)
            array[i] = array[i + 1];
        array[size--] = null;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        for (Object el : c)
            if (remove(el))
                result = true;
        return result;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object elem: c)
            if (!contains(elem))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean result = false;
        for (T item : c)
            if (add(item))
                result = true;
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }



    @Override
    public void clear() {
        size = 0;
        array = new Object[0];
    }
}
