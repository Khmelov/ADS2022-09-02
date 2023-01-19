package by.it.group151001.milkovskaya.lesson10;

import java.util.*;

public class TaskB<E>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    //Обязательные к реализации методы и конструкторы
    public Node<E> root;
    public int size = 0;
    public int compareTo(E o1, E o2)
    {
        return Integer.compare((int)o1, (int)o2);
    }
    public class Node<E>{
        public E data;
        public Node<E> left, right;
        public Node(E data){
            this.data = data;
        }
    }
    // public TaskB() {
    // }

    @Override
    public boolean add(E e) {
        Node<E> temp = root;
        Node<E> elem = new Node<>(e);
        if (!contains(e))
        {
            if (root == null)
            {
                root = new Node(e);
//                root = elem;
                size++;
                return true;
            }
            else
            {
                while (temp != null)
                {
                    if (compareTo(temp.data, e) > 0)
                    {
                        if (temp.left == null)
                        {
                            temp.left = elem;
                            size++;
                            return true;
                        }
                        temp = temp.left;
                    }
                    else
                    {
                        if (temp.right == null)
                        {
                            temp.right = elem;
                            size++;
                            return true;
                        }
                        temp = temp.right;
                    }
                }
            }
        }
        return false;
    }

    public Node<E> getMinimumKey(Node<E> curr)
    {
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }
    @Override
    public boolean remove(Object o) {
        Node<E> prnt = null;
        Node<E> curr = root;
        if (!contains(o)) {
            return false;
        } else{
            while (curr != null && compareTo(curr.data, (E) o) != 0) {
                prnt = curr;
                if (compareTo(curr.data, (E) o) > 0) {
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
            }

        if (curr.left == null && curr.right == null) {
            if (curr != root) {
                if (prnt.left == curr) {
                    prnt.left = null;
                } else {
                    prnt.right = null;
                }
            } else {
                root = null;
            }
            size--;
        } else if (curr.left != null && curr.right != null) {
            Node<E> successor = getMinimumKey(curr.right);
            E val = successor.data;
            remove(successor.data);
            curr.data = val;
        } else {
            Node<E> child = (curr.left != null) ? curr.left : curr.right;
            if (curr != root) {
                if (curr == prnt.left) {
                    prnt.left = child;
                } else {
                    prnt.right = child;
                }
            } else {
                root = child;
            }
            size--;
        }

        return true;
        }
    }

    @Override
    public boolean contains(Object o) {
        Node<E> temp = root;
        while (temp != null)
        {
            if (compareTo(temp.data, (E) o) > 0)
            {
                temp = temp.left;
            }
            else if (compareTo(temp.data, (E) o) < 0)
            {
                temp = temp.right;
            }
            else if (compareTo(temp.data, (E) o) == 0)
            {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void clear() {
        if(size > 0){
            this.root = null;
            size = 0;
        }
    }

    @Override
    public boolean isEmpty() {
        if(root != null){
            return false;
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E first() {
        if (size > 0)
        {
            Node<E> curr = getMinimumKey(root);
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


    void Sym(Node<E> root, StringBuilder res)
    {
        if (root != null)
        {
            Sym(root.left, res);
            res.append(root.data);
            res.append(", ");
            Sym(root.right, res);
        }

    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append('[');
        Sym(root, res);
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


}
