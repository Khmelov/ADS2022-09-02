package by.it.group151003.barilko.lesson09;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.StringJoiner;
import java.util.Vector;

public class SetC<TObject extends Comparable<TObject>> implements Set
{
    private class Node implements Comparable<Node>
    {
        public Node(TObject val) { this.val = val; state = true;}
        public TObject val;
        public boolean state;

        public int compareTo(Node other)
        {
            return  (this.val).compareTo(other.val);
        }
        public String toString()
        {
            if(val != null)
                return val.toString();
            else    
                return "null";
        }

    };

    private class compareByVal implements Comparator<Node>
    {
        @Override
        public int compare(SetC<TObject>.Node o1, SetC<TObject>.Node o2) 
        {
            if (o1 == null && o2 == null)
                return 0;
            if (o1 == null && o2 != null)
                return 1;
            if (o1 != null && o2 == null)
                return -1;

            if(o1.val == null && o2.val == null)
                return 0;
            if(o1.val == null && o2.val != null)
                return -1;
            if(o1.val != null && o2.val == null)
                return 1;

            return o1.compareTo(o2);
        }
        
    }
    private Vector<Node> storage;

    private int HashFuncTemplate(String key, int uMaxIndx, int coef)
    {
        int Result = 0;

        for (int i = 1; i < key.length() + 1; ++i)
        {
            int temp = coef * i * (int)(key.charAt(i - 1));
            Result = (Result + temp) % uMaxIndx;
        }
        Result = (Result * 2 + 1) % uMaxIndx;
        return Result;
    }
    
    private int HashFunc1(String key, int uMaxIndx)
    {
        return HashFuncTemplate(key, uMaxIndx, uMaxIndx + 1);
    }
    private int HashFunc2(String key, int uMaxIndx)
    {
        return HashFuncTemplate(key, uMaxIndx, uMaxIndx - 1);
    }

    public SetC()
    {
        uBuffSize = c_uDefSize;
        uSize = 0;
        uNonNullSize = 0;
    
        storage = new Vector<Node>(uBuffSize);
        storage.setSize(uBuffSize);
        Collections.fill(storage, null);
    }
    private boolean Add(String key, TObject obj)
    {
        if(uSize + 1 > (int)(c_fResizeCoef * uBuffSize))
            Resize();
        if(uNonNullSize > 2 * uSize)
            Rehash();
        int hash1 = HashFunc1(key, uBuffSize);
        int hash2 = HashFunc2(key, uBuffSize);
    
        int uFirstDeleted = uBuffSize;
        for(int i = 0; storage.get(hash1) != null && i < uBuffSize; ++i)
        {
            boolean nullTest = (storage.get(hash1).val == null && obj == null);
            if(storage.get(hash1).state && Objects.equals(obj, storage.get(hash1).val))
                return false;
            if(!storage.get(hash1).state)
            {
                uFirstDeleted = hash1;
                break;
            }
            hash1 += hash2;
            hash1 %= uBuffSize;
        }
        if(uFirstDeleted == uBuffSize)
        {
            storage.set(hash1, new Node(obj));
            ++uNonNullSize;
    
        }
        else
        {
            storage.get(uFirstDeleted).val = obj;
            storage.get(uFirstDeleted).state = true;
        }
        ++uSize;
        return true;
    }
    private boolean Delete(Object o)
    {
        String key = o == null ? "null" : o.toString();
        int h1 = HashFunc1(key, uBuffSize);
        int h2 = HashFunc2(key, uBuffSize);
    
        for (int i = 0; storage.get(h1) != null && i < uBuffSize; ++i)
        {
            if (storage.get(h1).state && Objects.equals(storage.get(h1).val, o))
            {
                storage.get(h1).state = false;
                --uSize;
                return true;
            }
            h1 += h2;
            h1 %= uBuffSize;
        }
        return false;
    }
    private TObject Get(String key)
    {
        int h1 = HashFunc1(key, uBuffSize);
        int h2 = HashFunc2(key, uBuffSize);
    
        for (int i = 0; storage.get(h1) != null && i < uBuffSize; ++i)
        {
            if (storage.get(h1).state)
                return storage.get(h1).val;
            h1 += h2;
            h1 %= uBuffSize;
        }
        return null;
    }

    private static final int c_uDefSize = 8;
    private static final float c_fResizeCoef = 0.75f;
    private int uSize;
    private int uBuffSize;
    private int uNonNullSize;

    private void Resize()
    {
        uBuffSize *= 2;
        Rehash();
    }
    private void Rehash()
    {
        uNonNullSize = 0;
        uSize = 0;
        Vector<Node> temp = (Vector<Node>)storage.clone();

        storage.clear();
        storage.setSize(uBuffSize);

        for(Node elem : temp)
            if(elem != null && elem.state)
                add(elem.val);
    }


    public String toString()
    {
        Vector<Node> temp = (Vector<Node>)storage.clone();
        {
            compareByVal comp = new compareByVal();
            temp.sort(comp);
        }
        StringJoiner result = new StringJoiner(", ", "[", "]");
        for(Node elem : temp)
        {
            if(elem == null)
                break;
            result.add(elem.toString());
        }
        return result.toString();
    }


    @Override
    public int size() {
        return uSize;
    }

    @Override
    public boolean isEmpty() {
        return uSize == 0;
    }

    @Override
    public boolean contains(Object o) 
    {
        String key = o == null ? "null" : o.toString();

        int h1 = HashFunc1(key, uBuffSize);
        int h2 = HashFunc2(key, uBuffSize);
    
        for (int i = 0; storage.get(h1) != null && i < uBuffSize; ++i)
        {
            if (storage.get(h1).state && Objects.equals(storage.get(h1).val, o))
                return true;
            h1 += h2;
            h1 %= uBuffSize;
        }
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
    public boolean add(Object e) 
    {
        if(e != null)  
            Add(e.toString(), (TObject)e);
        else
            Add("null", (TObject)e);
        return true;
    }

    @Override
    public boolean remove(Object o) 
    {
        return Delete(o);
    }

    @Override
    public boolean containsAll(Collection c) {
        boolean result = true;
        for(Object elem : c)
        {
            result = this.contains(elem);
            if(!result)
                break;    
        }
        return result;
    }

    @Override
    public boolean addAll(Collection c) {
        boolean changed = false;
        
        for(Object elem : c)
        {
            if(!changed)
                changed = this.add(elem);
            else
                this.add(elem);
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection c) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        for(Object elem : c)
            this.remove(elem);
        return true;
    }

    @Override
    public void clear() 
    {
        uSize = 0;
        uNonNullSize = 0;
        uBuffSize = c_uDefSize;

        storage.clear();
        storage.setSize(uBuffSize);
        
        
    }
    


}
