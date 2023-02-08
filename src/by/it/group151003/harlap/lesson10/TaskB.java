package by.it.group151003.harlap.lesson10;

import java.util.*;

public class TaskB<E>  implements NavigableSet<E> {

    //Создайте аналог дерева TreeSet БЕЗ использования других классов СТАНДАРТНОЙ БИБЛИОТЕКИ
    //Не нужно на массивах это делать или маскируя в поля TreeSet, TreeMap и т.д.
    //Можно реализовать класс Node с двумя полями такого же типа (потомки дерева),
    //в нем также может быть поле элемента E. Далее на этой основе ожидается бинарное дерево.

    private Node<E> root;
    private int size = 0;

    private static class Node<E> {
        private E value;
        private Node<E> left;
        private Node<E> right;
        private Node<E> parent;

        Node(E value) {
            this.value = value;
        }

        E getValue() {
            return value;
        }

        void setValue(E value) {
            this.value = value;
        }

        Node<E> getLeft() {
            return left;
        }

        void setLeft(Node<E> left) {
            this.left = left;
        }

        Node<E> getRight() {
            return right;
        }

        void setRight(Node<E> right) {
            this.right = right;
        }
    }

    private static class TreeSetIterator<E> implements Iterator<E> {

        private final Node<E> root;

        private Node<E> node;
        private Node<E> next;

        private Node<E> findLeft(Node<E> node) {
            if (node == null) return null;

            while (node.left != null) {
                node = node.left;
            }

            return node;
        }

        private TreeSetIterator(Node<E> root) {
            this.root = root;
        }

        @Override
        public boolean hasNext() {
            if (node == null) {
                next = findLeft(root);
            } else if (node.right != null) {
                next = findLeft(node.right);
            } else {
                next = node;
                while (next.parent != null && next == next.parent.right) {
                    next = next.parent;
                }

                next = next.parent;
                return next != null;
            }
            return true;
        }

        @Override
        public E next() {
            if (next == null) return null;

            node = next;
            return next.value;
        }
    }

    //Обязательные к реализации методы и конструкторы
    public TaskB() {
    }
    private int compareTo(E obj1, E obj2) {
        return Integer.compare((int)obj1, (int)obj2);
    }
    @Override
    public boolean add(E e) {
        Node<E> current = root;
        Node<E> parent = current;
        int result = 0;
        while (current != null){
            parent = current;
            result = compareTo(current.value,e);
            if (result > 0){
                current = current.left;
            } else if (result < 0){
                current = current.right;
            } else {
                return false;
            }
        }

        final Node<E> newNode = new Node<E>(e);
        if (parent == null) {
            root = newNode;
        } else if (result > 0) {
            parent.left = newNode;
            parent.left.parent = parent;
        } else {
            parent.right = newNode;
            parent.right.parent = parent;
        }

        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<E> current = root;
        Node<E> parent = null;

        int result;
        while (current != null && (result = compareTo(current.value,(E) o)) != 0) {
            parent = current;
            if (result > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        if (current == null) {
            return false;
        }

        if (current.left == null && current.right == null) {
            if (current != root) {
                if (parent.right == current) {
                    parent.right = null;
                } else {
                    parent.left = null;
                }
            } else {
                root = null;
            }
        } else if (current.left != null && current.right != null) {
            Node<E> min = current.right;
            while (min.left != null) {
                min = min.left;
            }
            remove(min.value);
            size++;
            current.value = min.value;
        } else {
            final Node<E> child  = (current.left != null)
                    ? current.left
                    : current.right;

            if (current != root){
                if (current == parent.left) {
                    parent.left = child;
                    child.parent = parent;
                } else {
                    parent.right = child;
                    child.parent = parent;
                }
            } else {
                root = child;
            }
        }
        size--;
        return true;
    }

    @Override
    public boolean contains(Object o) {
        Node<E> current = root;
        int result;
        while (current != null && ((result = compareTo(current.value,(E) o)) != 0)) {
            if (result > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current != null;
    }

    @Override
    public Iterator<E> iterator() {
        return new TreeSetIterator<>(root);
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
        if (root == null) {
            return null;
        } else {
            Node<E> current = root;
            while (current.left != null) {
                current = current.left;
            }
            return current.value;
        }
    }

    @Override
    public E last() {
        if (root == null) {
            return null;
        } else {
            Node<E> current = root;
            while (current.right != null) {
                current = current.right;
            }
            return current.value;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");

        if (root == null) return "[]";

        trace(root, stringBuilder);

        return stringBuilder.append("]").toString().replace(", ]", "]");
    }

    private void trace(Node<E> node, StringBuilder builder) {
        if (node == null) return;

        trace(node.getLeft(), builder);
        builder.append(node.getValue()).append(", ");
        trace(node.getRight(), builder);
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
