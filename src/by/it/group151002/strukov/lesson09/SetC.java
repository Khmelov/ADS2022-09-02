package by.it.group151002.strukov.lesson09;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T> {
    private final int DEF_SIZE = 10;
    private Object[] arr = new Object[DEF_SIZE];
    private int size = 0;

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
        for (Object item : arr)
        {
            if (item == o)
            {
                return true;
            }
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
        if (!contains(t)) {
            if (size == arr.length - 1) {
                Object[] newArray = new Object[arr.length * 2];
                System.arraycopy(arr, 0, newArray, 0, size);
                arr = newArray;
            }
            arr[size++] = t;
            return true;
        }

        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o))
            return false;

        int index = -1;
        for (int i = 0; i < size; i++)
            if (o == arr[i])
                index = i;
        if (index == -1)
            return false;

        for (int i = index; i < size; i++)
            arr[i] = arr[i + 1];
        arr[size] = null;
        size--;

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object item : c)
            if (!contains(item))
            {
                return false;
            }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean res = false;
        for (T item : c)
            if (add(item))
                res = true;
        return res;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = false;
        for (Object item : c)
            if (remove(item))
                res = true;
        return res;
    }

    @Override
    public void clear() {
        arr = new Object[0];
        size = 0;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        if (size != 0)
        {
            for (int i = 0; i < size - 1; i++) {
                res.append(arr[i]);
                res.append(", ");
            }

            res.append(arr[size - 1]);
        }

        res.append("]");
        return res.toString();
    }
}
