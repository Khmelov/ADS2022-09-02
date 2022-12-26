package by.it.group151002.bybikov.lesson10;

import java.util.*;

public class TaskA<E extends Comparable<E>>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.
    private E value;
    private TaskA<E> left;
    private TaskA<E> right;
    //Обязательные к реализации методы и конструкторы
    public TaskA() {
        this.value=null;
        this.left = null;
        this.right = null;
    }

    private void SetKey(TaskA<E> e){
        this.value = e.value;
        this.left = e.left;
        this.right =e.right;
    }
    public TaskA<E> getMinimumKey(TaskA<E> curr)
    {
        while (curr.left.value != null) {
            curr = curr.left;
        }
        return curr;
    }
    public TaskA<E> deleteNode(TaskA<E> root, E key)
    {

      TaskA<E> parent = null;


        TaskA<E> curr = root;
        System.out.println(curr.value);

        while (curr.value != null && !curr.value.equals(key))
        {

            parent = curr;


            if (curr.value.hashCode() > key.hashCode()) {
                curr = curr.left;
            }
            else {
                curr = curr.right;
            }
        }


        if (curr.value == null) {
            return root;
        }


        if (curr.left.value == null && curr.right.value == null)
        {

            if (curr.value != root.value)
            {
                if (parent.left.value == curr.value) {
                    parent.left.value = null;
                }
                else {
                    parent.right.value = null;
                }
            }

            else {
                root.value = null;
            }
        }


        else if (curr.left.value != null && curr.right.value != null)
        {

           TaskA<E> successor = getMinimumKey(curr.right);


            E val = successor.value;


            deleteNode(root, successor.value);


            curr.value = val;
        }


        else {

            TaskA<E> child = (curr.left.value != null)? curr.left: curr.right;

            if (curr.value != root.value)
            {
                if (curr.value == parent.left.value) {
                    parent.left = child;
                }
                else {
                    parent.right = child;
                }
            }


            else {
                root = child;

            }
        }

        return root;
    }

    @Override
    public boolean add(E e) {
        if(this.value == null)
        {
            this.value = e;
            this.left = new TaskA<>();
            this.right =  new TaskA<>();
        }else {
            if(this.value.hashCode() < e.hashCode()){
                return this.right.add(e);
            }
            if(this.value.hashCode()> e.hashCode())
            {
                return this.left.add(e);
            }

        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        TaskA<E> root =deleteNode(this,(E)o);
        this.SetKey(root);
        return root.value == this.value;
    }
    private void substr(StringBuilder sb)
    {
        if(this.value ==null)
        {
            return;
        }
        this.left.substr(sb);
        sb.append(this.value);
        sb.append(", ");
        this.right.substr(sb);

    }
    @Override
    public String toString() {
        if (this.value == null)
        {
            return "[]";
        }
        StringBuilder sb= new StringBuilder();
        sb.append("[");
        this.left.substr(sb);
        sb.append(this.value);
        sb.append(", ");
        this.right.substr(sb);
        sb.delete(sb.length()-2,sb.length());
        sb.append("]");
        return sb.toString();
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
