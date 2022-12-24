package by.it.group151003.klimovich.lesson09;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T> {
    private T[] elems;

    private int size;

    public SetC() {
        size = 0;
        elems = (T[]) new Object[0];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() { return size==0 ? true: false; }

    @Override
    public boolean contains(Object o) {
        for (Object element : elems) {
            if (element == o) {
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
    public boolean add(T element) {
        elems = Arrays.copyOf(elems, elems.length + 1);
        elems[size] = element;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = -1;
        for (int i = 0; i < size; i++)
            if (elems[i] == o)
                index = i;
        if (index == -1)
            return false;
        System.arraycopy(elems, index+1, elems, index, size-index-1);
        size--;
        elems[size] = null;
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
        Object[] modifiedArray = new Object[size + c.size()];
        System.arraycopy(elems, 0, modifiedArray, 0, size);
        elems = (T[]) modifiedArray;
        for (Object element : c) {
            if (!contains(element))
                elems[size++] = (T) element;
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) { return false; }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = true;
        for (Object element: c)
            isRemoved = remove(element);
        return isRemoved;
    }

    @Override
    public void clear() {
        elems = (T[]) new Object[0];
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        if(size>0)
            str.append(elems[0]);
        for(int i=1; i<size; i++){
            str.append(", ");
            str.append(elems[i]);
        }
        str.append("]");
        return str.toString();
    }
}
