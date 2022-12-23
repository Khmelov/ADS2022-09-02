package by.it.group151004.stahnov.lesson10;

import java.util.*;

public class TaskABC<E>  implements NavigableSet<E> {

    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private Node<E> root;
    private int size;

    private static class Node<E> {
        private E key;
        private Node<E> left, right;
        private boolean color;
        private int size;

        public Node(E key, boolean color, int size) {
            this.key = key;
            this.color = color;
            this.size = size;
        }
    }

    private Comparator<? super E> comparator;

    /* Constructors */

    public TaskABC() {
    }

    public TaskABC(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    public TaskABC(E[] keys) {
        for (E key : keys)
            add(key);
    }

    public TaskABC(E[] keys, Comparator<? super E> comparator) {
        this.comparator = comparator;
        for (E key : keys)
            add(key);
    }

    public TaskABC(Iterable<? extends E> keys) {
        for (E key : keys)
            add(key);
    }

    public TaskABC(Iterable<? extends E> keys, Comparator<? super E> comparator) {
        this.comparator = comparator;
        for (E key : keys)
            add(key);
    }

    /* Interface */

    @Override
    public int size() {
        return size(root);
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean contains(Object o) {
        return get((E) o) != null;
    }

    @Override
    public boolean add(E key) {
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");

        if (!contains(key)) {
            root = put(root, key);
            root.color = BLACK;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) throw new IllegalArgumentException("argument to delete() is null");
        if (!contains(o)) return false;

        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = delete(root, (E) o);
        if (!isEmpty()) root.color = BLACK;
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
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> current = root;
            private Node<E> lastReturned = null;
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
                E result = current.key;
                current = current.right;
                return result;
            }
        };
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public E lower(E e) {
        if (e == null) throw new NullPointerException();
        Node<E> x = root;
        Node<E> result = null;
        while (x != null) {
            if (compare(e, x.key) > 0) {
                result = x;
                x = x.right;
            } else {
                x = x.left;
            }
        }
        return result == null ? null : result.key;
    }

    @Override
    public E floor(E e) {
        if (e == null) throw new NullPointerException();
        Node<E> x = root;
        Node<E> result = null;
        while (x != null) {
            if (compare(e, x.key) >= 0) {
                result = x;
                x = x.right;
            } else {
                x = x.left;
            }
        }
        return result == null ? null : result.key;
    }

    @Override
    public E ceiling(E e) {
        if (e == null) throw new NullPointerException();
        Node<E> x = root;
        Node<E> result = null;
        while (x != null) {
            if (compare(e, x.key) <= 0) {
                result = x;
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return result == null ? null : result.key;
    }

    @Override
    public E higher(E e) {
        if (e == null) throw new NullPointerException();
        Node<E> x = root;
        Node<E> result = null;
        while (x != null) {
            if (compare(e, x.key) < 0) {
                result = x;
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return result == null ? null : result.key;
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
    public boolean containsAll(Collection<?> c) {
        if (c == null) throw new NullPointerException();
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c == null) throw new NullPointerException();
        for (E e : c) {
            add(e);
        }
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) throw new NullPointerException();
        for (Object o : c) {
            if (!contains(o)) remove(o);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) throw new NullPointerException();
        for (Object o : c) {
            remove(o);
        }
        return true;
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
        if (root == null) throw new NoSuchElementException();
        Node<E> x = root;
        while (x.left != null) {
            x = x.left;
        }
        return x.key;
    }

    @Override
    public E last() {
        if (root == null) throw new NoSuchElementException();
        Node<E> x = root;
        while (x.right != null) {
            x = x.right;
        }
        return x.key;
    }

    /* Helper methods */

    private int size(Node<E> x) {
        if (x == null) return 0;
        return x.size;
    }

    private boolean isRed(Node<E> x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private int compare(E key, E key1) {
        if (comparator == null) {
            return ((Comparable<E>) key).compareTo(key1);
        } else {
            return comparator.compare(key, key1);
        }
    }

    public E get(E key) {
        return get(root, key);
    }

    private E get(Node<E> x, E key) {
        if (x == null) return null;
        int cmp = compare(key, x.key);
        if      (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else              return x.key;
    }

    private Node<E> put(Node<E> h, E key) {
        if (h == null) return new Node<>(key, RED, 1);

        int cmp = compare(key, h.key);
        if      (cmp < 0) h.left  = put(h.left,  key);
        else if (cmp > 0) h.right = put(h.right, key);
        else              h.key   = key;

        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left)  &&  isRed(h.right)) flipColors(h);
        h.size = size(h.left) + size(h.right) + 1;

        return h;
    }

    private Node<E> rotateRight(Node<E> h) {
        assert (h != null) && isRed(h.left);
        Node<E> x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = x.right.color;
        x.right.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node<E> rotateLeft(Node<E> h) {
        assert (h != null) && isRed(h.right);
        Node<E> x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = x.left.color;
        x.left.color = RED;
        x.size = h.size;
        h.size = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node<E> h) {
        assert (h != null) && (h.left != null) && (h.right != null);
        assert (!isRed(h) && isRed(h.left) && isRed(h.right))
                || (isRed(h) && !isRed(h.left) && !isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    private Node<E> delete(Node<E> root, E o) {
        if (root == null) return null;

        if (compare(o, root.key) < 0) {
            if (!isRed(root.left) && !isRed(root.left.left))
                root = moveRedLeft(root);
            root.left = delete(root.left, o);
        }
        else {
            if (isRed(root.left))
                root = rotateRight(root);
            if (compare(o, root.key) == 0 && (root.right == null))
                return null;
            if (!isRed(root.right) && !isRed(root.right.left))
                root = moveRedRight(root);
            if (compare(o, root.key) == 0) {
                Node<E> x = min(root.right);
                root.key = x.key;
                root.right = deleteMin(root.right);
            }
            else root.right = delete(root.right, o);
        }
        return balance(root);
    }

    private Node<E> deleteMin(Node<E> right) {
        if (right.left == null)
            return null;

        if (!isRed(right.left) && !isRed(right.left.left))
            right = moveRedLeft(right);
        right.left = deleteMin(right.left);
        return balance(right);
    }

    private Node<E> balance(Node<E> right) {
        if (isRed(right.right)) right = rotateLeft(right);
        if (isRed(right.left) && isRed(right.left.left)) right = rotateRight(right);
        if (isRed(right.left) && isRed(right.right)) flipColors(right);
        right.size = size(right.left) + size(right.right) + 1;
        return right;
    }

    private Node<E> min(Node<E> right) {
        if (right.left == null) return right;
        else return min(right.left);
    }

    private Node<E> moveRedRight(Node<E> root) {
        flipColors(root);
        if (isRed(root.left.left)) {
            root = rotateRight(root);
            flipColors(root);
        }
        return root;
    }

    private Node<E> moveRedLeft(Node<E> root) {
        flipColors(root);
        if (isRed(root.right.left)) {
            root.right = rotateRight(root.right);
            root = rotateLeft(root);
            flipColors(root);
        }
        return root;
    }
}
