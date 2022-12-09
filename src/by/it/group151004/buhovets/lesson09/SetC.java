package by.it.group151004.buhovets.lesson09;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class SetC<E> implements Set<E> {

    private int size;
    private static final int DEFAULT_CAPACITY=16;
    private int capacity;
    private ArrayList<LinkedList<E>> cells;

    public  SetC(){
        size=0;
        capacity=DEFAULT_CAPACITY;
        cells = new ArrayList<>(capacity); //через ListB не вышло Т_Т
        for(int i=0; i<capacity;i++){
            cells.add(new LinkedList<>());
        }
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(size==0)
            return true;
        return false;
    }

    @Override
    public boolean contains(Object o) {
        int index=getHash((E)o);
        if(cells.get(index).contains(o))
            return true;
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) {
        int index = getHash(e);

        if(cells.get(index).contains(e))
            return false;

        cells.get(index).add(e);
        size++;
        return true;
    }

    private int getHash(E e){
        if(e==null)
            return 0;
        return e.hashCode()&(capacity-1);
    }


    @Override
    public boolean remove(Object o) {
        int index = getHash((E)o);
        if(!cells.get(index).contains(o))
            return false;
        cells.get(index).remove(o);
        size--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        int index;
        for(Object element: c){
            index=getHash((E)element);
           if(!cells.get(index).contains(element))
               return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        c.forEach(this::add);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        c.forEach(this::remove);
        return true;
    }

    @Override
    public void clear() {
        cells.forEach(LinkedList::clear);
        size=0;
    }

    @Override
    public boolean equals(Object o) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public Spliterator<E> spliterator() {
        return Set.super.spliterator();
    }

    public static void main(String[] args){
        SetC<Short> a = new SetC<Short>();
        System.out.println(a.add((short)100));

    }
}
