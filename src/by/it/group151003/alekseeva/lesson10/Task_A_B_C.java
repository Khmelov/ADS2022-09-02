package by.it.group151003.alekseeva.lesson10;

import java.util.*;

public class Task_A_B_C<E>  implements NavigableSet<E> {

    public static boolean RED   = true;
    public static boolean BLACK = false;

    private Node<E> node;
    private int size;

    public static class Node<E> {
        private E data;
        private Node<E> left, right;
        private boolean color;
        private int size;

        public Node(E data, boolean color, int size) {
            this.data = data;
            this.color = color;
            this.size = size;
        }
    }

    private Comparator<? super E> comparator;

    /* Constructors */

    public Task_A_B_C() {
    }

    public Task_A_B_C(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public Task_A_B_C(E[] dataArray) {
        for (E data : dataArray)
            add(data);
    }

    public Task_A_B_C(E[] dataArray, Comparator<? super E> comparator) {
        this.comparator = comparator;
        for (E data : dataArray)
            add(data);
    }

    public Task_A_B_C(Iterable<? extends E> dataArray) {
        for (E data : dataArray)
            add(data);
    }

    public Task_A_B_C(Iterable<? extends E> dataArray, Comparator<? super E> comparator) {
        this.comparator = comparator;
        for (E data : dataArray)
            add(data);
    }

    /* Interface */

    @Override
    public int size() {
        return size(node);
    }

    @Override
    public boolean isEmpty() {
        return node == null;
    }

    @Override
    public boolean contains(Object o) {
        return access((E) o) != null;
    }

    @Override
    public boolean add(E data) {
        if (data == null)
            throw new IllegalArgumentException();

        if (!contains(data)) {
            node = insert(node, data);
            node.color = BLACK;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null)
            throw new IllegalArgumentException();
        if (!contains(o))
            return false;

        if (!isRed(node.left) && !isRed(node.right))
            node.color = RED;

        node = delete(node, (E) o);
        if (!isEmpty())
            node.color = BLACK;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (E e : this) {
            sb.append(e);
            sb.append(", ");
        }
        if (sb.length() > 1) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null)
            throw new NullPointerException();
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null)
            throw new NullPointerException();
        for (E e : c) {
            add(e);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null)
            throw new NullPointerException();
        for (Object o : c) {
            if (!contains(o)) remove(o);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null)
            throw new NullPointerException();
        for (Object o : c) {
            remove(o);
        }
        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = node;
            private Stack<Node<E>> stack = new Stack<>();

            @Override
            public boolean hasNext() {
                return current != null || !stack.isEmpty();
            }

            @Override
            public E next() {
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
                current = stack.pop();
                E result = current.data;
                current = current.right;
                return result;
            }
        };
    }

    @Override
    public void clear() {
        node = null;
    }

    @Override
    public E lower(E e) {
        if (e == null)
            throw new NullPointerException();
        Node<E> x = node;
        Node<E> result = null;
        while (x != null) {
            if (compare(e, x.data) > 0) {
                result = x;
                x = x.right;
            } else {
                x = x.left;
            }
        }
        return result == null ? null : result.data;
    }

    @Override
    public E floor(E e) {
        if (e == null)
            throw new NullPointerException();
        Node<E> x = node;
        Node<E> result = null;
        while (x != null) {
            if (compare(e, x.data) >= 0) {
                result = x;
                x = x.right;
            } else {
                x = x.left;
            }
        }
        return result == null ? null : result.data;
    }

    @Override
    public E ceiling(E e) {
        if (e == null) throw new NullPointerException();
        Node<E> x = node;
        Node<E> result = null;
        while (x != null) {
            if (compare(e, x.data) <= 0) {
                result = x;
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return result == null ? null : result.data;
    }

    @Override
    public E higher(E e) {
        if (e == null) throw new NullPointerException();
        Node<E> x = node;
        Node<E> result = null;
        while (x != null) {
            if (compare(e, x.data) < 0) {
                result = x;
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return result == null ? null : result.data;
    }

    @Override
    public E pollFirst() {
        E result = first();
        remove(result);
        return result;
    }

    @Override
    public E pollLast() {
        E result = last();
        remove(result);
        return result;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size()];
        int i = 0;
        for (E e : this) {
            result[i++] = e;
        }
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size()) {
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size());
        }
        int i = 0;
        Object[] result = a;
        for (E e : this) {
            result[i++] = e;
        }
        if (a.length > size()) {
            a[size()] = null;
        }
        return a;
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
        if (node == null)
            throw new NoSuchElementException();
        Node<E> x = node;
        while (x.left != null) {
            x = x.left;
        }
        return x.data;
    }

    @Override
    public E last() {
        if (node == null)
            throw new NoSuchElementException();
        Node<E> x = node;
        while (x.right != null) {
            x = x.right;
        }
        return x.data;
    }

    /* Helper methods */

    private int size(Node<E> x) {
        if (x == null) return 0;
        return x.size;
    }

    private boolean isRed(Node<E> x) {
        if (x == null)
            return false;
        return x.color == RED;
    }

    private int compare(E data1, E data2) {
        if (comparator == null) {
            return ((Comparable<E>) data1).compareTo(data2);
        } else {
            return comparator.compare(data1, data2);
        }
    }

    public E access(E data) {
        return access(node, data);
    }

    private E access(Node<E> x, E data) {
        if (x == null) return null;
        int cmp = compare(data, x.data);
        if      (cmp < 0) return access(x.left, data);
        else if (cmp > 0) return access(x.right, data);
        else              return x.data;
    }

    private Node<E> insert(Node<E> newNode, E data) {
        if (newNode == null)
            return new Node<>(data, RED, 1);
        int res = compare(data, newNode.data);
        if (res < 0)
            newNode.left  = insert(newNode.left,  data);
        else if (res > 0)
            newNode.right = insert(newNode.right, data);
        else newNode.data   = data;

        if (isRed(newNode.right) && !isRed(newNode.left))
            newNode = rotateLeft(newNode);
        if (isRed(newNode.left)  &&  isRed(newNode.left.left))
            newNode = rotateRight(newNode);
        if (isRed(newNode.left)  &&  isRed(newNode.right))
            recolor(newNode);
        newNode.size = size(newNode.left) + size(newNode.right) + 1;

        return newNode;
    }

    private Node<E> rotateRight(Node<E> node) {
        assert (node != null) && isRed(node.left);
        Node<E> x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = x.right.color;
        x.right.color = RED;
        x.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        return x;
    }

    private Node<E> rotateLeft(Node<E> node) {
        assert (node != null) && isRed(node.right);
        Node<E> x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = x.left.color;
        x.left.color = RED;
        x.size = node.size;
        node.size = size(node.left) + size(node.right) + 1;
        return x;
    }

    private void recolor(Node<E> node) {
        assert (node != null) && (node.left != null) && (node.right != null);
        assert (!isRed(node) && isRed(node.left) && isRed(node.right))
                || (isRed(node) && !isRed(node.left) && !isRed(node.right));
        node.color = !node.color;
        node.left.color = !node.left.color;
        node.right.color = !node.right.color;
    }

    private Node<E> delete(Node<E> node, E o) {
        if (node == null)
            return null;
        if (compare(o, node.data) < 0) {
            if (!isRed(node.left) && !isRed(node.left.left))
                node = rotateRL(node);
            node.left = delete(node.left, o);
        }
        else {
            if (isRed(node.left))
                node = rotateRight(node);
            if (compare(o, node.data) == 0 && (node.right == null))
                return null;
            if (!isRed(node.right) && !isRed(node.right.left))
                node = rotateRR(node);
            if (compare(o, node.data) == 0) {
                Node<E> x = min(node.right);
                node.data = x.data;
                node.right = deleteMin(node.right);
            }
            else node.right = delete(node.right, o);
        }
        return balance(node);
    }

    private Node<E> deleteMin(Node<E> node) {
        if (node.left == null)
            return null;

        if (!isRed(node.left) && !isRed(node.left.left))
            node = rotateRL(node);
        node.left = deleteMin(node.left);
        return balance(node);
    }

    private Node<E> balance(Node<E> node) {
        if (isRed(node.right))
            node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left))
            node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right))
            recolor(node);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    private Node<E> min(Node<E> node) {
        if (node.left == null)
            return node;
        else
            return min(node.left);
    }

    private Node<E> rotateRR(Node<E> node) {
        recolor(node);
        if (isRed(node.left.left)) {
            node = rotateRight(node);
            recolor(node);
        }
        return node;
    }

    private Node<E> rotateRL(Node<E> node) {
        recolor(node);
        if (isRed(node.right.left)) {
            node.right = rotateRight(node.right);
            node = rotateLeft(node);
            recolor(node);
        }
        return node;
    }
}
