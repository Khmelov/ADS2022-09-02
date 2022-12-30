package by.it.group151004.karvatskaya.lesson10;

import java.util.*;

public class TaskA<E> implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    //Обязательные к реализации методы и конструкторы
    private class Nodus{
        E value;
        Nodus lil;
        Nodus big;

        Nodus(E value)
        {
            this.value = value;
        }
    }
    private Nodus root;
    public TaskA() {
    }
    private int compareTo(E obj1, E obj2)
    {
        return Integer.compare((int)obj1, (int)obj2);
    }
    @Override
    public boolean add(E e) {
        Nodus current = root;
        Nodus old = current;
        int result = 0;
        while (current != null){
            old = current;
            result = compareTo(current.value, e);
            if (result > 0)
                current = current.lil;
            else if (result < 0)
                current = current.big;
            else
                return false;
        }
        Nodus newer_nodus = new Nodus(e);
        if (old == null)
            root = newer_nodus;
        else if (result > 0)
            old.lil = newer_nodus;
        else
            old.big = newer_nodus;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Nodus current = root;
        int res = 0;
        Nodus old = null;
        while (current != null && ((res = compareTo(current.value, (E)o)) != 0)) {
            old = current;
            if (res > 0)
                current = current.lil;
            else
                current = current.big;
        }
        if (current == null)
            return false;
        if (current.lil == null && current.big == null) {
            if (current != root) {
                if (old.big == current)
                    old.big = null;
                else
                    old.lil = null;
            } else
                root = null;
        }
        else if (current.lil != null && current.big != null){
            Nodus minimal = current.big;
            while (minimal.lil != null) {
                minimal = minimal.lil;
            }
            remove(minimal.value);
            current.value = minimal.value;
        }
        else{
            Nodus leaf  = (current.lil != null)? current.lil: current.big;
            if (current != root){
                if (current == old.lil)
                    old.lil = leaf;
                else
                    old.big = leaf;
            }
            else
                root = leaf;
        }
        return true;
    }
    private String formString(Nodus nodus, String tmp){
        if (nodus != null){
            tmp = tmp  + formString(nodus.lil, tmp) + ", " + nodus.value.toString()  + formString(nodus.big, tmp);
        }
        return tmp;
    }

    @Override
    public String toString() {
        String result = "";
        if (root == null)
            return "[" + result + "]";
        return "[" + formString(root, result).substring(2) + "]";
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
