package by.it.group151001.kononchuk.lesson09;

import java.util.*;

public class SetC<T> implements Set<T> {
    ListB<T> data;

    public  SetC(){
        data = new ListB<>();
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return data.contains(o);
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
        if (!data.contains(t)){
            data.add(t);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return data.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean isContained = true;

        for (var elem:c) {
            if (!contains(elem)){
                isContained = false;
                break;
            }
        }
        return isContained;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T elem:c){
            add(elem);
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isChanged = false;
        for (var elem : c) {
            if(remove(elem)){
                isChanged = true;
            };
        }

        return isChanged;
    }

    @Override
    public void clear() {
        data.clear();
    }


    public String toString(){
        return data.toString();
    }


    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();

        set.add(12);
        set.add(23);

        set.add(56);

        set.add(null);

        System.out.println(set.toString());
    }
}
