package by.it.group151002.rusakovich.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T> {

    private ListB<ListB<T>> hashTable;
    private final int startLen = 100;
    private int currSize = 0;
    //private final float grow_coefficient = 0.9f;
    private int length = 0;
    private Object nullEl = 1;

    public SetC() {
        this.hashTable = new ListB<>(startLen);
        for(int i = 0; i < startLen; ++i)
            hashTable.add(new ListB<>());
        this.length = startLen;
    }

    @Override
    public String toString() {
        if(currSize == 0)
            return "[]";
        StringBuilder b = new StringBuilder();
        b.append("[");
        if(nullEl == null)
            b.append("null, ");
        for(int i = 0; i < length; ++i) {
            if (hashTable.get(i) != null)
                for (int j = 0; j < hashTable.get(i).size(); ++j) {
                    b.append(hashTable.get(i).get(j).toString());
                    b.append(", ");
                }
        }
        b.delete(b.length()-2, b.length());
        b.append("]");
        return b.toString();
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
        if(o == null)
            if(nullEl == null)
                return true;
            else
                return false;
        if(currSize == 0)
            return false;
        int index = System.identityHashCode(o) % length;
        ListB<T> temp = hashTable.get(index);
        for(int i = 0; i < temp.size(); i++)
            if(temp.get(i).equals(o))
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
    public boolean add(T t) {
        if(t == null) {
            if (nullEl != null)
                nullEl = null;
            return true;
        }
        if(contains(t))
            return false;
       int index = System.identityHashCode(t) % length;
       hashTable.get(index).add(t);
       currSize++;
       return true;
    }

    @Override
    public boolean remove(Object o) {
        if(o == null)
            if(nullEl == null) {
                nullEl = 1;
                return true;
            } else return false;
        if(!contains(o))
            return false;
        int index = System.identityHashCode(o) % length;
        hashTable.get(index).remove(o);
        currSize--;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object el : c)
            if(!contains(el))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for(T el : c)
            add(el);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for(Object el : c){
            remove(el);
        }
        return true;
    }

    @Override
    public void clear(){
        for(int i = 0; i < length; ++i)
            for(int j = 0; j < hashTable.get(i).size(); ++j) {
               remove(hashTable.get(i).get(j));
            }
    }
}
