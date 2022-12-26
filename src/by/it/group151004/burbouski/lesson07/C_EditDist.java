package by.it.group151004.burbouski.lesson07;

import java.io.*;
import java.util.Scanner;

public class C_EditDist {
	public String getDistanceEdinting(String one, String two) {
		int m = one.length();
		int n = two.length();
		StringBuilder result = new StringBuilder();
		int[][] T = new int[m + 1][n + 1];
		for (int i = 1; i <= m; i++) {
			T[i][0] = i;
		}
		for (int j = 1; j <= n; j++) {
			T[0][j] = j;
		}
		int cost;
		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				cost = one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1;
				T[i][j] = Integer.min(Integer.min(T[i - 1][j] + 1, T[i][j - 1] + 1), T[i - 1][j - 1] + cost);
			}
		}
		char costc;
		char A[] = new char[one.length() + 1];
		char B[] = new char[two.length() + 1];
		for (int i = 1; i < A.length; i++) {
			A[i] = one.charAt(i - 1);
		}
		for (int i = 1; i < B.length; i++) {
			B[i] = two.charAt(i - 1);
		}
		int i = m;
		int j = n;
		do {
			cost = A[i] == B[j] ? 0 : 1;
			costc = A[i] == B[j] ? '#' : '~';
			if (T[i][j] == T[i - 1][j - 1] + cost) {
				result.insert(0, ',');
				result.insert(0, costc);
				if (costc == '~') {
					result.insert(1, B[j]);
				}
				i--;
				j--;
			} else if (T[i][j] == T[i][j - 1] + 1) {
				result.insert(0, ',');
				result.insert(0, '+');
				result.insert(1, B[i]);
				j--;
			} else if (T[i][j] == T[i - 1][j] + 1) {
				result.insert(0, ',');
				result.insert(0, '-');
				result.insert(1, A[i]);
				i--;
			}
		} while (i > 0 && j > 0);
		if (i > 0) {
			result.insert(0, ',');
			result.insert(0, '-');
			result.insert(1, A[i]);
		} else if (j > 0) {
			result.insert(0, ',');
			result.insert(0, '+');
			result.insert(1, B[j]);
		}
		return result.toString();
	}

	public static void main(String[] args) throws FileNotFoundException {
		String root = System.getProperty("user.dir") + "/src/";
		InputStream stream = new FileInputStream(root + "by/it/group151004/burbouski/lesson07/dataABC.txt");
		C_EditDist instance = new C_EditDist();
		try (Scanner scanner = new Scanner(stream)) {
			System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
			System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
			System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
		}
	}
}