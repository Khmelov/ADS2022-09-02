package by.it.group151001.milkovskaya.lesson09;


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

    @Override
    public boolean add(T adding) {
        if(head.data == null){
            head = new Node(adding,null);
            return true;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(adding, null);
        return true;
    }

    public Node findByIndex(int index){
        int count = 0;
        Node temp = head;
        while (temp != null) {
            if (count == index){
                return temp;
            }
            temp = temp.next;
            count++;
        }
        return null;
    }

    public T remove(int index) {
        if(index == 0){
            Node buf = head;
            head = head.next;
            return buf.data;
        }
        Node prev = findByIndex(index-1);
        if (prev != null){
           Node buf = prev.next;
            prev.next = prev.next.next;
            return buf.data;
        }else return null;
    }

    @Override
    public T get(int index) {
        Node find = findByIndex(index);
        if(find != null){
            return find.data;
        } else return null;
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');

        Node temp = head;
        while (temp != null){
            res.append(temp.data);
            if (temp.next != null)
                res.append(", ");
            temp = temp.next;
        }

        res.append(']');

        return res.toString();
    }

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
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Node temp = head;
        while (temp.next != null)
            temp = temp.next;

        for(T e : c) {
            Node new_node = new Node(e,null);
            temp.next = new_node;
            temp = temp.next;

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
    public T set(int index, T element) {
        Node temp = findByIndex(index);
        if(temp == null){
            return null;
        }
        T s = temp.data;
        temp.data = element;
        return s;
    }

    @Override
    public void add(int index, T element) {

        Node new_node = new Node(element, null);
        if(index==0){
            new_node.next = head;
            head = new_node;

        } else {
            Node prev = findByIndex(index - 1);
            new_node.next = prev.next;
            prev.next = new_node;
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
}