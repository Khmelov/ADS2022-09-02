package by.it.group151004.belsky.lesson10;

import java.util.*;

public class TaskB<E extends Comparable<E>>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    //Обязательные к реализации методы и конструкторы

    private Node <E, Boolean> root = null;
    private int size;

    public TaskB() {
        size = 0;
    }

    @Override
    public boolean add(E e) {
        if (root == null) {
            root = new Node<>(e, true);
            size += 1;
            return true;
        } else if (root.getKey().equals(e)) {
            return false;
        }

        Node<E, Boolean> loopPt = root;
        while (true) {
            if (loopPt.getKey().compareTo(e) > 0) {
                //left
                if (loopPt.getLeft() == null) {
                    loopPt.setLeft(new Node<>(e, true));
                    size += 1;
                    return true;
                } else if (loopPt.getLeft().getKey().equals(e)) {
                    return false;
                } else {
                    loopPt = loopPt.getLeft();
                }
            } else {
                //right
                if (loopPt.getRight() == null) {
                    loopPt.setRight(new Node<>(e, true));
                    size += 1;
                    return true;
                } else if (loopPt.getRight().getKey().equals(e)) {
                    return false;
                } else {
                    loopPt = loopPt.getRight();
                }
            }
        }

    }

    private Node<E, Boolean> getMostLeft(Node<E, Boolean> root) {
        Node<E, Boolean> loopPt = root;
        while (loopPt.getLeft().getLeft() != null) {
            loopPt = loopPt.getLeft();
        }
        Node<E, Boolean> returnPt = loopPt.getLeft();
        loopPt.setLeft(returnPt.getRight());
        return returnPt;
    }

    @Override
    public boolean remove(Object o) {
        E e = (E) o;
//        if (root == null) return false;
        Node<E, Boolean> loopPt = root, parentPt = null;

        while (true) {
            if (loopPt == null) return false;

            if (loopPt.getKey().compareTo(e) > 0) {
                //left
                parentPt = loopPt;
                loopPt = loopPt.getLeft();
            } else if (loopPt.getKey().compareTo(e) < 0) {
                parentPt = loopPt;
                loopPt = loopPt.getRight();
            } else {
                if (loopPt.getLeft() == null && loopPt.getRight() == null) {
                    //no children present
                    if (parentPt == null) {
                        root = null;
                        return true;
                    }

                    if (parentPt.getLeft() != null && parentPt.getLeft().equals(loopPt)) {
                        //isLeft
                        parentPt.setLeft(null);
                    } else if (parentPt.getRight() != null && parentPt.getRight().equals(loopPt)) {
                        //isRight
                        parentPt.setRight(null);
                    }
                } else if (loopPt.getRight() == null) {
                    //only left child presents
                    loopPt.setKey(loopPt.getLeft().getKey());
                    loopPt.setRight(loopPt.getLeft().getRight());
                    loopPt.setLeft(loopPt.getLeft().getLeft());
                } else if (loopPt.getLeft() == null) {
                    //only right child presents
                    loopPt.setKey(loopPt.getRight().getKey());
                    loopPt.setLeft(loopPt.getRight().getLeft());
                    loopPt.setRight(loopPt.getRight().getRight());
                } else {
                    //both children present
                    if (loopPt.getRight().getLeft() == null) {
                        loopPt.setKey(loopPt.getRight().getKey());
                        loopPt.setRight(loopPt.getRight().getRight());
                    } else {
                        Node<E, Boolean> mostLeft = getMostLeft(loopPt.getRight());
                        loopPt.setKey(mostLeft.getKey());
                    }
                }
                size -= 1;
                return true;
            }
        }
    }

    @Override
    public boolean contains(Object o) {
        E e = (E) o;
        Node <E, Boolean> loopPt = root;
        while (loopPt != null) {
            if (loopPt.getKey().compareTo(e) > 0) {
                //left
                loopPt = loopPt.getLeft();
            } else if (loopPt.getKey().compareTo(e) < 0) {
                loopPt = loopPt.getRight();
            } else {
                return true;
            }
        }
        return false;
    }

    private void traversalAL(ArrayList<E> list, Node<E, Boolean> node) {
        if (node != null) {
            traversalAL(list, node.getLeft());
            list.add(node.getKey());
            traversalAL(list, node.getRight());
        }
    }

    @Override
    public Iterator<E> iterator() { //Can I use arraylist here?
        ArrayList<E> list = new ArrayList<>();
        traversalAL(list, root);
        return list.iterator();
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
        Node <E, Boolean> loopPt = root;
        while (loopPt.getLeft() != null) {
            loopPt = loopPt.getLeft();
        }
        return loopPt.getKey();
    }

    @Override
    public E last() {
        Node <E, Boolean> loopPt = root;
        while (loopPt.getRight() != null) {
            loopPt = loopPt.getRight();
        }
        return loopPt.getKey();
    }

    private void traversalSB(StringBuilder appendStr, Node<E, Boolean> node) {
        if (node != null) {
            traversalSB(appendStr, node.getLeft());
            appendStr.append(node.getKey().toString());
            appendStr.append(", ");
            traversalSB(appendStr, node.getRight());
        }
    }

    @Override
    public String toString() {
        if (root == null) return "[]";
        StringBuilder resultStr = new StringBuilder();
        resultStr.append("[");
        traversalSB(resultStr, root);
        resultStr.delete(resultStr.length()-1-1, resultStr.length());
        resultStr.append("]");
        return resultStr.toString();
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
