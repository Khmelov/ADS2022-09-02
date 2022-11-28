package by.it.group151003.patiyuk.lesson09;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class SetC<T> implements Set<T> {
    private static final int DEFAULT_CAPACITY = 10;
    private static final float LOAD_FACTOR = 0.75f;
    private static final Object NULL = new Object();

    private Object[] array;
    private int size = 0;
    private int capacity;

    public SetC() {
        this(DEFAULT_CAPACITY);
    }

    public SetC(int capacity) {
        this.capacity = capacity;
        array = new Object[capacity];
    }

    public SetC(Collection<? extends T> c) {
        this(c.size());
        this.addAll(c);
    }

    private void resize(int newCapacity) {
        Object[] newArray = new Object[newCapacity];
        for (Object element : array) {
            if (element != null) {
                int index = Math.abs(element.hashCode()) % newCapacity;
                while (newArray[index] != null) {
                    index = (index + 1) % newCapacity;
                }
                newArray[index] = element;
            }
        }
        array = newArray;
        capacity = newCapacity;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public boolean contains(Object o) {
           if (o == null) {
                for (Object element : array) {
                    if (element == NULL) {
                        return true;
                    }
                }
            } else {
                int index = Math.abs(o.hashCode()) % capacity;
                while (array[index] != null) {
                    if (array[index].equals(o)) {
                        return true;
                    }
                    index = (index + 1) % capacity;
                }
            }
            return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                while (index < capacity && array[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public T next() {
                return (T) array[index++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];
        int index = 0;
        for (Object element : array) {
            if (element != null) {
                newArray[index++] = element == NULL ? null : element;
            }
        }
        return newArray;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            a = (T1[]) Arrays.copyOf(toArray(), size, a.getClass());
        } else {
            System.arraycopy(toArray(), 0, a, 0, size);
            if (a.length > size) {
                a[size] = null;
            }
        }
        return a;
    }

    @Override
    public boolean add(T t) {
        if (t == null) {
            if (contains(null)) {
                return false;
            }
            t = (T) NULL;
        }
        if (size >= capacity * LOAD_FACTOR) {
            resize(capacity * 2);
        }
        int index = Math.abs(t.hashCode()) % capacity;
        while (array[index] != null) {
            if (array[index].equals(t)) {
                return false;
            }
            index = (index + 1) % capacity;
        }
        array[index] = t;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null) {
            for (int i = 0; i < capacity; i++) {
                if (array[i] == null) {
                    array[i] = null;
                    size--;
                    return true;
                }
            }
        } else {
            int index = Math.abs(o.hashCode()) % capacity;
            while (array[index] != null) {
                if (array[index].equals(o)) {
                    array[index] = null;
                    size--;
                    return true;
                }
                index = (index + 1) % capacity;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object element : c) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean result = false;
        for (T element : c) {
            if (add(element)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean result = false;
        for (Object element : array) {
            if (element != null && !c.contains(element)) {
                remove(element);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean result = false;
        for (Object element : c) {
            if (remove(element)) {
                result = true;
            }
        }
        return result;
    }

    @Override
    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Object element : array) {
            if (element != null) {
                if (element == NULL) {
                    sb.append("null");
                } else {
                    sb.append(element);
                }
                sb.append(", ");
            }
        }
        if (sb.length() > 1) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }
}
