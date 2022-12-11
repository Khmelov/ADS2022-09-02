package by.it.group151003.yagnish.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class SetC<T> implements Set<T> {
    private class Node {
        Object data;
        Node next;

        Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
    private Node head = new Node(null, null);
    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;

    }

    @Override
    public boolean contains(Object o) {
        Node tmp = head;
        if (o ==tmp.data)
            return true;
        while (tmp.next != null){
            if ((tmp.next.data==null&&o==null)|| Objects.equals(tmp.next.data, o))
                return true;
            else
                tmp = tmp.next;
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
        if(!contains(t)) {
            Node tmp = head;
            if (size == 0) {
                tmp.data = t;
            } else {
                while (tmp.next != null)
                    tmp = tmp.next;
                tmp.next = new Node(t, null);
            }
            size++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
       if(contains(o)) {
           Node tmp = head;
           if (head.data == o) {
               head = tmp.next;
               return true;
           }
           if (size == 0)
               return false;
           for (int i = 0; i < size - 1; i++) {
               if (Objects.equals(tmp.next.data, o))
                   break;
               tmp = tmp.next;
           }
           tmp.next = tmp.next.next;
           size--;
           return true;
       }
       return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object t:c)
            if (!contains(t))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
       Node tmp = head;
        for (int i = 0; i < size-1; i++)
            tmp =tmp.next;
        for (T t : c) {
            if (!contains(t)){
            tmp.next = new Node(t, null);
            tmp = tmp.next;
            size++;
            }
        }
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
        head = new Node(null, null);
        size=0;
    }
    public String toString (){
        StringBuilder res = new StringBuilder();
        Node tmp = head;
        res.append('[');
        for (int i=0; i<size-1;i++){
            res.append(tmp.data).append(',').append(' ');
            tmp = tmp.next;
        }
        res.append(tmp.data).append(']');
        return res.toString();
    }
}
