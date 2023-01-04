package by.it.group151004.karvatskaya.lesson10;

import java.util.*;

public class TaskC<E>  implements NavigableSet<E> {

    private class Nodus{
        Nodus lil = null;
        Nodus big = null;
        Nodus old;
        E value;
        public Nodus(E value, Nodus old){
            this.value = value;
            this.old = old;
        }
        public int compareTo(Object o){
            return Integer.compare((int)value ,(int)o);
        }
    }

    private Nodus root;

    private List<E> nodesList;

    private int razm = 0;

    public TaskC() {
        nodesList = new ArrayList<E>();
        root = new Nodus(null, null);
    }

    private Nodus findNode(E o){
        Nodus nodus = root;
        while(nodus != null){
            if(nodus.compareTo(o) > 0)
                nodus = nodus.lil;
            else if(nodus.compareTo(o) < 0)
                nodus = nodus.big;
            else
                return nodus;
        }
        return null;
    }
    @Override
    public boolean add(E e) {
        if(razm == 0){
            root.value = e;
            root.old= root;
            razm++;
            return true;
        }
        Nodus nodus = root;
        while(true){
            if(nodus.compareTo(e) > 0){
                if(nodus.lil == null){
                    nodus.lil = new Nodus(e, nodus);
                    razm++;
                    return true;
                }
                else
                    nodus = nodus.lil;
            }
            else if(nodus.compareTo(e) < 0){
                if(nodus.big == null){
                    nodus.big = new Nodus(e, nodus);
                    razm++;
                    return true;
                }else
                    nodus = nodus.big;
            }
            else
                return false;
        }
    }

    private void delete(Nodus nodus) {
        if(nodus.big == null && nodus.lil == null){
            if(nodus.old.lil == nodus)
                nodus.old.lil = null;
            else
                nodus.old.big = null;
        }else if(nodus.big == null){
            nodus.lil.old = nodus.old;
            if(nodus.old.lil == nodus){
                nodus.old.lil = nodus.lil;
            }else{
                nodus.old.big = nodus.lil;
            }
            if(nodus == root){
                root = nodus.lil;
            }
        }
        else if(nodus.lil == null){
            nodus.big.old = nodus.old;
            if(nodus.old.lil == nodus){
                nodus.old.lil = nodus.big;
            }else{
                nodus.old.big = nodus.big;
            }
            if(nodus == root){
                root = nodus.big;
            }
        }
        else{
            Nodus res = nodus.big;
            while(res.lil != null){
                res = res.lil;
            }
            nodus.value = res.value;
            delete(res);
        }
    }

    @Override
    public boolean remove(Object o) {
        Nodus nodus = findNode((E)o);
        if(nodus != null){
            delete(nodus);
            razm--;
        }
        return true;
    }

    private void treeToList(Nodus nodus){
        if(nodus != null){
            treeToList(nodus.lil);
            nodesList.add(nodus.value);
            treeToList(nodus.big);
        }

    }

    @Override
    public String toString() {
        if(razm == 0){
            return "[]";
        }
        nodesList.clear();
        treeToList(root);
        String outputText = "[";
        for(E value : nodesList){
            outputText = outputText + value + ", ";
        }
        outputText = outputText.substring(0, outputText.length() - 2) + ']';
        return outputText;
    }
    @Override
    public boolean contains(Object o) {
        return findNode((E)o) != null;
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

        SetIterator() {
            nodus = null;
            nxt = null;
        }

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
        public T next() {
            if (nxt == null)
                return null;
            nodus = nxt;
            return (T) nxt.value;
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new SetIterator<>();
    }

    @Override
    public void clear() {
        root = new Nodus(null, null);
        razm = 0;
    }

    @Override
    public boolean isEmpty() {
        return root.value == null;
    }

    @Override
    public int size() {
        return razm;
    }

    @Override
    public E first() {
        Nodus nodus = root;
        while(nodus.lil != null){
            nodus = nodus.lil;
        }
        return nodus.value;
    }

    @Override
    public E last() {
        Nodus nodus = root;
        while(nodus.big != null){
            nodus = nodus.big;
        }
        return nodus.value;
    }

    @Override
    public E lower(E e) {
        nodesList.clear();
        treeToList(root);
        for(int i = nodesList.size() - 1; i >= 0; i--){
            if((int) nodesList.get(i) < (int)e){
                return nodesList.get(i);
            }
        }
        return null;
    }

    @Override
    public E floor(E e) {
        nodesList.clear();
        treeToList(root);
        for(int i = nodesList.size() - 1; i >= 0; i--){
            if((int) nodesList.get(i) <= (int)e){
                return nodesList.get(i);
            }
        }
        return null;
    }

    @Override
    public E ceiling(E e) {
        nodesList.clear();
        treeToList(root);
        for(E value : nodesList){
            if((int)value >= (int)e){
                return value;
            }
        }
        return null;
    }

    @Override
    public E higher(E e) {
        nodesList.clear();
        treeToList(root);
        for(E value : nodesList){
            if((int)value > (int)e){
                return value;
            }
        }
        return null;
    }

    @Override
    public E pollFirst() {
        Nodus nodus = root;
        while(nodus.lil != null){
            nodus = nodus.lil;
        }
        E result = nodus.value;
        delete(nodus);
        razm--;
        return result;
    }

    @Override
    public E pollLast() {
        Nodus nodus = root;
        while(nodus.big != null){
            nodus = nodus.big;
        }
        E result = nodus.value;
        delete(nodus);
        razm--;
        return result;
    }
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////
    ////////         Эти методы реализовывать необязательно      ////////////
    /////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////

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
