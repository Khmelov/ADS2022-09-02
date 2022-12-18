package by.it.group151001.danko.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<T> implements List<T> {

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
        int i = 0;
        ListElement current = head.link;
        while(current != null)
        {
            current = current.link;
            i++;
        }
        return i;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        ListElement current = head.link;
        while(current != null && current.value != o)
            current = current.link;
        if(current != null) return true;
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
        ListElement current = head.link;
        while(current.link != null)
        {
            current = current.link;
        }
        for(T item : c)
        {
            ListElement temp = new ListElement(item, null);
            current.link = temp;
            current = current.link;
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
        int i = 0;
        ListElement current = head.link;
        while(current != null && i != index)
        {
            i++;
            current = current.link;
        }
        if(current != null){
            T result = current.value;
            current.value = element;
            return result;
        }
        return null;
    }

    @Override
    public void add(int index, T element) {
        ListElement current = head.link;
        int i = 0;
        if(head.link == null) return;
        while(current != null && i < index - 1)
        {
            current = current.link;
            i++;
        }
        if(current == null) return;
        if(i != 0)
        {
           ListElement temp = current.link;
           ListElement additional = new ListElement(element, temp);
           current.link = additional;
        }
        else
        {
            ListElement additional = new ListElement(element, current);
            head.link = additional;
        }
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
        ListElement current = head.link;
        int i = 0;
        while(current != null && current.value != o) {
            current = current.link;
            i++;
        }
        if(current != null) return i;
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
