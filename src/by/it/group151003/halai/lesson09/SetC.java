package by.it.group151003.halai.lesson09;


import java.util.Collection;
import java.util.Iterator;
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

    @Override
    public boolean add(T t) {
        if (!contains(t)) {
            Node temp = head;
            Node newNode = new Node(t, null);
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        Node temp = head;
        while (temp.next != null && temp.next.data != o) {
            temp = temp.next;
        }
        if (temp.next == null) {
            return false;
        } else {
            temp.next = temp.next.next;
            return true;
        }
    }

    @Override
    public int size() {
        int counter = 0;
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
            counter++;
        }
        return counter;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        Node temp = head.next;
        while (temp != null) {
            str.append(temp.data).append(", ");
            temp = temp.next;
        }
        if (str.length() > 2) {
            str.delete(str.length() - 2, str.length());
        }
        str.append("]");
        return str.toString();
    }

    @Override
    public boolean isEmpty() {
        return head.next == null;
    }

    @Override
    public boolean contains(Object o) {
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.data == o) {
                return true;
            }
            if (o != null && temp.next.data != null) {
                if (temp.next.data.equals(o)){
                    return true;
                }
            }
            temp = temp.next;
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
    public boolean containsAll(Collection<?> c) {
        boolean res;
        for(Object t : c) {
            res = contains(t);
            if (!res){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean res = true;
        for(T t : c) {
            res = add(t);
        }
        return res;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = true;
        for(Object t : c) {
            res = remove(t);
        }
        return res;
    }

    @Override
    public void clear() {
        head.next = null;
    }
}
