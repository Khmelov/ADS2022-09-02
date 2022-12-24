package by.it.group151003.denisova.lesson09;

import java.util.*;

public class ListA<T> implements List<T> {

    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    int size=0;

    public ListA(){
        this.array=new Object[DEFAULT_CAPACITY];
    }

    @Override
    public boolean add(T t) {
        if(size== array.length){
            Object[] array2=new Object[array.length*2];
            System.arraycopy(array,0,array2,0,size);
            array=array2;
        }
        array[size++]=t;
        return true;
    }

    @Override
    public String toString(){
        StringBuilder result=new StringBuilder("[");
        for(int i=0;i<size-1;i++){
            result.append(array[i]).append(", ");
        }
        result.append(array[size-1]).append("]");
        return result.toString();
    }

    @Override
    public int size() {
        return 0;
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
    public T get(int index) {
        return (T) array[index];
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
       T tmp = (T)array[index];
       if (array.length-1-index>=0) System.arraycopy(array, index+1, array, index, array.length-1-index);
       array[size-1]=null;
       size--;
        return tmp;
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
}


