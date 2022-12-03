package by.it.group151001.danko.lesson09;

import java.lang.annotation.ElementType;
import java.util.*;

public class ListA<T> implements List<T> {

    private class ListElement {
        T value;
        ListElement link;

        ListElement(T value, ListElement link)
        {
            this.value = value;
            this.link = link;
        }
    }
    private ListElement head = new ListElement(null, null);

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
        ListElement current = head.link;
        ListElement additional = new ListElement(t, null);
        if(head.link == null)
        {
            head.link = additional;
        }
        else {
            while(current.link != null) current = current.link;
            current.link = additional;
        }
        return true;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        ListElement el = head.link;
        if(el == null) return "[]";
        sb.append('[');
        for(;;)
        {
            sb.append(el.value);
            if(el.link == null) return sb.append(']').toString();
            sb.append(", ");
            el = el.link;
        }
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
        return false;
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
        int i = 0;
        ListElement current = head.link;
        while(current != null)
        {
            if(i == index) return current.value;
            i++;
            current = current.link;
        }
        assert(false);
        return null;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        int i = 0;
        ListElement temp = head.link;
        ListElement prev = head;
        ListElement deleted;
        if(temp == null) return null;
        while(temp != null && i != index)
        {
            prev = temp;
            temp = temp.link;
            i++;
        }
        if(temp == null) return null;
        if(temp == head.link)
        {
            deleted = temp;
            head.link = temp.link;
        }
        else
        {
            deleted = temp;
            prev.link = temp.link;
        }
        return deleted.value;
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
