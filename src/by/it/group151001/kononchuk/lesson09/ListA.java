package by.it.group151001.kononchuk.lesson09;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListA <T> implements List<T> {

    protected Node<T> head, tail;

    public ListA(){
        this.head = this.tail = new Node<>();
    }

    private Node<T> findIndex(int index){
        int count = -1;
        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
            count++;
            if (count == index){
                return temp;
            }
        }
        return null;
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
    public boolean add(T e){
        Node<T> temp = new Node<T>(e, tail, null);
        tail.next = temp;
        tail = temp;
        if (head.next == null){
            head.next = temp;
        }
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
    public T get(int index){
        return findIndex(index).data;
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index){
        Node<T> founded = findIndex(index);

        if (founded != null){
            founded.prev.next = founded.next;
            if (founded != tail){
                founded.next.prev = founded.prev;
            }
            else {
                tail = founded.prev;
                tail.next = null;
            }
        }
        return founded.data;
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

    public String toString(){
        StringBuilder result = new StringBuilder();

        result.append('[');
        Node<T> temp = head;
        while (temp.next != null) {
            temp = temp.next;
            if (temp.data != null) {
                result.append(temp.toString());
            }
            else {
                result.append("null");
            }

            if (temp.next != null){
                result.append(", ");
            }
        }
        result.append(']');
        return result.toString();
    }
}