package by.it.group151004.afanasenko.lesson07;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class B_EditDist {
	public int getDistanceEdinting(String one, String two) {
		int m = one.length();
		int n = two.length();
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
		return T[m][n];
	}

	public static void main(String[] args) throws FileNotFoundException {
		String root = System.getProperty("user.dir") + "/src/";
		InputStream stream = new FileInputStream(root + "by/it/group151004/afanasenko/lesson07/dataABC.txt");
		B_EditDist instance = new B_EditDist();
		try (Scanner scanner = new Scanner(stream)) {
			System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
			System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
			System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
		}
	}
}