package by.it.group151001.kononchuk.lesson09;

import com.sun.jdi.ShortValue;

import java.util.*;

public class ListB <T> implements List<T>{


    protected Node<T> head, tail;

    public ListB(){
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
        int size = 0;
        Node<T> temp = head;

        while (temp.next != null) {
            temp = temp.next;
            size++;
        }

        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        Node<T> temp = head;

        while (temp.next != null){
            temp = temp.next;

            if (temp.data == null){
                if (o == null)
                    return  true;
            }
            else {
                if (temp.data.equals((Integer)o)){
                    return true;
                }
            }
        }
        return  false;
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
        Node<T> temp = head;
        boolean isFounded = false;
        while (temp.next != null){
            temp = temp.next;
            if (temp.data != null){
                if (temp.data.equals((Integer)o)){
                    isFounded = true;
                    break;
                }
            }
            else {
                if (o == null){
                    isFounded = true;
                    break;
                }
            }
        }

        if (isFounded){
            temp.prev.next = temp.next;
            if (temp != tail){
                temp.next.prev = temp.prev;
            }
            else {
                tail = temp.prev;
                tail.next = null;
            }
            return true;
        }
        else
            return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (var elem:c) {
            Node<T> temp = new Node<>(elem, tail, null);
            tail.next = temp;
            tail = temp;
        }
        return  true;
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
        tail = head;
        head.next = null;
    }

    @Override
    public T get(int index){
        return findIndex(index).data;
    }

    @Override
    public T set(int index, T element) {
        Node<T> founded = findIndex(index);

        if (founded == null){
            return null;
        }
        T temp = founded.data;
        founded.data = element;
        return  temp;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0){
            return;
        }
        else if (index == size()){
            Node<T> temp = new Node<T>(element, tail, null);
            tail.next = temp;
            tail = temp;
        } else {
            Node<T> founded = findIndex(index);
            if (founded != null) {
                Node<T> temp = new Node<T>(element, founded.prev, founded);
                founded.prev.next = temp;
                founded.next.prev = temp;
            }
        }
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
            result.append(temp.toString());

            if (temp.next != null){
                result.append(", ");
            }
        }
        result.append(']');
        return result.toString();
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(null);
        list.add(12);
        list.add(1, 0);
        list.add(78);

        System.out.println(list.toString());
    }
}