package by.it.group151002.volontirov.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
public class ListB<T> implements List<T> {
    private Node<T> head;
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node<T> tmp = head;
        do{
            builder.append(tmp.value());
            builder.append(", ");
            tmp = tmp.next();
        }while(tmp != null);
        builder.delete(builder.length() - 2, builder.length());
        builder.append("]");
        return builder.toString();
    }
    ListB(){
        head = null;
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
    public boolean add(T t) {
        if(head == null){
            head = new Node(t);
            return true;
        } else {
            Node tmp = head;
            while(tmp.next() != null){
                tmp = tmp.next();
            }
            tmp.setNext(new Node(t));
            return true;
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
        for(T i : c){
            this.add(i);
        }
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
        Node<T> tmp = head;
        while(index != 0){
            tmp = tmp.next();
            index--;
        }
        return tmp.value();
    }

    @Override
    public T set(int index, T element) {
        Node<T> tmp = head;
        while(index != 0) {
            tmp = tmp.next();
            index--;
        }
        T oldValue = tmp.value();
        tmp.setValue(element);
        return oldValue;
    }

    @Override
    public void add(int index, T element) {
        Node<T> tmp = head;
        if(index == 0) {
            head = new Node(element);
            head.setNext(tmp);
            return;
        }
        while(index != 1){
            tmp = tmp.next();
            index--;
        }
        tmp.setNext(new Node(element, tmp.next()));
    }

    @Override
    public T remove(int index) {
        Node<T> tmp = head;
        Node<T> prev = null;
        while(index != 0){
            prev = tmp;
            tmp = tmp.next();
            index--;
        }
        if(tmp == head){
            head = head.next();
        } else {
            prev.setNext(tmp.next());
        }
        return tmp.value();
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
