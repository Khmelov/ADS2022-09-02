package by.it.group151001.loziuk.lesson10;

import java.util.*;

public class TaskA<E>  implements NavigableSet<E> {
    public int size = 0;
    public Node<E> root;
    public int compareTo(E o1, E o2)
    {
        return Integer.compare((int)o1, (int)o2);
    }

    public class Node<E>
    {
        public E data;
        public Node<E> left;
        public Node<E> right;
        public Node<E> parent;

        public Node(E data)
        {
            this.data = data;
        }
    }

    @Override
    public boolean add(E key) {
        Node<E> curr = root;
        Node<E> elem = new Node<>(key);
        if (!contains(key))
        {
            if (root == null)
            {
                root = new Node(key);
                root.parent = null;
                size++;
                return true;
            }
            else
            {
                boolean isInserted = false;
                while (!isInserted)
                {
                    if (compareTo(curr.data, key) > 0)
                    {
                        if (curr.left == null)
                        {
                            curr.left = elem;
                            curr.left.parent = curr;
                            size++;
                            return true;
                        }

                        curr = curr.left;
                    }
                    else
                    {
                        if (curr.right == null)
                        {
                            curr.right = elem;
                            curr.right.parent = curr;
                            size++;
                            return true;
                        }

                        curr = curr.right;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> cur = root;
        boolean flag = false;
        while(!flag && size > 0){
            if(cur == null) return false;
            if(cur != null && compareTo(cur.data, (E) o) > 0){
                cur = cur.left;
            } else
            if(cur != null && compareTo(cur.data,(E)o) < 0){
                cur = cur.right;
            }
            if(cur != null && compareTo(cur.data,(E)o)==0){
                flag = true;
            }
        }
        if(cur.left == null && cur.right == null){
            if(cur.parent==null){
                root=null;
            } else
            if(cur.parent.right==cur){
                cur.parent.right=null;
            }else {
                cur.parent.left = null;
            }
            size--;
            return true;
        }
        if(cur.left==null || cur.right==null){
            if(cur.left==null){
                if(cur.parent==null){
                    root=cur.right;
                } else
                if(cur.parent.left==cur){
                    cur.right.parent=cur.parent;
                    cur.parent.left=cur.right;
                } else
                if(cur.parent.right==cur){
                    cur.right.parent=cur.parent;
                    cur.parent.right = cur.right;
                }
            }
            if(cur.right==null){
                if(cur.parent==null){
                    root=cur.left;
                } else
                if(cur.parent!=null && cur.parent.right==cur){
                    cur.left.parent=cur.parent;
                    cur.parent.right=cur.left;
                } else
                if(cur.parent!=null && cur.parent.left==cur){
                    cur.left.parent=cur.parent;
                    cur.parent.left = cur.left;
                }
            }
            size--;
            return true;
        }
        if(cur.left!=null && cur.right!=null){
            Node<E> tmp;
            tmp=cur.right;
            boolean flag1=false;
            while(tmp.left!=null){
                tmp=tmp.left;
                flag1=true;
            }
            if(flag1){
                if(tmp.right!=null){
                    tmp.right.parent=tmp.parent;
                    tmp.parent.left=tmp.right;
                    cur.data=tmp.data;
                }
                if(tmp.right==null){
                    tmp.parent.left=null;
                    cur.data=tmp.data;
                }
                size--;
                return true;
            }
            else if(!flag1){
                if(tmp.right!=null) {
                    tmp.right.parent = tmp.parent;
                    tmp.parent.right = tmp.right;
                    cur.data = tmp.data;
                }
                if(tmp.right==null){
                    tmp.parent.right=null;
                    cur.data=tmp.data;
                }
                size--;
                return true;
            }
            size--;
            return true;
        }

        return false;
    }


    void LRB(Node<E> root, StringBuilder res)
    {
        if (root != null)
        {
            LRB(root.left, res);
            res.append(root.data);
            res.append(", ");
            LRB(root.right, res);
        }

    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        LRB(root, res);
        if (res.length() < 2)
        {
            res.append(']');
        }
        else
        {
            res.delete(res.length() - 2, res.length());
            res.append("]");
        }

        return res.toString();
    }
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////         Эти методы реализовывать необязательно      ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////


    @Override
    public boolean contains(Object o) {
        Node<E> curr = root;
        boolean isFound = false;
        while (size >= 1 && isFound == false)
        {
            if (curr == null)
            {
                isFound = false;
                break;
            }
            else if (curr != null && compareTo(curr.data, (E) o) > 0)
            {
                curr = curr.left;
            }
            else if (curr != null && compareTo(curr.data, (E) o) < 0)
            {
                curr = curr.right;
            }
            else if (curr != null && compareTo(curr.data, (E) o) == 0)
            {
                isFound = true;
            }
        }

        return isFound;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        if (size > 0)
        {
            return false;
        }

        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E lower(E e) {
        return null;
    }

    @Override
    public E floor(E e) {
        return null;
    }

    @Override
    public E ceiling(E e) {
        return null;
    }

    @Override
    public E higher(E e) {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public NavigableSet<E> descendingSet() {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return null;
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        return null;
    }

    @Override
    public Comparator<? super E> comparator() {
        return null;
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return null;
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return null;
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return null;
    }

    @Override
    public E first() {
        if (size > 0)
        {
            Node<E> curr = root;
            while (curr.left != null)
            {
                curr = curr.left;
            }
            return curr.data;
        }
        else
        {
            return null;
        }
    }

    @Override
    public E last() {
        if (size > 0)
        {
            Node<E> curr = root;
            while (curr.right != null)
            {
                curr = curr.right;
            }
            return curr.data;
        }
        else
        {
            return null;
        }
    }
}

