package by.it.group151004.karvatskaya.lesson10;

import java.util.*;

public class TaskB<E>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    //Обязательные к реализации методы и конструкторы
    private class Nodus{
        E value;
        Nodus lil;
        Nodus big;
        Nodus old;
        Nodus(E value)
        {
            this.value = value;
        }
    }
    private class SetIterator<T> implements Iterator<T> {
        private Nodus nodus, nxt;
        private Nodus findLeft(Nodus nodus) {
            if (nodus == null)
                return null;
            while (nodus.lil != null) {
                nodus = nodus.lil;
            }
            return nodus;
        }
        SetIterator(){
            nodus = null;
            nxt = null;
        }
        @Override
        public boolean hasNext() {
            if (nodus == null) {
                nxt = findLeft(root);
            } else if (nodus.big != null) {
                nxt = findLeft(nodus.big);
            } else {
                nxt = nodus;
                while (nxt.old != null && nxt == nxt.old.big) {
                    nxt = nxt.old;
                }
                nxt = nxt.old;
                if (nxt == null)
                    return false;
            }
            return true;
        }

        @SuppressWarnings("unchecked")
        @Override
        public T next() {
            if (nxt == null)
                return null;
            nodus = nxt;
            return (T) nxt.value;
        }
    }
    private Nodus root;
    private int size = 0;
    public TaskB() {
    }
    private int compareTo(E obj1, E obj2) {
        return Integer.compare((int)obj1, (int)obj2);
    }
    @Override
    public boolean add(E e) {
        Nodus current = root;
        Nodus old = current;
        int res = 0;
        while (current != null){
            old = current;
            res = compareTo(current.value, e);
            if (res > 0){
                current = current.lil;
            }
            else if (res < 0){
                current = current.big;
            }
            else{
                return false;
            }
        }
        Nodus new_node = new  Nodus(e);
        if (old == null)
            root = new_node;
        else if (res > 0) {
            old.lil = new_node;
            old.lil.old = old;
        }
        else {
            old.big = new_node;
            old.big.old = old;
        }
        size++;
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
            Nodus min = current.big;
            while (min.lil != null) {
                min = min.lil;
            }
            remove(min.value);
            size++;
            current.value = min.value;
        }
        else{
            Nodus leaf = (current.lil != null)? current.lil: current.big;
            if (current != root){
                if (current == old.lil)
                    old.lil = leaf;
                else
                    old.big = leaf;
            }
            else
                root = leaf;
        }
        size--;
        return true;
    }
    private String formString( Nodus nodus, String tmp){
        if (nodus != null){
            tmp = tmp  + formString(nodus.lil, tmp) + ", " + nodus.value.toString()  + formString(nodus.big, tmp);
        }
        return tmp;
    }

    @Override
    public String toString() {
        String res = "";
        if (root == null)
            return "[" + res + "]";
        return "[" + formString(root, res).substring(2) + "]";
    }
    @Override
    public boolean contains(Object o) {
        Nodus cur = root;
        int result = 0;
        while (cur != null && ((result = compareTo(cur.value, (E)o)) != 0))
            if (result > 0)
                cur = cur.lil;
            else
                cur = cur.big;
        return cur != null;
    }

    @Override
    public Iterator<E> iterator() {
        return new SetIterator<>();
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E first() {
        if (root == null)
            return null;
        else{
            Nodus cur = root;
            while (cur.lil != null)
                cur = cur.lil;
            return cur.value;
        }
    }

    @Override
    public E last() {
        if (root == null)
            return null;
        else{
            Nodus cur = root;
            while (cur.big != null)
                cur = cur.big;
            return cur.value;
        }
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
