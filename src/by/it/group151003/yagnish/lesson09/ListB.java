package by.it.group151003.yagnish.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<T> implements List<T> {

    private class Node {
        T data;
        Node next;

        Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
    private Node head = new Node(null,null);
    private int length = 0;



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
    public boolean add(T e) {
        Node tmp = head;
        if (length == 0){
            tmp.data = e;
        }
        else{
            while(tmp.next != null)
                tmp = tmp.next;
            tmp.next = new Node(e,null);
        }
        length++;
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
        Node tmp = head;
        for (int i = 0; i < length-1; i++)
            tmp =tmp.next;
        for (T t : c) {
            tmp.next = new Node(t, null);
            tmp = tmp.next;
        }
        length+=c.size();
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
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
        }
        Node tmp = head;
        for (int i = 0; i < index; i++)
            tmp = tmp.next;
        return tmp.data;
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
        }
        if (index ==0){
            T tmp = head.data;
            head.data = element;
            return tmp;
        }
        else {
            Node tmp = head;
            for (int i = 0; i < index - 1; i++)
                tmp = tmp.next;
            T res = tmp.next.data;
            tmp.next.data = element;
            return res;
        }
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
        }
        else {
            if (index == 0)
                head = new Node(element, head);
            else {
                Node tmp = head;
                for (int i = 0; i < index - 1; i++)
                    tmp = tmp.next;
                tmp.next = new Node(element, tmp.next);
            }
            length++;
        }
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
        }
        Node tmp = head;
        if (length==0)
            return null;
        else if (index ==0) {
            head = tmp.next;
            length--;
            return tmp.data;
        }
        else{
            for (int i = 0; i < index-1; i++)
                tmp = tmp.next;
            T res = tmp.next.data;
            tmp.next = tmp.next.next;
            length--;
            return res;
        }

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

    public String toString (){
        StringBuilder res = new StringBuilder();
        Node tmp = head;
        res.append('[');
        for (int i=0; i<length-1;i++){
            res.append(tmp.data).append(',').append(' ');
            tmp = tmp.next;
        }
        res.append(tmp.data).append(']');
        return res.toString();
    }

}
