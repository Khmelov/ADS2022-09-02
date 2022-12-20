package by.it.group151002.talalaev.lesson09;


import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class TaskC<T> implements Set<T> {
    private final int INITIAL_SIZE = 10;
    private Object[] arr = new Object[INITIAL_SIZE];
    private int curSize = 0;

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < curSize; i++) {
            str.append(arr[i]);
            str.append(", ");
        }
        if (str.length() > 3)
            str.delete(str.length() - 2, str.length());
        str.append("]");
        return str.toString();
    }

    @Override
    public boolean add(T t) {
        if (!contains(t)) {
            if (curSize == arr.length - 1) {
                Object[] newArray = new Object[arr.length * 2];
                System.arraycopy(arr, 0, newArray, 0, curSize);
                arr = newArray;
            }
            arr[curSize++] = t;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o))
            return false;
        int index = -1;
        for (int i = 0; i < curSize; i++)
            if (o == arr[i])
                index = i;
        if (index == -1)
            return false;
        for (int i = index; i < curSize; i++)
            arr[i] = arr[i + 1];
        arr[curSize] = null;
        curSize--;
        return true;
    }

    @Override
    public boolean contains(Object o) {
        for (Object el : arr)
            if (el == o)
                return true;
        return false;
    }

    @Override
    public int size() {
        return curSize;
    }

    @Override
    public boolean isEmpty() {
        return curSize == 0;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean res = false;
        for (T el : c)
            if (add(el))
                res = true;
        return res;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object el : c)
            if (!contains(el))
                return false;
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = false;
        for (Object el : c)
            if (remove(el))
                res = true;
        return res;
    }

    @Override
    public void clear() {
        arr = new Object[0];
        curSize = 0;
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
}