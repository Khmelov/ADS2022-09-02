package by.it.group151003.matoshko.lesson09;

import java.util.*;
import java.util.function.UnaryOperator;


public class ListA<T> implements List<T> {
    private T[] m_elements;
    private int m_size;

    // Если в массиве 0 элементов, то конструктор с длинной "динамического массива" равной 0
    public ListA() {
        m_size = 0;
        m_elements = (T[]) new Object[0];
    }

    // Если в качестве параметра принимается сразу коллекция элементов
    public ListA(Collection<? extends T> newElements) {
        // Сохраняем эти элементы
        Object[] elementsCopy = newElements.toArray();
        // Устанавливаем длинну List равной количеству входящих элементов
        m_size = elementsCopy.length;
        // Копируем входящие элементы в наш List
        m_elements = Arrays.copyOf((T[])elementsCopy, elementsCopy.length);
    }

    // Конструктор с известной заранее необходимой длиной
    public ListA(int initialSize) {
        // Устанавливаем этот размер
        m_size = initialSize;
        // Создаем сами пустые элементы
        m_elements = (T[]) new Object[initialSize];
    }

    @Override
    // Добавление элемента в конец. Принимает просто сам элемент
    public boolean add(T element) {
        // Если элемент не равен null
        if (element != null) {
            // Копируем исходный List на новое место с увеличенным размером
            m_elements = Arrays.copyOf(m_elements, m_elements.length + 1);
            // На новое место ставим элемент
            m_elements[m_size++] = element;
            return true;
        }
        return false;
    }

    @Override
    // Добавление элемента по опр. индексу. Принимает элемент и индекс его вставки
    public void add(int index, T element) {
        // Увеличение размера List на единицу
        m_elements = Arrays.copyOf(m_elements, m_elements.length + 1);
        // Копирование элементов правее индекса вставки вправо( со сдвигом элементов на 1, чтобы вставить элемент)
        // на входящий индекс
        System.arraycopy(m_elements, index, m_elements, index + 1, m_size - index);
        // Вставка самого индекса
        m_elements[index] = element;
        // Сохранение нового размера
        m_size++;
    }

    @Override
    // Удаление элемента по индексу
    public T remove(int index) {
        T returnValue = m_elements[index];
        // Копирование элементов правее удаляемого со сдвигом влево для удаления
        System.arraycopy(m_elements, index + 1, m_elements, index, m_size - index-1);
        // Уменьшение размера
        m_elements[--m_size] = null;
        return returnValue;
    }

    @Override
    // Получение элемента по индексу
    public T get(int index) {
        return m_elements[index];
    }

    @Override
    // Установка нового значение вместо старого по индексу
    public T set(int index, T element) {
        T oldValue = m_elements[index];
        m_elements[index] = element;
        return oldValue;
    }

    @Override
    public String toString() {
        // Обработчик строк
        StringJoiner txt = new StringJoiner(", ", "[", "]"); // Вывод в формате: [element1, element2, element3, ..., elementN]
       // Проход по всем элементам List
       for (int i = 0; i < m_size; i++) {
           // Если элемент не равен 0, то он добавляется в обработчик
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
    public void clear() { m_size = 0; }

    @Override
    public void replaceAll(UnaryOperator<T> operator) { }

    @Override
    public void sort(Comparator<? super T> c) { }

    @Override
    public Spliterator<T> spliterator() {
        return null;
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