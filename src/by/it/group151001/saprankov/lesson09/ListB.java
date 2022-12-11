package by.it.group151001.saprankov.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<T> implements List<T> {
    private final int DEFAULT_CAPACITY = 10;
    private Object[] arr = new Object[DEFAULT_CAPACITY];
    private int currSize = 0;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < currSize; i++) {
            sb.append(arr[i]);
            if (i != currSize - 1) {
                sb.append(", ");
            }

        }
        sb.append("]");
        return sb.toString();
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
    public boolean add(T t) {

        Object[] new_arr = new Object[currSize + 1];
        int i = 0;
        for (i = 0; i < currSize; i++) {
            new_arr[i] = arr[i];
        }
        new_arr[i] = t;
        arr = new_arr;
        currSize++;

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
        int new_sz =currSize+c.size();
        Object[] new_t = new Object[new_sz];
        Object[] add_arr = new Object[c.size()];
        for(int i=0;i<currSize;i++){
            new_t[i]=arr[i];
        }
        c.toArray(add_arr);
        for(int i=0;i<c.size();i++){
            new_t[i+currSize]=add_arr[i];
        }
        arr=new_t;
        currSize=new_sz;
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
    public void clear() {

    }

    @Override
    public T get(int index) {
        T get_i = (T) arr[index];
        return get_i;
    }

    @Override
    public T set(int index, T element) {
    T set = (T) arr[index];
    arr[index]=element;
    return set;
    }

    @Override
    public void add(int index, T element) {
        Object[] exp_arr = new Object[currSize+1];
        int j=0;
        for(int i=0;i<=currSize;i++){
            if(i!=index) {
                exp_arr[i] = arr[j];
                j++;
            }
            else{
                exp_arr[index]=element;
            }
        }
        arr=exp_arr;
        currSize++;

    }

    @Override
    public T remove(int index) {
        Object[] rep_arr=new Object[currSize-1];
        T rem = (T)arr[index];
        for(int i=0,j=0;i<currSize;i++){
            if(i!=index) {
                rep_arr[j]=arr[i];
                j++;
            }
        }
        arr=rep_arr;
        currSize--;
        return rem;
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
