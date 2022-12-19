package by.it.group151004.afanasenko.lesson04;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class C_GetInversions {
	public int invertions = 0;

	public void merge(int[] a, int p, int q, int r) {
		int n1 = q - p + 1;
		int n2 = r - q;
		int[] L = new int[n1];
		int[] R = new int[n2];
		int i, j, k;
		for (i = 0; i < n1; i++) {
			L[i] = a[p + i];
		}

		for (j = 0; j < n2; j++) {
			R[j] = a[q + 1 + j];
		}

		i = 0;
		j = 0;
		k = p;
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				a[k] = L[i++];
				k++;
			} else {
				a[k] = R[j++];
				k++;
				invertions = invertions + q + 1 - p - i;
			}
		}
		while (i < n1) {
			a[k] = L[i++];
			k++;
		}
		while (j < n2) {
			a[k] = R[j++];
			k++;
		}
	}
	public void mergeSort(int[] a, int p, int r) {
		if (p < r) {
			int q = (p + r) / 2;
			mergeSort(a, p, q);
			mergeSort(a, q + 1, r);
			merge(a, p, q, r);
		}
	}
	public int calc(InputStream stream) throws FileNotFoundException {
		try (Scanner scanner = new Scanner(stream)) {
			int n = scanner.nextInt();
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = scanner.nextInt();
			}
			mergeSort(a, 0, n - 1);
			return invertions;
		}
	}
	public static void main(String[] args) throws FileNotFoundException {
		String root = System.getProperty("user.dir") + "/src/";
		InputStream stream = new FileInputStream(root + "by/it/group151004/afanasenko/lesson04/dataC.txt");
		C_GetInversions instance = new C_GetInversions();
		int result = instance.calc(stream);
		System.out.print(result);
	}
}