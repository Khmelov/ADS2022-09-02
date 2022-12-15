package by.it.group151004.afanasenko.lesson05;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_CountSort {
	public int[] countSort(InputStream stream) throws FileNotFoundException {
		try (Scanner scanner = new Scanner(stream)) {
			int n = scanner.nextInt();
			int[] points = new int[n];
			for (int i = 0; i < n; i++) {
				points[i] = scanner.nextInt();
			}
			int min, max = min = points[0];
			for (int i = 1; i < n; i++) {
				if (points[i] < min) {
					min = points[i];
				}
				if (points[i] > max) {
					max = points[i];
				}
			}
			int[] count = new int[max - min + 1];
			for (int i = 0; i < n; i++) {
				count[points[i] - min]++;
			}
			int index = 0;
			for (int i = 0; i < count.length; i++) {
				for (int j = 0; j < count[i]; j++) {
					points[index] = i + min;
					index++;
				}
			}
			return points;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		String root = System.getProperty("user.dir") + "/src/";
		InputStream stream = new FileInputStream(root + "by/it/group151004/afanasenko/lesson05/dataB.txt");
		B_CountSort instance = new B_CountSort();
		int[] result = instance.countSort(stream);
		for (int index : result) {
			System.out.print(index + " ");
		}
	}
}