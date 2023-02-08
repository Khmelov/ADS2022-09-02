package by.it.group151003.romanko.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<T> implements List<T> {

    private static final int DEF_CAP = 10;

    private int size = 0;
    private int capacity = 0;
    private Object[] elements;

    public ListB() {
        this.elements = new Object[DEF_CAP];
        this.capacity = DEF_CAP;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("[");
        for (int i = 0; i < this.size; ++i) {
            if(this.elements[i] == null)
                b.append("null");
            else {
                b.append(this.elements[i].toString());
            }
            if (i != this.size - 1)
                b.append(", ");
        }
        b.append("]");
        return b.toString();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        for (Object elem : this.elements){
            if (elem == o){
                return true;
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
    public boolean add(T elem) {
        if (this.size + 1 == this.capacity) {
            this.capacity = (int) (this.capacity * 1.5);
            Object[] temp = new Object[this.capacity];
            System.arraycopy(this.elements, 0, temp, 0, this.size);
            this.elements = temp;
        }
        elements[this.size] = elem;
        this.size++;
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
        for(T elem: c){
            add(elem);
        }
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
        return (T) this.elements[index];
    }

    @Override
    public T set(int index, T elem) {
        if(index > this.size || index < 0)
            return null;
        T res = (T) this.elements[index];
        if(index == this.size) {
            add(elem);
        } else {
            this.elements[index] = elem;
        }
        return res;
    }

    @Override
    public void add(int index, T element) {
        if (this.size == this.capacity) {
            this.capacity = (int) (this.capacity * 1.5);
            Object[] temp = new Object[this.capacity];
            System.arraycopy(this.elements, 0, temp, 0, this.size);
            this.elements = temp;
        }
        System.arraycopy(this.elements, index, this.elements, index + 1, this.size - index );
        this.elements[index] = element;
        this.size++;
    }

    @Override
    public T remove(int index) {
        Object[] newArr = new Object[this.capacity - 1];
        Object returnElem = this.elements[index];
        if (index > 0) {
            System.arraycopy(this.elements, 0, newArr, 0, index);
        }
        System.arraycopy(this.elements, index + 1, newArr, index, this.size - index - 1);
        this.elements = newArr;
        this.size--;
        return (T) returnElem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < this.size; i++){
            if (this.elements[i] == o){
                return i;
            }
        }
        return -1;
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
