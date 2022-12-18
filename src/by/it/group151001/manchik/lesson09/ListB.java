package by.it.group151001.manchik.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<T> implements List<T>{

    public static void main(String[] argv){
        ListB<Integer> list = new ListB<>();

        list.add(0,0);
        list.add(1,0);
        list.remove(1);
        list.add(1,999);
        System.out.printf("%s",list.toString());
    }

    private TNode head;
    private TNode prev;
    private int size;
    ListB(){
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
        TNode cur = head.next;
        while (cur != null && cur.value != o){
            cur = cur.next;
        }
        if (cur == null) return false;
        return true;
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
        this.size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        TNode cur = head.next;
        while (cur != null && cur.value != o){
            cur = cur.next;
        }
        if (cur != null) {
            this.size--;
            if (cur.next != null) cur.next.prev = cur.prev;
            else prev = cur.prev;
            cur.prev.next = cur.next;
            return true;
        } else return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T item :c) {
            this.add(item);
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
        if (index < 0 || index >= size()) return null;
        else {
            TNode cur = head.next;
            T prev_value;
            int i = 0;
            while (i != index) {
                i++;
                cur = cur.next;
            }
            prev_value = cur.value;
            cur.value = element;
            return prev_value;
        }
    }

    @Override
    public void add(int index, T element) {
        if (index >= 0 && index < size()) {
            TNode cur = head.next;
            int i = 0;
            while (i != index) {
                i++;
                cur = cur.next;
            }
            TNode inserter = new TNode(element);

            inserter.next = cur;
            inserter.prev = cur.prev;
            cur.prev.next = inserter;
            cur.prev = inserter;
            this.size++;
        }
        else if (index == size()) add(element);
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
        TNode cur = head.next;
        int index = 0;
        while (cur != null && cur.value != o){
            index++;
            cur = cur.next;
        }
        if (cur == null) return -1;
        return index;
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

        if (this.size == 0) return "[]";

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