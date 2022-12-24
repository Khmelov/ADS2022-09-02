package by.it.group151001.pastukhou.lesson10;

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
        }else{
            if (this.value.hashCode() < e.hashCode()){
                return this.right.add(e);
            }
            if (this.value.hashCode() > e.hashCode()){
                return this.left.add(e);
            }
        }
        return false;
    }
    public TaskB<E> getMinimumKey(TaskB<E> curr)
    {
        while (curr.left.value != null) {
            curr = curr.left;
        }
        return curr;
    }
    public TaskB<E> deleteNode(TaskB<E> root, E key)
    {

        TaskB<E> parent = null;

        TaskB<E> curr = root;
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

            TaskB<E> successor = getMinimumKey(curr.right);

            E val = successor.value;

            deleteNode(root, successor.value);

            curr.value = val;
        }

        else {

            TaskB<E> child = (curr.left.value != null)? curr.left: curr.right;

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
        {
            TaskB<E> curr = this;
            while (curr.value != null) {
                if (curr.value.equals(o)){
                    return true;
                }
                if (curr.value.hashCode() > o.hashCode()) {
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
            }
            return false;
        }
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
        return;
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
