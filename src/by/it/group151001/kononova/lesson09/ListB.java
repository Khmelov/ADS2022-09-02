package by.it.group151001.kononova.lesson09;

import java.util.*;
import java.util.function.UnaryOperator;

public class ListB<T> implements List<T> {
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
        return false;
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
        if (index == array.length) {
            Object[] biggerArray = new Object[SIZE*2];
            System.arraycopy(array,0,biggerArray,0,array.length);
            array = biggerArray;
        }
        array[index] = t;
        index++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] arrayWithCollection = new Object[this.index + c.size()];
        System.arraycopy(array, 0, arrayWithCollection, 0, this.index);
        array = arrayWithCollection;
        for (Object el : c)
            array[this.index++] = el;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        List.super.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super T> c) {
        List.super.sort(c);
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return (T)array[index];
    }

    @Override
    public T set(int index, T element) {
        T deletedElem = (T) array[index];
        array[index] = element;
        return deletedElem;
    }

    @Override
    public void add(int index, T element) {
        add(element);
        for (int i = this.index-1; i >= index + 1; i--) {
            array[i] = array[i-1];
        }
        array[index] = element;
    }

    @Override
    public T remove(int index) {
        T deletedElem = (T) array[index];
        for (int i = index; i < this.index-1; i++) {
            array[i] = array[i+1];
        }
        array[this.index-1] = null;
        this.index--;
        return deletedElem;
    }

    @Override
    public int indexOf(Object o) {
        int result = -1;
        for (int i = 0; i < this.index; i++) {
            if (array[i] == o) {
                result = i;
            }
        }
        return result;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Spliterator<T> spliterator() {
        return List.super.spliterator();
    }
}
