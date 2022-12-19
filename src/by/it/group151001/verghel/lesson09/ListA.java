package by.it.group151001.verghel.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListA<T> implements List<T> {

   public class MyList{
       T data;
       MyList next;

       MyList(T data, MyList next) {
           this.data = data;
           this.next = next;
       }
   }

   private MyList head = new MyList(null,null);

    @Override
    public boolean add(T t) {

        MyList temp = head.next;
        MyList elem = new MyList(t,null);

        if (head.next==null)
            head.next=elem;
        else{
            while (temp.next != null)
                temp = temp.next;
            temp.next = elem;
        }

        return true;
    }

    @Override
    public T remove(int index) {

        MyList temp = head.next;
        int count = 0;

        while (temp != null && count < index - 1) {
            temp = temp.next;
            count++;
        }

        if (temp == null)
            return null;
        else {
            MyList deleted;
            if (temp == head.next) {
                deleted = temp;
                head.next = temp.next;
            }
            else {
                deleted = temp.next;
                temp.next = temp.next.next;
            }

            return deleted.data;
        }
    }

    @Override
    public T get(int index) {

        int count = 0;
        MyList temp = head.next;

        while (temp.next != null && count < index){
            temp = temp.next;
            index++;
        }
        if (temp == null)
            return null;
        else
            return temp.data;
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        MyList temp = head.next;

        res.append("[");
        while (temp.next != null) {
            res.append(temp.data).append(", ");
            temp = temp.next;
        }
        res.append(temp.data).append("]");

        return res.toString();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean remove(Object o) {
        return false;
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
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

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
