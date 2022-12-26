package by.it.group151003.barilko.lesson09;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.StringJoiner;

public class ListB<tVal> implements List
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

    //private Methods

    private Node gotoNode(int index)
    {
        if((index >= 0) && (index <= size))
        {
            Node Curr = Head;
            while(Curr != null && index > 0)
            {
                Curr = Curr.next;
                --index;
            }
            return Curr;
        }
        return null;
    }


    //Methods
    public boolean add(Object val)
    {
        Node prev = gotoNode(size);
        prev.next = new Node();
        prev.next.prev = prev;
        prev.next.val = (tVal)val;
        ++size;
        return true;
    }

    public Object remove(int index)
    {
        Node prev = gotoNode(index);
        tVal temp = prev.next.val;
        if(prev != null)
        {
            if(prev.next.next != null)
            {
                prev.next.next.prev = prev;
            }
            else
                LastCreated = prev;
            prev.next = prev.next.next;
        }
        --size;
        return temp;
    }
    public tVal get(int index)
    {
        Node Curr = gotoNode(index + 1);
        return Curr.val;
    }

    public String toString()
    {
        StringJoiner result = new StringJoiner(", ", "[", "]");
        Node Curr = Head.next;

        while(Curr != null)
        {
            if(Curr.val != null)
                result.add(Curr.val.toString());
            else
                result.add("null");
            Curr = Curr.next;
        }
        return result.toString();
    }

    public Object set(int index, Object val)
    {
        Node Curr = gotoNode(index + 1);
        tVal prevVal = Curr.val;
        if(Curr != null)
            Curr.val = (tVal)val;
        return prevVal;
    }

    public void add(int index, Object val)
    {
        Node prev = gotoNode(index);
        if(prev != null)
        {
            Node temp = prev.next;
            prev.next = new Node();
            prev.next.prev = prev;
            prev.next.next = temp;
            prev.next.val = (tVal)val;
            ++size;
        }
    }

    public void add(List<tVal> list)
    {
        for(tVal val : list)
        {
            this.add(val);
        }
    }

    public void add(ListB<tVal> list)
    {
        Node Curr = list.Head.next;
        while(Curr != null)
        {
            this.add(Curr.val);
            Curr = Curr.next;
        }
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
    public boolean contains(Object o) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public Iterator iterator() {
        // TODO Auto-generated method stub
        return null;
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
    public boolean addAll(Collection list) 
    {  
        for(Object val : list)
        {
            this.add(val);
        }
        return true;
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
    public boolean retainAll(Collection c) {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public void clear() {
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

