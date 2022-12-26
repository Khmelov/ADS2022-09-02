package by.it.group151002.saprin.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T> {

    private final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size = 0;

    private int capacity = 0;

    public SetC(){
        this.elements = new Object[DEFAULT_CAPACITY];
        this.capacity = DEFAULT_CAPACITY;
    }

    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("[");
        if (size != 0)
        {
            for (int i = 0; i < size - 1; i++) {
                b.append(this.elements[i]);
                b.append(", ");
            }

            b.append(this.elements[size - 1]);
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
        return this.size == 0;
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
    public boolean add(T t) {
        if (!contains(t)){
            if (this.size + 1 == this.capacity){
                this.capacity = (int) (this.capacity * 1.5);
                Object[] temp = new Object[this.capacity];
                System.arraycopy(this.elements, 0, temp, 0, this.size);
                this.elements = temp;
            }
            this.elements[this.size] = t;
            this.size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if(contains(o)){
            int index = -1;
            for (int i = 0; i < size; i++) {
                if (this.elements[i] == o) {
                    index = i;
                }
            }
            if (index == -1) {
                return false;
            }
            for (int i = index; i < size; i++) {
                this.elements[i] = this.elements[i + 1];
            }
            this.elements[size] = null;
            size--;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object elem: c){
            if (!contains(elem)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean isDone = false;
        for (T elem: c){
            if (add(elem)){
                isDone = true;
            }
        }
        return isDone;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isDone = false;
        for (Object elem: c){
            if (remove(elem)){
                isDone = true;
            }
        }
        return isDone;
    }

    @Override
    public void clear() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }
}
