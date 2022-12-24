package by.it.group151003.matoshko.lesson10;

import java.util.*;

public class TaskC<E extends Comparable<E>>  implements NavigableSet<E> {

    //Создайте БЕЗ использования других классов (включая абстрактные)
    //аналог дерева TreeSet
    private class Node<T extends Comparable<T>> {
        public T m_info;
        public Node m_rightNode;
        public Node m_leftNode;

        public Node() {

        }
    }

    private Node m_node;
    private int m_size;

    //Обязательные к реализации методы и конструкторы
    public TaskC() { m_node = null; m_size = 0; }

    @Override
    public boolean add(E e) {
        if (e == null)
            return false;
        Node node = new Node();
        node.m_info = e;
        node.m_leftNode = null;
        node.m_rightNode = null;
        if(m_node == null){
            m_node = node;
            ++m_size;
        } else {
            Node current = m_node;
            Node prev = null;
            while (true){
                if (current.m_info.equals(e))
                    return false;
                prev = current;
                if(e.compareTo((E) prev.m_info) < 0){
                    current = current.m_leftNode;
                    if(current==null){
                        prev.m_leftNode = node;
                        ++m_size;
                        return true;
                    }
                }else{
                    current = current.m_rightNode;
                    if(current==null){
                        prev.m_rightNode = node;
                        ++m_size;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private Node getMinimumKey(Node node) {
        while (node.m_leftNode != null) {
            node = node.m_leftNode;
        }
        return node;
    }

    private boolean delete(Node node, E value) {
        Node parent = null;
        Node current = m_node;

        //Find node, which contains value, we have to remove
        while (current != null && !current.m_info.equals(value))
        {
            parent = current;
            if (value.compareTo((E) current.m_info) < 0) {
                current = current.m_leftNode;
            }
            else {
                current = current.m_rightNode;
            }
        }

        //Return false if there is no such value in tree
        if (current == null) {
            return false;
        }

        //If node is leaf
        if (current.m_leftNode == null && current.m_rightNode == null) {
            //If this leaf isn't tree root
            if (!current.equals(m_node))
            {
                if (parent.m_leftNode == current) {
                    parent.m_leftNode = null;
                }
                else {
                    parent.m_rightNode = null;
                }
            }

            //If it's root we can just make it null
            else {
                m_node = null;
            }
        }

        //Deleted node has 2 offsprings
        else if (current.m_leftNode != null && current.m_rightNode != null) {
            //Find the most left subtree element of right offspring
            Node successor = getMinimumKey(current.m_rightNode);

            //Remember value of that element
            E val = (E) successor.m_info;

            //Delete the most left element of right offspring(notice: it obviously has less than 2 offsprings)
            delete(m_node, (E)successor.m_info);

            //Copy value to our node
            current.m_info = val;
        }

        //Deleted node has 1 offspring
        else {
            Node child = (current.m_leftNode != null) ? current.m_leftNode : current.m_rightNode;

            //If deleted node isn't tree root
            if (!current.equals(m_node))
            {
                if (current == parent.m_leftNode) {
                    parent.m_leftNode = child;
                }
                else {
                    parent.m_rightNode = child;
                }
            }

            //If deleted node is root we can just assign it to child
            else {
                m_node = child;
            }
        }
        return true; //Case where we can't delete node and return false is above
    }

    @Override
    public boolean remove(Object o) { --m_size; return delete(m_node, (E) o); } //Calls recursive function

    @Override
    public boolean contains(Object o) {
        Node parent = null;
        Node current = m_node;

        //Find node, which contains value, we have to remove
        while (current != null && !current.m_info.equals((E) o))
        {
            parent = current;
            if (current.m_info.compareTo((E) o) < 0) {
                current = current.m_rightNode;
            }
            else {
                current = current.m_leftNode;
            }
        }

        //Return false if there is no such value in tree
        return current != null;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private int current = -1;

            @Override
            public boolean hasNext() {
                return current + 1 < m_size;
            }

            private E traversal(Node node, int iterator, E returnValue) {
                if (node == null)
                    return null;
                traversal(node.m_leftNode, iterator, returnValue);
                ++iterator;
                if (iterator == current + 1)
                    returnValue = (E)node.m_info;
                traversal(node.m_rightNode, iterator, returnValue);
                return returnValue;
            }

            @Override
            public E next() {
                E returnValue = null;
                return traversal(m_node, 0, returnValue);
            }

            private void del(Node node, int iterator) {
                if (node == null)
                    return;
                del(node.m_leftNode, iterator);
                ++iterator;
                if (iterator == current)
                    delete(m_node, (E)node.m_info);
                del(node.m_rightNode, iterator);
            }

            @Override
            public void remove() {
                del(m_node, 0);
                --m_size;
            }
        };
    }

    @Override
    public void clear() { m_node = null; m_size = 0; }

    @Override
    public boolean isEmpty() { return m_node == null; }

    @Override
    public int size() { return m_size; }

    @Override
    public E first() {
        //First element is the most left
        if (m_node == null)
            return null;
        Node parent = null;
        Node current = m_node;
        while (current != null) {
            parent = current;
            current = current.m_leftNode;
        }

        return (E)parent.m_info;
    }

    @Override
    public E last() {
        //Last element is the most right
        if (m_node == null)
            return null;
        Node parent = null;
        Node current = m_node;
        while (current != null) {
            parent = current;
            current = current.m_rightNode;
        }

        return (E)parent.m_info;
    }

    private E lowerRecursion(Node node, E e, E max) {
        lowerRecursion(node.m_rightNode, e, max);
        lowerRecursion(node.m_leftNode, e, max);
        if (node.m_info.compareTo(max) > 0 && max.compareTo(e) < 0)
            max = (E)node.m_info;
        return max;
    }

    @Override
    public E lower(E e) {
        E max = null;
        return lowerRecursion(m_node, e, max);
    }

    private E floorRecursion(Node node, E e, E max) {
        floorRecursion(node.m_rightNode, e, max);
        floorRecursion(node.m_leftNode, e, max);
        if (node.m_info.compareTo(max) > 0 && max.compareTo(e) <= 0)
            max = (E)node.m_info;
        return max;
    }

    @Override
    public E floor(E e) {
        E max = null;
        return floorRecursion(m_node, e, max);
    }

    private E ceilingRecursion(Node node, E e, E min) {
        ceilingRecursion(node.m_rightNode, e, min);
        ceilingRecursion(node.m_leftNode, e, min);
        if (node.m_info.compareTo(min) < 0 && min.compareTo(e) >= 0)
            min = (E)node.m_info;
        return min;
    }

    @Override
    public E ceiling(E e) {
        E min = null;
        return ceilingRecursion(m_node, e, min);
    }

    private E higherRecursion(Node node, E e, E min) {
        higherRecursion(node.m_rightNode, e, min);
        higherRecursion(node.m_leftNode, e, min);
        if (node.m_info.compareTo(min) > 0 && min.compareTo(e) < 0)
            min = (E)node.m_info;
        return min;
    }

    @Override
    public E higher(E e) {
        E min = null;
        return higherRecursion(m_node, e, min);
    }

    @Override
    public E pollFirst() {
        //First element is the most left
        if (m_node == null)
            return null;
        Node grandParent = null;
        Node parent = null;
        Node current = m_node;
        while (current != null) {
            grandParent = parent;
            parent = current;
            current = current.m_leftNode;
        }
        E info = (E)parent.m_info;
        if (grandParent == null)
            m_node = null;
        else
            grandParent.m_leftNode = null;
        --m_size;
        return info;
    }

    @Override
    public E pollLast() {
        //Last element is the most right
        if (m_node == null)
            return null;
        Node grandParent = null;
        Node parent = null;
        Node current = m_node;
        while (current != null) {
            grandParent = parent;
            parent = current;
            current = current.m_rightNode;
        }
        E info = (E)parent.m_info;
        if (grandParent == null)
            m_node = null;
        else
            grandParent.m_rightNode = null;
        --m_size;
        return info;
    }

    private String str(Node node, StringJoiner txt) {
        if (node == null)
            return txt.toString();
        str(node.m_leftNode, txt);
        txt.add(node.m_info.toString());
        str(node.m_rightNode, txt);
        return txt.toString();
    }

    @Override
    public String toString() {
        StringJoiner txt = new StringJoiner(", ", "[", "]");;
        return str(m_node, txt);
    }
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////         Эти методы реализовывать необязательно      ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

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
