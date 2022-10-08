package by.it.group151004.burbouski.lesson05;

import java.io.*;
import java.util.Scanner;

public class C_QSortOptimized {
	public class Segment implements Comparable<Segment> {
		int start;
		int stop;

		Segment(int start, int stop) {
			this.start = start;
			this.stop = stop;
		}

		@Override
		public int compareTo(Segment o) {
			return Integer.compare(start, o.start);
		}
	}

	public int[] getAccessory2(InputStream stream) throws FileNotFoundException {
		try (Scanner scanner = new Scanner(stream)) {
			int n = scanner.nextInt();
			Segment[] segments = new Segment[n];
			int m = scanner.nextInt();
			int[] points = new int[m];
			int[] result = new int[m];
			for (int i = 0; i < n; i++) {
				segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
			}
			for (int i = 0; i < m; i++) {
				points[i] = scanner.nextInt();
			}
			quickSort(segments, 0, segments.length - 1);
			for (int i = 0; i < points.length; i++) {
				int index = binarySearch(segments, points[i]);
				if (index == -1) {
					result[i] = 0;
				} else {
					int count = 1;
					int j = index - 1;
					while (j >= 0 && segments[j].start == segments[index].start) {
						count++;
						j--;
					}
					j = index + 1;
					while (j < segments.length && segments[j].start == segments[index].start) {
						count++;
						j++;
					}
					result[i] = count;
				}
			}
			return result;
		}
	}

	public int binarySearch(Segment[] segments, int point) {
		int left = 0;
		int right = segments.length - 1;
		int middle = 0;
		while (left <= right) {
			middle = (left + right) / 2;
			if (segments[middle].start <= point && segments[middle].stop >= point) {
				return middle;
			}
			if (segments[middle].start > point) {
				right = middle - 1;
			} else {
				left = middle + 1;
			}
		}
		return -1;
	}

	public void quickSort(Segment[] segments, int left, int right) {
		while (left < right) {
			int[] pivots = partition(segments, left, right);
			if (pivots[0] - left < right - pivots[1]) {
				quickSort(segments, left, pivots[0] - 1);
				left = pivots[1] + 1;
			} else {
				quickSort(segments, pivots[1] + 1, right);
				right = pivots[0] - 1;
			}
		}
	}

	public int[] partition(Segment[] segments, int left, int right) {
		int pivot = left + (int) (Math.random() * (right - left + 1));
		int pivotValue = segments[pivot].start;
		int i = left;
		int j = right;
		int k = left;
		while (k <= j) {
			if (segments[k].start < pivotValue) {
				swap(segments, i, k);
				i++;
				k++;
			} else if (segments[k].start > pivotValue) {
				swap(segments, k, j);
				j--;
			} else {
				k++;
			}
		}
		return new int[] { i, j };
	}

	public void swap(Segment[] segments, int i, int j) {
		Segment tmp = segments[i];
		segments[i] = segments[j];
		segments[j] = tmp;
	}

	public static void main(String[] args) throws FileNotFoundException {
		String root = System.getProperty("user.dir") + "/src/";
		InputStream stream = new FileInputStream(root + "by/it/a_khmelev/lesson05/dataC.txt");
		C_QSortOptimized instance = new C_QSortOptimized();
		int[] result = instance.getAccessory2(stream);
		for (int index : result) {
			System.out.print(index + " ");
		}
	}
}