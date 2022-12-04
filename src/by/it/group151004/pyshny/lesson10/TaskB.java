package by.it.group151004.pyshny.lesson10;

import java.util.*;

public class TaskB<E>  implements NavigableSet<E> {
    public Object[] A = new Object[100];
    public int amount = 0;
    //Создайте БЕЗ использования других классов (включая абстрактные)
    //аналог дерева TreeSet

    //Обязательные к реализации методы и конструкторы
    public TaskB() {
    }

    private void sortset(){
        Object[] arr = A;
        for (int i = 0; i < amount; i++) {
            for (int j = 0; j < amount - i - 1; j++) {
                if ((int)A[j] >(int)A[j+1]) {
                    E temp=(E)A[j];
                    A[j] = A[j+1];
                    A[j+1] = temp;
                }
            }
        }
    }

    @Override
    public boolean add(E e) {
        if(!contains(e)){
            if (amount == A.length - 2) {
                Object[] newA = new Object[A.length * 2];
                System.arraycopy(A, 0, newA, 0, amount);
                A = newA;
            }
            A[amount] = e;
            amount++;
            if(amount>1) sortset();
            return true;
        }
        else return false;
    }

    @Override
    public boolean remove(Object o) {
        int remove = -1;
        for (int i = 0; i < amount; i++) {
            if (A[i] == o) {
                remove = i;
                break;
            }
        }
        if (remove == -1)
            return false;
        for (int i = remove; i < amount; i++) {
            A[i] = A[i+1];
        }
        A[amount] = null;
        amount--;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[");
        for (int i = 0; i < amount; i++) {
            str.append(A[i]);
            str.append(", ");
        }
        if (str.length() < 2) {
            str.append("]");
        } else {
            str.delete(str.length()-2, str.length());
            str.append("]");
        }
        return str.toString();
    }

    @Override
    public boolean contains(Object o) {
        for (Object el : A) {
            if (el == o ){
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> iterator = (Iterator<E>) Arrays.stream(A).iterator();
        return iterator;
    }

    @Override
    public void clear() {
        amount=0;
        A= new Object[100];
    }

    @Override
    public boolean isEmpty() {
        return (amount==0);
    }

    @Override
    public int size() {
        return amount;
    }

    @Override
    public E first() {
        if(amount>=0){
            return (E)A[0];
        }
        else return null;
    }

    @Override
    public E last() {
        if(amount>=0){
            return (E)A[amount-1];
        }
        else return null;
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
