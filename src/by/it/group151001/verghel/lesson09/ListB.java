package by.it.group151001.verghel.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;




public class ListB<T> implements List<T> {
    private class MyNode {
        T data;
        MyNode next;

            MyNode(T data, MyNode next) {
                this.data = data;
                this.next = next;
            }
        }
        private MyNode head = new MyNode(null,null);

        @Override
        public boolean add(T adding) {
            if(head.data == null){
                head = new MyNode(adding,null);
                return true;
            }
            MyNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new MyNode(adding, null);
            return true;
        }

        public MyNode findByIndex(int index){
            int count = 0;
            MyNode temp = head;
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
                MyNode buf = head;
                head = head.next;
                return buf.data;
            }
            MyNode prev = findByIndex(index-1);
            if (prev != null){
                MyNode buf = prev.next;
                prev.next = prev.next.next;
                return buf.data;
            }else return null;
        }

        @Override
        public T get(int index) {
            MyNode find = findByIndex(index);
            if(find != null){
                return find.data;
            } else return null;
        }

        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append('[');

            MyNode temp = head;
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
        public boolean addAll(Collection<? extends T> c) {
            MyNode temp = head;
            while (temp.next != null)
                temp = temp.next;

            for(T e : c) {
                MyNode new_My_node = new MyNode(e,null);
                temp.next = new_My_node;
                temp = temp.next;

            }

            return true;
        }

    @Override
    public T set(int index, T element) {
        MyNode temp = findByIndex(index);
        if(temp == null){
            return null;
        }
        T s = temp.data;
        temp.data = element;
        return s;
    }

    @Override
    public void add(int index, T element) {

        MyNode new_My_node = new MyNode(element, null);
        if(index==0){
            new_My_node.next = head;
            head = new_My_node;

        } else {
            MyNode prev = findByIndex(index - 1);
            new_My_node.next = prev.next;
            prev.next = new_My_node;
        }
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

