package by.it.group151002.strukov.lesson10;

import java.util.*;

public class TaskA<E> implements NavigableSet<E> {
    public int size = 0;
    public Node<E> root;
    public int compareTo(E o1, E o2) {
        return Integer.compare((int)o1, (int)o2);
    }

    public class Node<E> {
        public E data;
        public Node<E> left;
        public Node<E> right;
        public Node<E> parent;

        public Node(E data)
        {
            this.data = data;
        }
    }
    //Обязательные к реализации методы и конструкторы

    @Override
    public boolean add(E e) {
        Node<E> curr = root;
        Node<E> elem = new Node<>(e);
        if (!contains(e))
        {
            if (root == null)
            {
                root = new Node(e);
//                root = elem;
                root.parent = null;
                size++;
                return true;
            }
            else
            {
                boolean isInserted = false;
                while (!isInserted)
                {
                    if (compareTo(curr.data, e) > 0)
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
        Node<E> curr = root;
        boolean flag = false;
        while(!flag && size > 0){
            if(curr == null)
                return false;
            if(curr != null && compareTo(curr.data, (E) o) > 0)
                curr=curr.left;
            else if(curr!=null && compareTo(curr.data,(E)o) < 0)
                curr=curr.right;
            if(curr!=null && compareTo(curr.data,(E)o)==0)
                flag = true;
        }
        if(curr.left==null && curr.right==null){
            if(curr.parent==null)
                root=null;
            else if(curr.parent.right==curr)
                curr.parent.right=null;
            else
                curr.parent.left = null;
            size--;
            return true;
        }
        if(curr.left==null || curr.right==null){
            if(curr.left==null){
                if(curr.parent==null)
                    root=curr.right;
                else if(curr.parent.left==curr){
                    curr.right.parent=curr.parent;
                    curr.parent.left=curr.right;
                }
                else if(curr.parent.right==curr){
                    curr.right.parent=curr.parent;
                    curr.parent.right = curr.right;
                }
            }
            if(curr.right==null){
                if(curr.parent==null)
                    root=curr.left;
                else if(curr.parent!=null && curr.parent.right==curr){
                    curr.left.parent=curr.parent;
                    curr.parent.right=curr.left;
                }
                else if(curr.parent!=null && curr.parent.left==curr){
                    curr.left.parent=curr.parent;
                    curr.parent.left = curr.left;
                }
            }
            size--;
            return true;
        }
        if(curr.left!=null && curr.right!=null){
            Node<E> tmp;
            tmp=curr.right;
            boolean flag1=false;
            while(tmp.left!=null){
                tmp=tmp.left;
                flag1=true;
            }
            if(flag1){
                if(tmp.right!=null){
                    tmp.right.parent=tmp.parent;
                    tmp.parent.left=tmp.right;
                    curr.data=tmp.data;
                }
                if(tmp.right==null){
                    tmp.parent.left=null;
                    curr.data=tmp.data;
                }
                size--;
                return true;
            }
            else if(!flag1){
                if(tmp.right!=null) {
                    tmp.right.parent = tmp.parent;
                    tmp.parent.right = tmp.right;
                    curr.data = tmp.data;
                }
                if(tmp.right==null){
                    tmp.parent.right=null;
                    curr.data=tmp.data;
                }
                size--;
                return true;
            }
            size--;
            return true;
        }

        return false;
    }

    void toOrder(Node<E> root, StringBuilder res)
    {
        if (root != null)
        {
            toOrder(root.left, res);
            res.append(root.data);
            res.append(", ");
            toOrder(root.right, res);
        }

    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        toOrder(root, res);
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
        if (size > 0) {
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
        if (size > 0) {
            Node<E> curr = root;
            while (curr.left != null)
                curr = curr.left;
            return curr.data;
        }
        else
            return null;
    }

    @Override
    public E last() {
        if (size > 0) {
            Node<E> curr = root;
            while (curr.right != null)
                curr = curr.right;
            return curr.data;
        }
        else
            return null;
    }
}
