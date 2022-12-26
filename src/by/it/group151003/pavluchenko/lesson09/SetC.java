package by.it.group151003.pavluchenko.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T> {
    public int defaultSize = 10;
    public Object[] array = new Object[defaultSize];
    public int currentSize = 0;

    @Override
    public boolean contains(Object o) {
        for (Object element : array) {
            if (element == o) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean add(T t) {
        if (!contains(t)) {
            if (currentSize == array.length-1) {
                Object[] newArray = new Object[array.length * 2];
                System.arraycopy(array, 0, newArray, 0, currentSize);
                array = newArray;
            }
            array[currentSize++] = t;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        int index = -1;
        for (int i = 0; i < currentSize; i++)
            if (array[i] == o)
                index = i;
        if (index == -1)
            return false;
        for (int i = index; i < currentSize; i++)
            array[i] = array[i + 1];
        array[currentSize--] = null;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) throw new NullPointerException();
        for (Object o : c) {
            if (!contains(o))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] modifiedArray = new Object[currentSize + c.size()];
        System.arraycopy(array, 0, modifiedArray, 0, currentSize);
        array = modifiedArray;
        for (Object element : c) {
            if (!contains(element))
                array[currentSize++] = element;
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = true;
        for (Object element: c)
            isRemoved = remove(element);
        return isRemoved;
    }

    @Override
    public void clear() {
        array = new Object[0];
        currentSize = 0;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("[");
        for (int i = 0; i < currentSize; i++) {
            string.append(array[i]);
            string.append(", ");
        }
        if (string.length() > 3) {
            string.delete(string.length() - 2, string.length());
        }
        string.append("]");

        return string.toString();
    }

    @Override
    public boolean isEmpty() {
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
    public boolean retainAll(Collection<?> c) {
        return false;
    }
}
