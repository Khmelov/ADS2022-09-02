package by.it.group151002.ravodin.lesson09;


import java.util.*;
import java.util.stream.Collectors;

public class ListA<T> implements List<T> {
    private final int startSize = 10;
    private int size = 0;
    private Object[] arr = new Object[startSize];

    @Override
    public String toString(){
        return "[" + Arrays.stream(arr).limit(size).map(Object::toString).collect(Collectors.joining(", ")) +
                ']';
    }

    @Override
    public T get(int index) {return (T)arr[index];
    }
    @Override
    public boolean add(T t) {
        arr[size] = t;
        size++;
        float loadToExpand = 0.8f;
        if(((float)arr.length)/ size >= loadToExpand){
            float expansionCoefficient = 2;
            Object[] newArr = new Object[(int)(arr.length* expansionCoefficient)];
            System.arraycopy(arr,0,newArr,0,arr.length);
            arr = newArr;
        }
        return true;
    }

    @Override
    public T remove(int index) {
        T valueToReturn = (T)arr[index];
        System.arraycopy(arr,index+1,arr,index, size -index-1);
        size--;
        arr[size] = null;
        return valueToReturn;
    }

    @Override
    public boolean remove(Object o) {
        return false;
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
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

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
