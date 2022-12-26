package by.it.group151003.barilko.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.StringJoiner;

public class ListA<tVal> implements List
{
    //Internal stuff
    private int size;
    private class Node
    {
        public Node() { this.val = null; next = null; prev = null;}
        public tVal val;
        public Node next;
        public Node prev;
    }    
    private Node Head = new Node();
    private Node LastCreated = Head;

    public Object set(int indx, Object val)
    {
        return null;
    }

    public boolean retainAll(Collection col)
    {
        return false;
    }

    public boolean contains(Object val)
    {
        return false;
    }

    public void clear()
    {
        return;
    }

    public Iterator iterator()
    {
        return null;
    }


    //Methods
    public boolean add(Object val)
    {
        Node Temp = LastCreated;
        LastCreated.next = new Node();
        LastCreated = LastCreated.next;
        LastCreated.prev = Temp;
        LastCreated.val = (tVal)val;
        ++size;
        return true;
    }
    public tVal remove(int index)
    {
        tVal result = null;
        if(index >= 0 && index < size)
        {
            Node Curr = Head.next;
            while(Curr != null && index > 0)
            {
                Curr = Curr.next;
                --index;
            }
            if(Curr != null)
            {
                result = Curr.val;
                Node prev = Curr.prev;
                prev.next = Curr.next;
                if(Curr.next != null)
                    Curr.next.prev = prev;
                else
                    LastCreated = Curr.prev;
            }
        }
        return result;
    }
    public tVal get(int index)
    {
        if(index >= 0 && index < size)
        {
            Node Curr = Head.next;
            while(Curr != null && index > 0)
            {
                Curr = Curr.next;
                --index;
            }
            if(Curr != null)
                return Curr.val;
        }
        return null;
    }

    public String toString()
    {
        StringJoiner result = new StringJoiner(", ", "[", "]");
        Node Curr = Head.next;

        while(Curr != null)
        {
            result.add(Curr.val.toString());
            Curr = Curr.next;
        }
        return result.toString();
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Object[] toArray() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Object[] toArray(Object[] a) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean remove(Object o) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void add(int index, Object element) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int indexOf(Object o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        // TODO Auto-generated method stub
        return null;
    }
}

