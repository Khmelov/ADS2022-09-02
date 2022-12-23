package by.it.group151001.loziuk.lesson10;

import java.util.*;

public class TaskB<E>  implements NavigableSet<E> {
    //Создайте БЕЗ использования других классов (включая абстрактные)
    //аналог дерева TreeSet
    private E value;
    private TaskB<E> left;
    private TaskB<E> right;
    private void SetKey(TaskB<E> elem){
        this.left = elem.left;
        this.right = elem.right;
        this.value = elem.value;
    }
    public TaskB() {
        this.value = null;
        this.left = null;
        this.right = null;
    }

    @Override
    public boolean add(E e) {
        if (this.value == null){
            this.value = e;
            this.left = new TaskB<>();
            this.right = new TaskB<>();
            return true;
        }
        else{
            if (this.value.hashCode() < e.hashCode()){
                return this.right.add(e);
            }
            if (this.value.hashCode() > e.hashCode()){
                return this.left.add(e);
            }
        }
        return false;
    }
    public TaskB<E> getMinimumKey(TaskB<E> cur)
    {
        while (cur.left.value != null) {
            cur = cur.left;
        }
        return cur;
    }
    public TaskB<E> deleteNode(TaskB<E> root, E key)
    {
        TaskB<E> parent = null;

        TaskB<E> cur = root;
        System.out.println(cur.value);

        while (cur.value != null && !cur.value.equals(key))
        {
            parent = cur;
            if (cur.value.hashCode() > key.hashCode()) {
                cur = cur.left;
            }
            else {
                cur = cur.right;
            }
        }

        if (cur.value == null) {
            return root;
        }

        // Элемент - лист
        if (cur.left.value == null && cur.right.value == null)
        {
            // не корень
            if (cur.value != root.value)
            {
                if (parent.left.value == cur.value) {
                    parent.left.value = null;
                }
                else {
                    parent.right.value = null;
                }
            }
            // корень
            else {
                root.value = null;
            }
        }

        // Элемент имеет одного потомка
        else if ((cur.left.value != null && cur.right.value == null)||(cur.left.value == null && cur.right.value != null))
        {
            // выбираем дочерний узел
            TaskB<E> child;
            if (cur.left.value != null)
                child = cur.left;
            else
                child = cur.right;

            // не корень
            if (cur.value != root.value)
            {
                if (cur.value == parent.left.value) {
                    parent.left = child;
                }
                else {
                    parent.right = child;
                }
            }
            // корень
            else {
                root = child;
            }
        }
        // Элемент имеет двух потомков
        else {
            TaskB<E> newChild = getMinimumKey(cur.right);
            E newValue = newChild.value;
            deleteNode(root, newChild.value);

            // копируем значение преемника в текущий узел
            cur.value = newValue;
        }
        return root;
    }
    @Override

    public boolean remove(Object o) {
        TaskB<E> root = deleteNode(this,(E)o);
        this.SetKey( root);
        return root.value == this.value;
    }

    @Override
    public boolean contains(Object o) {
        if (this.value == null){
            return false;
        }
        TaskB<E> cur = this;
        while (cur.value != null) {
            if (cur.value.equals(o)){
                return true;
            }
            if (cur.value.hashCode() > o.hashCode()) {
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        Stack<E> stack = new Stack<>();
        final TaskB<E>[] curr = new TaskB[]{this};
        Iterator<E> iterator = new Iterator<E>() {
            @Override
            public boolean hasNext() {

                return (!stack.isEmpty() || curr[0].value != null);
            }

            @Override
            public E next() {
                while (curr[0].value != null) {
                    stack.push(curr[0].value);
                    curr[0] = curr[0].left;
                }

                curr[0] = (TaskB<E>) stack.pop();
                TaskB<E> node = curr[0];
                curr[0] = curr[0].right;

                return node.value;
            }
        };
        return iterator;
    }

    @Override
    public void clear() {
        if(this.value != null){
            this.left.clear();
            this.right.clear();
            this.value = null;
            this.left = null;
            this.right = null;
        }
    }

    @Override
    public boolean isEmpty() {
        return this.value == null;
    }
    @Override
    public int size() {
        int s = 0;
        if (this.value == null){
            return s;
        }
        TaskB<E> l = this.left;
        TaskB<E> r = this.right;
        s++;
        if (l.value != null){
            s += l.size();
        }
        if (r.value != null){
            s += r.size();
        }
        return s;
    }

    @Override
    public E first() {
        TaskB<E> curr = this;
        while(curr.left.value != null){
            curr = curr.left;
        }
        return curr.value;
    }

    @Override
    public E last() {
        TaskB<E> curr = this;
        while(curr.right.value != null){
            curr = curr.right;
        }
        return curr.value;
    }


    private void subString(StringBuilder sb) {
        if(this.value == null){
            return;
        }
        this.left.subString(sb);
        sb.append(this.value);
        sb.append(", ");
        this.right.subString(sb);
    }
    @Override
    public String toString() {
        if (this.value == null){
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        left.subString(sb);
        sb.append(this.value);
        sb.append(", ");
        right.subString(sb);
        sb.delete(sb.length() - 2,sb.length());
        sb.append("]");
        return sb.toString();
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
