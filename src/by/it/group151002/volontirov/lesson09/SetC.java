package by.it.group151002.volontirov.lesson09;

import javax.swing.text.html.HTMLDocument;
import java.util.*;
import java.util.stream.Stream;

public class SetC<T> implements Set<T> {
    private class Node<T>{
        private Node<T> next;
        private T value;
        Node(T value){
            this.value = value;
            this.next = null;
        }
        public T getValue() {
            return value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }
    private Node<T> head;
    SetC(){
        head = null;
    }
    public String toString(){
        if(head == null){
            return "[]";
        }
        StringBuilder builder = new StringBuilder("[");
        Node<T> tmp = head;
        while(tmp != null){
            builder.append(tmp.getValue());
            builder.append(", ");
            tmp = tmp.getNext();
        }
        builder.delete(builder.length() - 2, builder.length());
        builder.append("]");
        return builder.toString();
    }
    @Override
    public int size() {
        int size = 0;
        Node<T> tmp = head;
        while(tmp != null){
            size++;
            tmp = tmp.getNext();
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        if(size() == 0) return true;
        return false;
    }

    @Override
    public boolean contains(Object o) {
        Node<T> tmp = head;
        while(tmp != null){
            if(tmp.getValue() == null ? tmp.getValue() == o : tmp.getValue().equals(o)) return true;
            tmp = tmp.getNext();
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
        if(this.contains(t)){
            return false;
        }
        if(head == null){
            head = new Node<T>(t);
        } else {
            Node<T> tmp = head;
            while(tmp.getNext() != null){
                tmp = tmp.getNext();
            }
            tmp.setNext(new Node<T>(t));
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(!this.contains(o)){
            return false;
        }
        if(head.getValue().equals(o)){
            head = head.getNext();
        } else {
            Node<T> tmp = head;
            Node<T> prev = null;
            while(!(tmp.getValue() == null ? tmp.getValue() == o : tmp.getValue().equals(o))){
                prev = tmp;
                tmp = tmp.getNext();
            }
            prev.setNext(tmp.getNext());
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object i : c){
            if(!this.contains(i)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean changed = false;
        for(T i : c){
            changed |= this.add(i);
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for(Object i : c){
            changed |= this.remove(i);
        }
        return changed;
    }

    @Override
    public void clear() {
        this.head = null;
    }

    public static void main(String[] args) {
        SetC<Integer> set = new SetC();
        set.add(5);
        System.out.println(set.add(6));
        List<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(5);
        System.out.println(set.addAll(list));
        System.out.println(set.size());
    }
}
