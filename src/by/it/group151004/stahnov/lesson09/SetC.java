package by.it.group151004.stahnov.lesson09;

import java.util.*;

public class SetC<T> extends AbstractSet<T> implements Set<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};
    //transient Object[] elementData;
    private Object[] elementData;
    private int size;
    private int maxSize;

    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(elementData, 0, newArray, 0, size);
        elementData = newArray;
        maxSize = newLength;
    }

    public SetC() {
        //this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
        this.elementData = new Object[DEFAULT_CAPACITY];
        maxSize = DEFAULT_CAPACITY;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    public boolean isEqual(Object a, Object b)
    {
        if(a == null)
        {
            return b == null;
        }
        else
            return a.equals(b);
    }

    @Override
    public boolean contains(Object o) {
        for(int i = 0; i < size; i++)
        {
            if(isEqual(elementData[i],o))
                return true;
        }
        return false;
    }

    @Override
    public Object[] toArray() {
        return super.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return super.toArray(a);
    }

    @Override
    public boolean add(T t) {
        if(!contains(t))
        {
            size++;
            if(maxSize <= size) {
                //maxSize = maxSize * 2;
                resize(maxSize * 2);
            }
            elementData[size-1] = t;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        for(int j = 0; j < size; j++)
        {
            if (isEqual(elementData[j],o)) {
                System.arraycopy(elementData, j + 1, elementData, j, size - j);
                elementData[size] = null;
                size--;
                if (elementData.length > DEFAULT_CAPACITY && size < elementData.length / 2)
                    resize(elementData.length/2);
                return true;
            }
        }
        return false;


    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean flag = true;
        Object[] tmp = c.toArray();
        for(int i = 0; i < c.size(); i++){
            if (!contains((T) tmp[i])) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] tmp = c.toArray();
        for(int i = 0; i < c.size();i++)
        {
            add((T)tmp[i]);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return super.retainAll(c);
    }

    @Override
    public void clear() {
        elementData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for(int i = 0; i < size; i++)
        {
            str.append(elementData[i]);
            str.append(", ");
        }
        if(str.length() > 1) {
            str.deleteCharAt(str.length() - 1);
            str.deleteCharAt(str.length() - 1);
        }
        str.append("]");
        return str.toString();
    }

    @Override
    public boolean removeAll(Collection<?> c){
        Object[] tmp = c.toArray();
        for(int i = 0; i< c.size(); i++)
            remove((T)tmp[i]);
        return true;
    }
}
