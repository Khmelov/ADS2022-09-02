package by.it.group151002.strukov.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListB<T> implements List<T> {
    private class ListNode {
        T data;
        ListNode next;

        ListNode(T data, ListNode next) {
            this.data = data;
            this.next = next;
        }
    }

    private ListNode head = new ListNode(null, null);

    @Override
    public int size() {
        int res = 0;
        ListNode curr = head.next;
        while (curr != null)
        {
            res++;
            curr = curr.next;
        }

        return res;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        ListNode curr = head.next;
        boolean isFound = false;
        while (curr != null && curr.data != o)
        {
            curr = curr.next;
        }

        if (curr != null)
        {
            return true;
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
        ListNode curr = head.next;
        ListNode elem = new ListNode(t, null);
        if (head.next == null)
        {
            head.next = elem;
        }
        else
        {
            while (curr.next != null) {
                curr = curr.next;
            }

            curr.next = elem;
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);
        if (index != -1)
        {
            T data = remove(index);
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        ListNode curr = head;
        while (curr.next != null)
        {
            curr = curr.next;
        }

        for (T item : c)
        {
            ListNode elem = new ListNode(item, null);
            curr.next = elem;
            curr = curr.next;
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
        int counter = 0;
        ListNode curr = head.next;
        while (curr != null && counter < index)
        {
            curr = curr.next;
            counter++;
        }

        if (curr == null)
        {
            return null;
        }
        else
        {
            return curr.data;
        }
    }

    @Override
    public T set(int index, T element) {
        int counter = 0;
        ListNode curr = head.next;
        while (curr != null && counter < index)
        {
            curr = curr.next;
            counter++;
        }

        if (curr == null)
        {
            return null;
        }
        else
        {
            T res = curr.data;
            curr.data = element;
            return res;
        }
    }

    @Override
    public void add(int index, T element) {
        ListNode curr = head.next;
        int counter = 0;
        while (curr != null && counter < index - 1)
        {
            curr = curr.next;
            counter++;
        }

        if (curr != null)
        {
            if (curr != head.next)
            {
                ListNode temp = curr.next;
                curr.next = new ListNode(element, temp);
            }
            else
            {
                head.next = new ListNode(element, head.next);
            }

        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        ListNode curr = head.next;
        res.append("[");
        while (curr.next != null) {
            res.append(curr.data).append(", ");
            curr = curr.next;
        }
        res.append(curr.data).append("]");
        return res.toString();
    }

    @Override
    public T remove(int index) {
        ListNode curr = head.next;
        int counter = 0;
        while (curr != null && counter < index - 1)
        {
            curr = curr.next;
            counter++;
        }

        if (curr == null)
        {
            return null;
        }
        else
        {
            ListNode deleted;
            if (curr == head.next)
            {
                deleted = curr;
                head.next = curr.next;
            }
            else
            {
                deleted = curr.next;
                curr.next = curr.next.next;
            }

            return deleted.data;
        }
    }

    @Override
    public int indexOf(Object o) {
        int res = 0;
        ListNode curr = head.next;
        while (curr != null && curr.data != o)
        {
            res++;
            curr = curr.next;
        }
        if (curr != null)
        {
            return res;
        }
        return -1;
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
