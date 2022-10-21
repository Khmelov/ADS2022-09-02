package by.it.group151004.burbouski.lesson07;

import java.io.*;
import java.util.*;

public class A_EditDist {
	public int getDistanceEdinting(String one, String two) {
		int[][] words = new int[one.length()][two.length()];
		for (int i = 0; i < one.length(); i++) {
			Arrays.fill(words[i], Integer.MAX_VALUE);
		}
		return new EditDist().dist(one.length(), two.length(), one, two);
	}

	public int min(int a, int b, int c) {
		return Integer.min(a, Integer.min(b, c));
	}

	public static void main(String[] args) throws FileNotFoundException {
		String root = System.getProperty("user.dir") + "/src/";
		InputStream stream = new FileInputStream(root + "by/it/group151004/burbouski/lesson07/dataABC.txt");
		A_EditDist instance = new A_EditDist();
		try (Scanner scanner = new Scanner(stream)) {
			System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
			System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
			System.out.println(instance.getDistanceEdinting(scanner.nextLine(), scanner.nextLine()));
		}
	}

	public class EditDist {
		int dist(int i, int j, String one, String two) {
			if (i == 0) {
				return j;
			}
			if (j == 0) {
				return i;
			}
			int cost = one.charAt(i - 1) == two.charAt(j - 1) ? 0 : 1;
			return min(dist(i - 1, j, one, two) + 1, dist(i, j - 1, one, two) + 1, dist(i - 1, j - 1, one, two) + cost);
		}
	}
}