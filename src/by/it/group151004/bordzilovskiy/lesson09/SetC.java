package by.it.group151004.bordzilovskiy.lesson09;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T> {
    public Object[] A = new Object[10];
    public int amount = 0;

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("[");
        for (int i = 0; i < amount; i++) {
            s.append(A[i]);
            s.append(", ");
        }
        if (amount!=0) {
            s.delete(s.length() - 2, s.length());
        }
        s.append("]");
        return s.toString();
    }

    @Override
    public int size() {
        return amount;
    }

    @Override
    public boolean isEmpty() {
        if(amount==0) return true;
        return false;
    }

    @Override
    public boolean contains(Object o) {
        for(int i=0;i<A.length;i++){
            if(A[i]==o){return true;}
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return A;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        if(!contains(t)){
            if (amount == A.length - 1) {
                Object[] newA = new Object[A.length * 2];
                System.arraycopy(A, 0, newA, 0, amount);
                A = newA;
            }
            A[amount] = t;
            amount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        if(contains(o)){
            int index=-1;
            for (int i = 0; i < amount; i++) {
                if (o == A[i]) {
                    index = i;
                }
            }
            if (index == -1) {
                return false;
            }
            for (int i = 0; i < amount; i++) {
                if (o == A[i]) {
                    index = i;
                }
            }
            for (int i = index; i < amount; i++) {
                A[i] = A[i + 1];
            }
            A[amount] = null;
            amount--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean res = false;
        for (T el : c) {
            if (add(el)) {
                res = true;
            }
        }
        return res;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean res = false;
        for (Object o : c) {
            if (remove(o)) {
                res = true;
            }
        }
        return res;
    }

    @Override
    public void clear() {
        A = new Object[0];
        amount = 0;
    }
}
