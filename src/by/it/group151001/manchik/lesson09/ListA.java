package by.it.group151001.manchik.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListA<T> implements List<T>{

    public static void main(String[] argv){
        ListA<Integer> list = new ListA<>();

        list.add(8);
        System.out.printf("%s",list.toString());
    }

    private TNode head;
    private TNode prev;
    private int size;
    ListA(){
        this.head = new TNode();
        this.prev = this.head;
    }
    private class TNode{
        T value;
        TNode next;
        TNode prev;

        public TNode(T value){
           this.value = value;
        }
        TNode(){
            this.next = null;
            this.prev = null;
        }
    }

    @Override
    public int size() {
        return size;
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
        TNode item = new TNode(t);

        item.next = prev.next;
        item.prev = prev;
        prev.next = item;
        prev = item;
        size++;
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
    public T get(int index) {
        if (index < 0 || index >= size()) return null;
        else {
            TNode cur = head.next;
            int i = 0;
            while (i != index) {
                i++;
                cur = cur.next;
            }
            return cur.value;
        }
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
        if (index < 0 || index >= size()) return null;
        else {
            TNode cur = head.next;
            int i = 0;
            while (i != index){
                i++;
                cur = cur.next;
            }
            this.size--;
            if (cur.next != null) cur.next.prev = cur.prev;
            else prev = cur.prev;
            cur.prev.next = cur.next;
            return cur.value;
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

    public String toString(){
        StringBuilder res = new StringBuilder();
        TNode current;

        if (size == 0) return "[]";

        res.append("[");
        current = head.next;
        while (current != null){
            res.append(current.value).append(", ");
            current = current.next;
        }
        res.delete(res.length() - 2,res.length());
        res.append("]");
        return res.toString();
    }
}