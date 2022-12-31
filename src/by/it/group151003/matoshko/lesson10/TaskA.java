package by.it.group151003.matoshko.lesson10;
import java.util.*;

public class TaskA<E extends Comparable<E>>  implements NavigableSet<E> {

    private class Node<T extends Comparable<T>> {
        public T m_info;
        public by.it.group151003.matoshko.lesson10.TaskA.Node m_rightNode;
        public by.it.group151003.matoshko.lesson10.TaskA.Node m_leftNode;

        public Node() {

        }
    }
    //Создайте БЕЗ использования других классов (включая абстрактные)
    //аналог дерева TreeSet
    private by.it.group151003.matoshko.lesson10.TaskA.Node m_node;
    private int m_size;

    //Обязательные к реализации методы и конструкторы
    public TaskA() { m_node = null; m_size = 0;}

    @Override
    public boolean add(E e) {
        if (e == null)
            return false;
        by.it.group151003.matoshko.lesson10.TaskA.Node node = new by.it.group151003.matoshko.lesson10.TaskA.Node();
        node.m_info = e;
        node.m_leftNode = null;
        node.m_rightNode = null;
        if(m_node == null){
            m_node = node;
            ++m_size;
        } else {
            by.it.group151003.matoshko.lesson10.TaskA.Node current = m_node;
            by.it.group151003.matoshko.lesson10.TaskA.Node prev = null;
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

    private by.it.group151003.matoshko.lesson10.TaskA.Node getMinimumKey(by.it.group151003.matoshko.lesson10.TaskA.Node node) {
        while (node.m_leftNode != null) {
            node = node.m_leftNode;
        }
        return node;
    }

    private boolean delete(by.it.group151003.matoshko.lesson10.TaskA.Node node, E value) {
        by.it.group151003.matoshko.lesson10.TaskA.Node parent = null;
        by.it.group151003.matoshko.lesson10.TaskA.Node current = m_node;

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
            by.it.group151003.matoshko.lesson10.TaskA.Node successor = getMinimumKey(current.m_rightNode);

            //Remember value of that element
            E val = (E) successor.m_info;

            //Delete the most left element of right offspring(notice: it obviously has less than 2 offsprings)
            delete(m_node, (E)successor.m_info);

            //Copy value to our node
            current.m_info = val;
        }

        //Deleted node has 1 offspring
        else {
            by.it.group151003.matoshko.lesson10.TaskA.Node child = (current.m_leftNode != null) ? current.m_leftNode : current.m_rightNode;

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

    private String str(by.it.group151003.matoshko.lesson10.TaskA.Node node, StringJoiner txt) {
        if (node == null)
            return txt.toString();
        str(node.m_leftNode, txt);
        txt.add(node.m_info.toString());
        str(node.m_rightNode, txt);
        return txt.toString();
    }

    @Override
    public String toString() { //Calls recursive function
        StringJoiner txt = new StringJoiner(", ", "[", "]");;
        return str(m_node, txt);
    }
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////         Эти методы реализовывать необязательно      ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
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
        return null;
    }

    @Override
    public E last() {
        return null;
    }
}