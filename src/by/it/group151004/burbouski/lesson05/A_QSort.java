package by.it.group151004.burbouski.lesson05;

import java.io.*;
import java.util.Scanner;

public class A_QSort {
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

	public int[] getAccessory(InputStream stream) throws FileNotFoundException {
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
			for (int i = 0; i < m; i++) {
				result[i] = getAccessoryCount(segments, points[i]);
			}
			return result;
		}
	}

	public int getAccessoryCount(Segment[] segments, int point) {
		int count = 0;
		for (Segment segment : segments) {
			if (segment.start <= point && segment.stop >= point) {
				count++;
			}
		}
		return count;
	}

	public void quickSort(Segment[] segments, int left, int right) {
		if (left < right) {
			int q = partition(segments, left, right);
			quickSort(segments, left, q - 1);
			quickSort(segments, q + 1, right);
		}
	}

	public int partition(Segment[] segments, int left, int right) {
		Segment x = segments[right];
		int j = left - 1;
		for (int k = left; k < right; k++) {
			if (segments[k].compareTo(x) <= 0) {
				j++;
				Segment temp = segments[j];
				segments[j] = segments[k];
				segments[k] = temp;
			}
		}
		Segment temp = segments[j + 1];
		segments[j + 1] = segments[right];
		segments[right] = temp;
		return j + 1;
	}

	public static void main(String[] args) throws FileNotFoundException {
		String root = System.getProperty("user.dir") + "/src/";
		InputStream stream = new FileInputStream(root + "by/it/group151004/burbouski/lesson05/dataA.txt");
		A_QSort instance = new A_QSort();
		int[] result = instance.getAccessory(stream);
		for (int index : result) {
			System.out.print(index + " ");
		}
	}
}