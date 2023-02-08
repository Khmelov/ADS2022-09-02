package by.it.group151002.bybikov.lesson09;

import java.util.*;

public class List_B<T> implements List<T> {
    private int length = 0;
    private int capacity;
    private final static int BEGIN_CAPACITY = 10;
    private Object[] list_Field;

    private void setCapacity(int capacity) {
        if (capacity <= length) {
            throw new IndexOutOfBoundsException("Capacity: %d, must been greater then length: %d".formatted(this.capacity, this.length));
        }
        Object[] new_Field = new Object[capacity];
        for (int i = 0; i < this.length; i++) {
            new_Field[i] = this.list_Field[i];
        }
        this.list_Field = new_Field;
        this.capacity = capacity;
    }

    List_B() {
        List<Integer> list = new ArrayList<>();
        //list.subList();
        this.length = 0;
        setCapacity(BEGIN_CAPACITY);
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public boolean isEmpty() {
        return this.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        return this.indexOf(o) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] new_Field = new Object[this.length];
        for (int i = 0; i < this.length; i++) {
            new_Field[i] = this.list_Field[i];
        }
        return new_Field;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        if (this.length == this.capacity) {
            setCapacity(this.capacity * 2);
        }
        this.list_Field[this.length++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1){
            this.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() == 0)
            return false;
        if (this.size() + c.size() > this.capacity){
            this.setCapacity(this.size() + c.size());
        }
        for (T t : c) {
            this.list_Field[this.length++] = t;
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
        this.length = 0;
        setCapacity(BEGIN_CAPACITY);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException("index: %d, when the list size: %d".formatted(index, length));
        }
        return (T)this.list_Field[index];
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= this.length) {
            throw new IndexOutOfBoundsException("index: %d, when the list size: %d".formatted(index, length));
        }
        T result = this.get(index);
        this.list_Field[index] = element;
        return result;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > this.length){
            throw new IndexOutOfBoundsException("index: %d, when the list size: %d".formatted(index, length));
        }
        if (this.length == this.capacity){
            setCapacity(this.capacity * 2);
        }
        for (int i = this.length; i > index; i--) {
            this.list_Field[i] = this.list_Field[i - 1];
        }
        this.list_Field[index] = element;
        this.length++;
    }

    @Override
    public T remove(int index) {
        if(index < 0 || index >= this.length){
            throw new IndexOutOfBoundsException("index: %d, when the list size: %d".formatted(index, length));
        }
        T result = (T)this.list_Field[index];
        this.length--;
        for (int i = index; i < this.length; i++) {
            this.list_Field[i] = this.list_Field[i + 1];
        }
        return result;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null){
            for (int i = 0; i < this.length; i++) {
                if (this.list_Field[i] == null) {
                    return i;
                }
            }
        }
        else {
            for (int i = 0; i < this.length; i++) {
                if (o.equals(this.list_Field[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null){
            for (int i = this.length - 1; i >= 0; i--) {
                if (this.list_Field[i] == null)
                    return i;
            }
        }
        else {
            for (int i = this.length - 1; i >= 0; i--) {
                if (o.equals(this.list_Field[i]))
                    return i;
            }
        }
        return -1;
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

    @Override
    public String toString () {
        StringBuilder result = new StringBuilder();
        result.append('[');
        if (this.length > 0){
            if (this.list_Field[0] == null){
                result.append("null");
            }
            else {
                result.append(this.list_Field[0].toString());
            }
            for (int i = 1; i < this.length; i++) {
                result.append(", ");
                if (this.list_Field[i] == null){
                    result.append("null");
                }
                else {
                    result.append(this.list_Field[i].toString());
                }
            }
        }
        result.append(']');
        return result.toString();
    }
}
