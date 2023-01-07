package by.it.group151003.haritonenko.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T> {
    int SIZE = 12;
    Object[] array = new Object[SIZE];
    int index = 0;

    public String toString() {
        String arrayString = "[";
        for (int i = 0; i < this.index-1; i++) {
            arrayString += array[i]  +", ";
        }
        if (this.index != 0) {
            arrayString += array[this.index - 1];
        }
        arrayString += "]";
        return arrayString;
    }

    @Override
    public int size() {
        return this.index;
    }

    @Override
    public boolean isEmpty() {
        boolean isEmpty;
        if (this.index == 0) {
            isEmpty = true;
        } else isEmpty = false;
        return isEmpty;
    }

    @Override
    public boolean contains(Object o) {
        boolean isInArray = false;
        for (Object elem: array) {
            if (elem == o) {
                isInArray = true;
            }
        }
        return isInArray;
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
        if (this.index == array.length) {
            Object[] biggerArray = new Object[array.length*2];
            System.arraycopy(array,0,biggerArray,0,array.length);
            array = biggerArray;
        }
        array[this.index] = t;
        this.index++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(!contains(o)) return false;
        int index = -1;
        for (int i = 0; i < this.index; i++) {
            if (array[i] == o) {
                index = i;
            }
        }
        if (index == -1) return false;
        for (int i = index; i < this.index-1; i++) {
            array[i] = array[i+1];
        }
        array[this.index] = null;
        this.index--;

        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean isInArrayAll = false;
        for (Object elem: c) {
            isInArrayAll = contains(elem);
            if (!isInArrayAll) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean isAdded = false;
        for (T elem: c) {
            if (add(elem)) isAdded = true;
        }
        return isAdded;

    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = false;
        for (Object elem: c) {
            if (remove(elem)) isRemoved = true;
        }
        return isRemoved;
    }

    @Override
    public void clear() {
        array = new Object[0];
        this.index = 0;
    }
}
