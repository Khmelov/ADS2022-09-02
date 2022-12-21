package by.it.group151001.trybchik.lesson09;

import java.util.*;

public class ListB<T> implements List<T> {
    public final int DEFAULT_SIZE = 15;

    private Object[] arr = new Object[DEFAULT_SIZE];

    private int currsize = 0;

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < currsize; i++) {
            sb.append(arr[i]);
            if (i != currsize - 1) {
                sb.append(", ");
            }

        }
        sb.append("]");
        return sb.toString();
    }
    public boolean add(T t){
        Object[] tmp= new Object[currsize+1];
        for (int i = 0;i<currsize;i++)
        {
            tmp[i] = arr[i];
        }
        tmp[currsize] = t;
        arr =tmp;
        currsize++;
        return true;
    }

    public T remove(int index)
    {
        T tmp= (T) arr[index];
        for (int i = index; i < currsize-1; i++)
            arr[i] = arr[i + 1];
        currsize--;
        return tmp;
    }

    public T get(int index)
    {
        return (T) arr[index];
    }

    @Override
    public  int size(){return 0;}
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

        Object tmp[] = new Object[currsize+c.size()];
        for (int  i =0;i<currsize;i++)
        {
            tmp[i] = arr[i];
        }
        Object new_arr[] = new Object[c.size()];
        new_arr =c.toArray();
        for(int i = 0;i<c.size();i++)
        {
            tmp[i+currsize]=new_arr[i];
        }
        currsize=currsize + c.size();
        arr = tmp;
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c)
    {
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
        T tmp = (T) arr[index];
        arr[index] = element;
        return tmp;
    }

    @Override
    public void add(int index, T element) {
        Object tmp[] =new Object[currsize+1];
        for (int i = 0;i<index;i++)
        {
            tmp[i] =arr[i];
        }
        tmp[index] = element;
        for (int  i =index;i<currsize;i++)
        {
            tmp[i+1] = arr[i];
        }
        currsize++;
        arr  = tmp;
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