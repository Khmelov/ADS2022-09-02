package by.it.group151004.burbouski.lesson03;

import java.io.*;
import java.util.*;

public class C_HeapMax {
	public class MaxHeap {
		public List<Long> heap = new ArrayList<>();

		public void swap(int index1, int index2) {
			Long temp = heap.get(index2);
			heap.set(index2, heap.get(index1));
			heap.set(index1, temp);
		}

		public boolean isInHeap(int i) {
			return i < heap.size();
		}

		public int biggestChild(int i) {
			int left = i * 2 + 1, right = i * 2 + 2;
			if (isInHeap(left) && isInHeap(right)) {
				return heap.get(left) > heap.get(right) ? left : right;
			}
			return isInHeap(left) ? left : isInHeap(right) ? right : i;
		}

		public int siftDown(int i) {
			while (heap.get(i) < heap.get(biggestChild(i))) {
				swap(i, biggestChild(i));
			}
			return i;
		}

		public int siftUp(int i) {
			int j = (i - 1) / 2;
			while (heap.get(i) > heap.get(j)) {
				swap(i, j);
				i = j;
			}
			return i;
		}

		public void insert(Long value) {
			heap.add(value);
			siftUp(heap.size() - 1);
		}

		public Long extractMax() {
			Long result = heap.get(0);
			heap.set(0, heap.get(heap.size() - 1));
			heap.remove(heap.size() - 1);
			siftDown(0);
			return result;
		}
	}

	public Long findMaxValue(InputStream stream) {
		Long maxValue = 0L;
		MaxHeap heap = new MaxHeap();
		try (Scanner scanner = new Scanner(stream)) {
			Integer count = scanner.nextInt();
			for (int i = 0; i < count;) {
				String s = scanner.nextLine();
				if ("extractMax".equalsIgnoreCase(s)) {
					Long res = heap.extractMax();
					if (res != null && res > maxValue) {
						maxValue = res;
					}
					System.out.println();
					i++;
				}
				if (s.contains(" ")) {
					String[] p = s.split(" ");
					if ("insert".equalsIgnoreCase(p[0])) {
						heap.insert(Long.parseLong(p[1]));
					}
					i++;
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return maxValue;
	}

	public static void main(String[] args) throws FileNotFoundException {
		String root = System.getProperty("user.dir") + "/src/";
		InputStream stream = new FileInputStream(root + "by/it/group151004/burbouski/lesson03/heapData.txt");
		C_HeapMax instance = new C_HeapMax();
		System.out.println("MAX=" + instance.findMaxValue(stream));
	}
}
