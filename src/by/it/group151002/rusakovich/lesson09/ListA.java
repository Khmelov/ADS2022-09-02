package by.it.group151002.rusakovich.lesson09;

import java.util.*;

public class ListA<T> implements List<T>{
    private int listSize = 0;
    private int listLength = 0;
    private static final int defaultLen = 10;
    private Object[] arr;

    public ListA(){
        this.arr = new Object[defaultLen];
        listLength = defaultLen;
    }
    public ListA(int size){
        this.arr = new Object[size];
        listLength = size;
    }
    @Override
    public boolean add(T e){
        if(listSize + 1 == listLength)
            grow();
        arr[listSize] = e;
        ++listSize;
        return true;
    }
    private void grow(){
        listLength = listLength + (listLength >> 1) + 1;
        Object[] newArr = new Object[listLength];
        if (listSize >= 0) System.arraycopy(arr, 0, newArr, 0, listSize);
        arr = newArr;
    }

    @Override
    public T get(int index) {
        if(index >= listSize || index < 0)
            return null;
        return (T) arr[index];
    }
    public T remove(int index){
        if(index >= listSize || index < 0)
            return null;
        Object res = arr[index];
        System.arraycopy(arr, index + 1, arr, index, listSize - 1 - index);
        arr[listSize-1] = null;
        listSize--;
        return (T) res;
    }
    @Override
    public String toString(){
        StringBuilder b = new StringBuilder();
        b.append("[");
        for(int i = 0; i < listSize; ++i) {
            b.append(arr[i].toString());
            if(i != listSize-1)
                b.append(", ");
        }
        b.append("]");
        return b.toString();
    }

    @Override
    public int size() {
        return listSize;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
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
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
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
    public void clear() {

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
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

}
