package by.it.group151003.onuchina.lesson09;

import java.util.*;

public class SetC <T> implements Set<T> {
    private T[] elems;
    private int size;

    public SetC() {
        size = 0;
        elems = (T[]) new Object[0];
    }

    public SetC(Collection<? extends T> new_elems) {
        size = 0;
        Object[] elems_copy = new_elems.toArray();
        for (Object elem : elems_copy) {
            if (!this.contains(elem)) {
                this.add((T)elem);
            }
        }
    }

    @Override
    public boolean add(T elem_to_add) {
        boolean was_add = true;

        if (elems.length == 0) {
            elems = Arrays.copyOf(elems, elems.length + 1);
            elems[size++] = elem_to_add;
            return true;
        } else {
            for (T elem : elems) {
                if (elem == null) {
                    if (elem == elem_to_add) {
                        was_add = false;
                        break;
                    }
                } else {
                    if (elem.equals(elem_to_add)) {
                        was_add = false;
                        break;
                    }
                }
            }
        }
        if (was_add) {
            elems = Arrays.copyOf(elems, elems.length + 1);
            elems[size++] = elem_to_add;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> elements) {
        T[] elems_copy = (T[]) elements.toArray();
        boolean was_add = false;
        for (T element : elems_copy) {
            if (add(element)) {
                was_add = true;
            }
        }
        return was_add;
    }

    @Override
    public boolean remove(Object object) {
        boolean remove_switch = false;
        int index = 0;
        if (elems.length == 0) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (elems[i] == null) {
                if (object == elems[i]) {
                    remove_switch = true;
                    index = i;
                    break;
                }
            } else {
                if (elems[i].equals(object)) {
                    remove_switch = true;
                    index = i;
                    break;
                }
            }
        }
        if (remove_switch) {
            System.arraycopy(elems, index + 1, elems, index, size - (index + 1));
            size--;
            return true;
        }
        return false;
    }


    @Override
    public boolean removeAll(Collection<?> elems_to_remove) {
        boolean was_changed = false;
        for (Object elem : elems_to_remove) {
            if (remove(elem)) {
                was_changed = true;
            }
        }
        return was_changed;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int current = -1;

            @Override
            public boolean hasNext() {
                return current + 1 < size;
            }

            @Override
            public T next() {
                return elems[++current];
            }

            @Override
            public void remove() {
                by.it.group151003.onuchina.lesson09.SetC.this.remove(elems[current]);
            }
        };
    }

    @Override
    public boolean contains(Object object) {
        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            if (object == null) {
                if (iterator.next() == object) {
                    return true;
                }
            } else {
                if (object.equals(iterator.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> elements) {
        for (Object elems : elements) {
            if (!contains(elems)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(",", "[", "]");
        for (int i = 0; i < size; i++) {
            if (elems[i] != null) {
                output.add(elems[i].toString());
            } else {
                output.add(null);
            }
        }
        return output.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Spliterator<T> spliterator() {
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
    public boolean retainAll(Collection<?> c) {
        return false;
    }

}
