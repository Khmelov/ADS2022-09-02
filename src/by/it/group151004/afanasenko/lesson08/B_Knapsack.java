package by.it.group151004.afanasenko.lesson08;

import java.io.*;
import java.util.Scanner;

public class B_Knapsack {
	public int getMaxWeight(InputStream stream) {
		try (Scanner scanner = new Scanner(stream)) {
			int we = scanner.nextInt();
			int n = scanner.nextInt();
			int gold[] = new int[n];
			for (int i = 0; i < n; i++) {
				gold[i] = scanner.nextInt();
			}
			int[][] results = new int[we + 1][gold.length + 1];
			for (int i = 1; i <= gold.length; i++) {
				for (int w = 1; w <= we; w++) {
					results[w][i] = results[w][i - 1];
					if (gold[i - 1] <= w) {
						results[w][i] = max(results[w][i], results[w - gold[i - 1]][i - 1] + gold[i - 1]);
					}
				}
			}
			return results[we][gold.length];
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		String root = System.getProperty("user.dir") + "/src/";
		InputStream stream = new FileInputStream(root + "by/it/group151004/afanasenko/lesson08/dataB.txt");
		B_Knapsack instance = new B_Knapsack();
		int res = instance.getMaxWeight(stream);
		System.out.println(res);
	}

	public static int max(int first, int second) {
		return Math.max(first, second);
	}
}