package by.it.group151002.protchenko.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T> {

    private final int INIT_SIZE = 10;
    private Object[] arr = new Object[INIT_SIZE];
    private int size = 0;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object o: arr) {
            sb.append(o);
            sb.append(", ");
        }
        if (sb.length() > 3)
            sb.delete(sb.length()-2, sb.length());
        sb.append("]");
        return sb.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object b : arr)
            if (b == o)
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
        for (Object b : c)
            if (!contains(b))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean res = false;
        for (T o : c)
            if (add(o))
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
        for (Object o : c)
            if (remove(o))
                res = true;
        return res;
    }

    @Override
    public void clear() {
        arr = new Object[0];
        size = 0;
    }
}
