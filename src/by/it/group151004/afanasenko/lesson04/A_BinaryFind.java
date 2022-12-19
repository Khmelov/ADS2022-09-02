package by.it.group151004.afanasenko.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class A_BinaryFind {
	public int[] findIndex(InputStream stream) throws FileNotFoundException {
		try (Scanner scanner = new Scanner(stream)) {
			int n = scanner.nextInt();
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = scanner.nextInt();
			}

			int k = scanner.nextInt();
			int[] result = new int[k];
			for (int i = 0; i < k; i++) {
				int value = scanner.nextInt();
				int left = 0;
				int right = n - 1;
				int mid = 0;
				boolean isFound = false;
				while (!isFound && left <= right) {
					mid = (left + right) / 2;
					if (a[mid] == value) {
						isFound = true;
						result[i] = mid + 1;
					} else if (a[mid] < value) {
						left = mid + 1;
					} else {
						right = mid - 1;
					}
				}

				if (!isFound) {
					result[i] = -1;
				}

			}
			return result;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		String root = System.getProperty("user.dir") + "/src/";
		InputStream stream = new FileInputStream(root + "by/it/group151004/afanasenko/lesson04/dataA.txt");
		A_BinaryFind instance = new A_BinaryFind();
		int[] result = instance.findIndex(stream);
		for (int index : result) {
			System.out.print(index + " ");
		}
	}
}