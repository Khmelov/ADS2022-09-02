package by.it.group151001.danko.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T> {


    private class ListElement {
        Object value;
        ListElement link;

        ListElement(Object value, ListElement link)
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
        if(size() == 0) return true;
        else return false;
    }

    @Override
    public boolean contains(Object o) {
        ListElement current = head;
        while (current.link != null) {
            if (current.link.value == o) {
                return true;
            }
            if (o != null && current.link.value != null) {
                if (current.link.value.equals(o)){
                    return true;
                }
            }
            current = current.link;
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
    public boolean add(T e) {
        if(contains(e)) {
            return false;
        }
        else
        {
            ListElement current = head;
            ListElement additional = new ListElement(e, null);
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
    }

    @Override
    public boolean remove(Object o) {
        ListElement current = head;
        while(current.link != null && current.link.value != o)
        {
            current = current.link;
        }
        if(current.link == null) return false;

            current.link = current.link.link;
            return true;

    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object e : c)
        {
            if(!contains(e))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean Res = true;
        for(T e : c) {
            Res = add(e);
        }
        return Res;
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
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean comp = true;
        for(Object e: c) comp = remove(e);
        return comp;
    }

    @Override
    public void clear() {
        ListElement current = head;
        while(current.link != null)
        {
            current = current.link;
            remove(current.value);
        }
    }
}