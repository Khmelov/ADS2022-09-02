package by.it.group151004.afanasenko.lesson09;

import java.util.*;

public class SetC<T> implements Set<T> {
	public int DEFAULT_CAPACITY = 10;
	public Object[] arr = new Object[DEFAULT_CAPACITY];
	public int currSize = 0;

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("[");
		for (int i = 0; i < currSize; i++) {
			str.append(arr[i]);
			str.append(", ");
		}
		if (str.length() > 3) {
			str.delete(str.length() - 2, str.length());
		}
		str.append("]");
		return str.toString();
	}

	@Override
	public boolean add(T t) {
		if (!contains(t)) {
			if (currSize == arr.length - 1) {
				Object[] newArray = new Object[arr.length * 2];
				System.arraycopy(arr, 0, newArray, 0, currSize);
				arr = newArray;
			}
			arr[currSize++] = t;
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object o) {
		if (!contains(o)) {
			return false;
		}
		int index = -1;
		for (int i = 0; i < currSize; i++) {
			if (o == arr[i]) {
				index = i;
			}
		}
		if (index == -1) {
			return false;
		}
		for (int i = index; i < currSize; i++) {
			arr[i] = arr[i + 1];
		}
		arr[currSize] = null;
		currSize--;
		return true;
	}

	@Override
	public boolean contains(Object o) {
		for (Object el : arr) {
			if (el == o) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int size() {
		return currSize;
	}

	@Override
	public boolean isEmpty() {
		return currSize == 0;
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
	public boolean containsAll(Collection<?> c) {
		for (Object el : c) {
			if (!contains(el)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean res = false;
		for (Object el : c) {
			if (remove(el)) {
				res = true;
			}
		}
		return res;
	}

	// допы

	@Override
	public void clear() {
		arr = new Object[0];
		currSize = 0;
	}

	@Override
	public Iterator<T> iterator() {
		return null;
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T0> T0[] toArray(T0[] a) {
		return null;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}
}