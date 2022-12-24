package by.it.group151004.karvatskaya.lesson09;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T> {

    public Object[] mas= new Object[16];
    int razm=0;

    public SetC(){
        razm=0;
        this.mas=new Object[16];
    }
    @Override
    public String toString() {
        if (razm < 1) return "[]";
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < razm - 1; i++) {
            result.append(mas[i]).append(", ");
        }
        result.append(mas[razm - 1]).append("]");
        return result.toString();
    }

    @Override
    public int size() {
        return razm;
    }


    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (Object element : mas)
            if (element == o) return true;
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
    public boolean add(T t) {
        if (contains(t)) return false;
        if (razm == mas.length) {
            Object[] array2 = new Object[mas.length * 2];
            System.arraycopy(mas, 0, array2, 0, mas.length);
            mas = array2;
        }
        mas[razm++] = t;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o)) return false;
        int j = 0;
        for (int i = 0; i < razm; i++) {
            if (mas[i] == o)
                j = i;
        }
        if (j == 0) return false;
        if (razm - 1 - j >= 0) System.arraycopy(mas, j + 1, mas, j, razm - 1 - j);
        mas[razm--] = null;
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c)
            if (!contains(element))
                return false;
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean y = false;
        for (T element : c)
            if (add(element))
                y = true;
        return y;
    }


    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean y = false;
        for (Object element : c)
            if (remove(element))
                y = true;
        return y;
    }

    @Override
    public void clear() {
        mas = new Object[0];
        razm = 0;

    }
}