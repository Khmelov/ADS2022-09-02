package by.it.group151004.karvatskaya.lesson09;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

 public class ListA<T> implements List<T> {

    public Object[] mas  = new Object[10];
    int razm = 0;

    public ListA(){
        this.mas=new Object[10];
    }

    @Override
    public boolean add(T t) {
        if(razm== mas.length){
            Object[] mas2=new Object[mas.length*2];
            System.arraycopy(mas,0,mas2,0,razm);
            mas=mas2;
        }
        mas[razm++]=t;
        return true;
    }

    @Override
    public String toString(){
        StringBuilder result=new StringBuilder("[");
        for(int i=0;i<razm-1;i++){
            result.append(mas[i]).append(", ");
        }
        result.append(mas[razm-1]).append("]");
        return result.toString();
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        return (T) mas[index];
    }

    @Override
    public T set(int index, T element) {
        return null;
    }

    @Override
    public void add(int index, T element) {

    }

    @Override
    public T remove(int index) {
        T y;
        y = (T)mas[index];
        if (mas.length-1-index>=0) System.arraycopy(mas, index+1, mas, index, mas.length-1-index);
        mas[razm-1]=null;
        razm--;
        return y;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }
}