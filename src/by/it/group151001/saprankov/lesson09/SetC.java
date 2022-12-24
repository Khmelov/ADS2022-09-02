package by.it.group151001.saprankov.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SetC<T> implements Set<T> {
    private final int DEFAULT_CAPACITY = 10;
    private Object[] arr = new Object[DEFAULT_CAPACITY];
    private int currSize = 0;
    public String toString(){
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
        return currSize;
    }

    @Override
    public boolean isEmpty() {
        return currSize == 0;
    }

    @Override
    public boolean contains(Object o) {
        for(Object obj: arr){
            if(obj==null&&o==null){
                return true;

            }
            else {
                if(obj!=null&&obj.equals(o)){
                    return true;
                }
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
    public boolean add(T t) {
        if(!contains(t)){
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
        return false;


    }

    @Override
    public boolean remove(Object o) {
        if(!contains(o)){
            return false;
        }
        else{
            Object[] n = new Object[currSize-1];
            int j=0,i=0;
            for(Object ch : arr){

                if(ch!=o){
                    n[j]=arr[i];
                    j++;
                }
                i++;
            }
            arr=n;
            currSize--;
            return true;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object col:c){
           if(!contains(col)){
               return false;
           }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for(T el : c){
            if(!contains(el)){
                add((T)el);
            }

        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for(Object con: c){
            if(contains(con)){
                remove(con);
            }
        }
        return true;
    }

    @Override
    public void clear() {
        for(int i=0;i<currSize;i++){
            remove(arr[i]);
        }
        currSize=0;

    }
}
