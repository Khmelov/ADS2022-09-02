package by.it.group151002.zaitseva.lesson09;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

class SetC<T> implements Set<T> {
    private final int INIT_SIZE = 16;
    private final int CUT_RATE = 4;
    private Object[] array = new Object[INIT_SIZE];
    private int pointer = 0;
    @Override
    public int size() {
        return pointer;
    }

    @Override
    public boolean isEmpty() {
        if (pointer==0)
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        boolean res=false;
        for (Object i:array)
        {
            if (i==o)
            {
                res=true;
                break;
            }
        }
        return res;
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
    public boolean add(T item) {
        if (!contains(item)) {
            if (pointer == array.length - 1)
                resize(array.length * 2);
            array[pointer++] = item;
            return true;
        }
        return false;
    }

    public int find(Object o)
    {
        for (int i=0;i<pointer; i++)
        {
            if (o==array[i])
                return i;
        }
        return -1;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o))
        {
            return false;
        }
        if (find(o)==-1)
        {
            return false;
        }
        else
        {
            for (int i = find(o); i<pointer; i++)
                array[i] = array[i+1];
            array[pointer] = null;
            pointer--;
            return true;
        }
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean r=false,res=false;
        for (Object i:c)
        {
            r=contains(i);
            if (r)
            {
                res=true;
            }
        }
        return res;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean r=false,res=false;
        for (T i:c)
        {
            r=add(i);
            if (r)
            {
                res=true;
            }
        }
        return res;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean r=false,res=false;
        for (Object i:c)
        {
            r=remove(i);
            if (r)
            {
                res=true;
            }
        }
        return res;
    }

    @Override
    public void clear() {
        array=new Object[0];
        pointer=0;
    }
    private void resize(int newLength) {
        Object[] newArray = new Object[newLength];
        System.arraycopy(array, 0, newArray, 0, pointer);
        array = newArray;
    }
}
