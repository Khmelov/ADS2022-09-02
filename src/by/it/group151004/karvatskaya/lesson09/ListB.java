package by.it.group151004.karvatskaya.lesson09;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
public class ListB<T> implements List<T> {

    public Object[] mas = new Object[10];
    int razm = 0;


    public ListB() {
        this.mas = new Object[10];
    }

    @Override
    public boolean add(T t) {
        if (razm == mas.length) {
            Object[] mas2 = new Object[mas.length * 2];
            System.arraycopy(mas, 0, mas2, 0, mas.length);
            mas = mas2;
        }
        mas[razm++] = t;
        return true;
    }

    @Override
    public T remove(int index) {
        T y;
        y = (T) mas[index];
        if (mas.length - 1 - index >= 0) System.arraycopy(mas, index + 1, mas, index, mas.length - 1 - index);
        mas[razm - 1] = null;
        razm--;
        return y;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < razm - 1; i++)
            result.append(mas[i]).append(", ");
        result.append(mas[razm - 1]).append("]");
        return result.toString();
    }

    @Override
    public T get(int index) {
        return (T) mas[index];
    }

    @Override
    public T set(int index, T element) {
        T x;
        x = (T) mas[index];
        mas[index] = element;
        return x;
    }

    @Override
    public void add(int index, T element) {

        System.arraycopy(mas, index,
                mas, index + 1, razm - index);
        mas[index] = element;
        razm ++;

    }


    @Override
    public boolean addAll(Collection<? extends T> c) {
        Object[] array2 = new Object[mas.length + c.size()];
        System.arraycopy(mas, 0, array2, 0, razm);
        mas = array2;
        for (Object elem : c)
            mas[razm++] = elem;
        return true;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < mas.length; i++)
            if (o == mas[i])
                return i;
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public int size() {
        return razm;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
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
    public void clear() { }

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
