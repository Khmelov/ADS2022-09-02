package by.it.group151001.loziuk.lesson10;

import java.util.*;

public class TaskA<E>  implements NavigableSet<E> {
    private E Value;
    private TaskA<E> Left;
    private TaskA<E> Right;
    private void SetKey(TaskA<E> elem){
        this.Value = elem.Value;
        this.Left = elem.Left;
        this.Right = elem.Right;
    }
    //Создайте БЕЗ использования других классов (включая абстрактные)
    //аналог дерева TreeSet

    //Обязательные к реализации методы и конструкторы
    public TaskA() {
        this.Value = null;
        this.Left = null;
        this.Right = null;
    }

    public static void main(String[] args) {
        TaskA<Integer> tree = new TaskA<>();
        tree.add(8);
        tree.add(7);
    }

    @Override
    public boolean add(E e) {
        if (this.Value == null){
            this.Value = e;
            this.Left = new TaskA<>();
            this.Right = new TaskA<>();
            return true;
        }
        else{
            if(e.hashCode() > this.Value.hashCode())
                return this.Right.add(e);
            else
                return this.Left.add(e);
        }
    }

    public TaskA<E> getMinChild(TaskA<E> temp)
    {
        while (temp.Left.Value != null) {
            temp = temp.Left;
        }
        return temp;
    }

    public TaskA<E> delete(TaskA<E> root, E deleting){
        TaskA<E> pred = null;
        TaskA<E>  temp = root;
        while (temp.Value != null && !temp.Value.equals(deleting)){
            pred = temp;
            if (deleting.hashCode() > temp.Value.hashCode())
                temp = temp.Right;
            else
                temp = temp.Left;
        }
        if (temp.Value == null)
            return root;
        else{
            //лист
            if(temp.Left.Value == null && temp.Right.Value == null){
                //не корень
                if (temp.Value != root.Value){
                    if(pred.Left.Value == temp.Value)
                        pred.Left.Value = null;
                    else
                        pred.Right.Value = null;
                }
                //корень
                else
                    root.Value = null;
            }
            else{
                //1 ребенок
                if ((temp.Left.Value != null && temp.Right.Value == null) || (temp.Left.Value == null && temp.Right.Value != null)){
                    TaskA<E> child;
                    if (temp.Left.Value == null)
                        child = temp.Right;
                    else
                        child = temp.Left;

                    //не корень
                    if (temp.Value != root.Value){
                        if(pred.Left.Value == temp.Value)
                            pred.Left = child;
                        else
                            pred.Right = child;
                    }
                    //корень
                    else
                        root = child;
                }
                // 2 ребенка
                else{
                    TaskA<E> min = getMinChild(temp.Right);
                    E newValue = min.Value;
                    delete(root,newValue);
                    temp.Value = newValue;
                }
            }
        }
        return root;
    }

    @Override
    public boolean remove(Object o) {
        TaskA<E> root = delete(this,(E)o);
        this.SetKey(root);
        return root.Value == this.Value;
    }

    private void subString(StringBuilder sb) {
        if(this.Value == null){
            return;
        }
        this.Left.subString(sb);
        sb.append(this.Value);
        sb.append(", ");
        this.Right.subString(sb);
    }

    @Override
    public String toString() {
        if (this.Value == null){
            return "[ ]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Left.subString(sb);
        sb.append(this.Value);
        sb.append(", ");
        Right.subString(sb);
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
