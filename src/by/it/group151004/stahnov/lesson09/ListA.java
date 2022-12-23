package by.it.group151004.stahnov.lesson09;

import java.util.*;

public class ListA<T> extends AbstractList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    //transient Object[] elementData;
    private Object[] elementData;
    private int size;
    private int maxSize;

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(elementData, 0, newArray, 0, size);
        elementData = newArray;
    }

    public ListA() {
        //this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
        this.elementData = new Object[DEFAULT_CAPACITY];
        maxSize = DEFAULT_CAPACITY;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        for(int i = 0; i < size; i++)
        {
            if (elementData[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public boolean add(T o) {
        size++;
        if(maxSize <= size) {
            maxSize = maxSize * 2;
            resize(maxSize);
        }
        elementData[size-1] = o;
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elementData[index];
    }

    @Override
    public T set(int index, Object element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        Object tmp;
        tmp = elementData[index];
        for (int i = index; i<size; i++)
            elementData[i] = elementData[i+1];
        elementData[size] = null;
        size--;
        if (elementData.length > DEFAULT_CAPACITY && size < elementData.length / 2)
            resize(elementData.length/2);
        return (T) tmp;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }

    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("[");
        for(int i = 0; i < size; i++)
        {
            str.append(elementData[i]);
            str.append(", ");
        }
        str.deleteCharAt(str.length() - 1);
        str.deleteCharAt(str.length() - 1);
        str.append("]");
        return str.toString();
    }
}
