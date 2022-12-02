package by.it.group151002.rusakovich.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<T> implements List<T> {
    private int listSize = 0;
    private int listLength = 0;
    private static final float grow_coefficient = 0.9f;
    private static final int defaultLen = 10;
    private Object[] arr;

    public ListB(){
        this.arr = new Object[defaultLen];
        listLength = defaultLen;
    }

    public ListB(int size){
        this.arr = new Object[size];
        listLength = size;
    }

    private void grow(){
        listLength = listLength + (listLength >> 1) + 1;
        Object[] newArr = new Object[listLength];
        if (listSize > 0) System.arraycopy(arr, 0, newArr, 0, listSize);
        arr = newArr;
    }

    @Override
    public int size() {
        return listSize;
    }

    @Override
    public boolean isEmpty() {
        return listSize == 0;
    }

    @Override
    public boolean add(T t) {
        if(listSize + 1 >= (int)(listLength * grow_coefficient))
            grow();
        arr[listSize] = t;
        ++listSize;
        return true;
    }

    @Override
    public T remove(int index) {
        if(index >= listSize || index < 0)
            return null;
        Object res = arr[index];
        System.arraycopy(arr, index + 1, arr, index, listSize - 1 - index);
        arr[listSize-1] = null;
        listSize--;
        return (T) res;
    }

    @Override
    public T get(int index) {
        if(index >= listSize || index < 0)
            return null;
        return (T) arr[index];
    }


    @Override
    public T set(int index, T element){
        if(index > listSize || index < 0)
            return null;
        if(index == listSize)
            add(element);
        T res = (T) arr[index];
        arr[index] = element;
        return res;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for(T element : c){
            add(element);
        }
        return true;
    }

    @Override
    public void add(int index, T element) {
        if(index < 0 || index > listLength)
            return;
        if(listSize + 1 >= (int)(listLength * grow_coefficient))
            grow();
        System.arraycopy(arr, index, arr, index + 1, listSize - index);
        arr[index] = element;
        listSize++;
    }

    @Override
    public String toString(){
        StringBuilder b = new StringBuilder();
        b.append("[");
        for(int i = 0; i < listSize; ++i) {
            if(arr[i] == null)
                b.append("null");
            else
                b.append(arr[i].toString());
            if(i != listSize-1)
                b.append(", ");
        }
        b.append("]");
        return b.toString();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if(index < 0 || index > listLength)
            return false;
        while(listSize + c.size() >= (int)(listLength * grow_coefficient))
            grow();
        System.arraycopy(arr, index, arr, index + c.size(), listSize - index);
        int i = 0;
        for(T el : c){
            add(index+i, el);
        }
        return true;

    }

    @Override
    public boolean contains(Object o) {
        for(int i = 0; i < listSize; ++i)
            if(arr[i] == o)
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
    public boolean remove(Object o) {
        int index = indexOf(o);
        if(index == -1)
            return false;
        remove(index);
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
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
    public void clear() {

    }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < listSize; ++i)
            if(o == arr[i])
                return i;
        return -1;
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
}
