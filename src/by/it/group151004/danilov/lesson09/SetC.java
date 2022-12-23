package by.it.group151004.danilov.lesson09;

import java.util.*;

public class SetC<T> implements Set<T> {
    private T[] m_elements;
    private int m_size;

    public SetC() {
        m_size = 0;
        m_elements = (T[]) new Object[0];
    }

    public SetC(Collection<? extends T> newElements) {
        m_size = 0;
        Object[] elementsCopy = newElements.toArray();
        for (Object element : elementsCopy) {
            if (!this.contains(element)) {
                this.add((T) element);
            }
        }
    }

    @Override
    public boolean add(T addElement) {
        boolean isAdd = true;

        if (m_elements.length == 0) {
            m_elements = Arrays.copyOf(m_elements, m_elements.length + 1);
            m_elements[m_size++] = addElement;
            return true;
        } else {
            for (T element : m_elements) {
                if (element == null) {
                    if (element == addElement) {
                        isAdd = false;
                        break;
                    }
                } else {
                    if (element.equals(addElement)) {
                        isAdd = false;
                        break;
                    }
                }
            }
        }
        if (isAdd) {
            m_elements = Arrays.copyOf(m_elements, m_elements.length + 1);
            m_elements[m_size++] = addElement;
            return true;
        }
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> elements) {
        T[] elementsCopy = (T[]) elements.toArray();
        boolean isAdd = false;
        for (T element : elementsCopy) {
            if (add(element)) {
                isAdd = true;
            }
        }
        return isAdd;
    }

    @Override
    public boolean remove(Object object) {
        boolean removeSwitch = false;
        int index = 0;
        if (m_elements.length == 0) {
            return false;
        }
        for (int i = 0; i < m_size; i++) {
            if (m_elements[i] == null) {
                if (object == m_elements[i]) {
                    removeSwitch = true;
                    index = i;
                    break;
                }
            } else {
                if (m_elements[i].equals(object)) {
                    removeSwitch = true;
                    index = i;
                    break;
                }
            }
        }
        if (removeSwitch) {
            System.arraycopy(m_elements, index + 1, m_elements, index, m_size - (index + 1));
            m_size--;
            return true;
        }
        return false;
    }


    @Override
    public boolean removeAll(Collection<?> removeElements) {
        boolean isChanged = false;
        for (Object element : removeElements) {
            if (remove(element)) {
                isChanged = true;
            }
        }
        return isChanged;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int current = -1;

            @Override
            public boolean hasNext() {
                return current + 1 < m_size;
            }

            @Override
            public T next() {
                return m_elements[++current];
            }

            @Override
            public void remove() {
                SetC.this.remove(m_elements[current]);
            }
        };
    }

    @Override
    public boolean contains(Object object) {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            if (object == null) {
                if (it.next() == object) {
                    return true;
                }
            } else {
                if (object.equals(it.next())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> elements) {
        for (Object element : elements) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringJoiner txt = new StringJoiner(",", "[", "]");
        for (int i = 0; i < m_size; i++) {
            if (m_elements[i] != null) {
                txt.add(m_elements[i].toString());
            } else {
                txt.add(null);
            }
        }
        return txt.toString();
    }

    @Override
    public int size() {
        return m_size;
    }

    @Override
    public boolean isEmpty() {
        return m_size == 0;
    }

    @Override
    public void clear() {
        m_size = 0;
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
