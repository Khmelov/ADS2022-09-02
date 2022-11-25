package by.it.group151004.burbouski.lesson08;

import java.io.*;
import java.util.Scanner;

public class C_Stairs {
	public int getMaxSum(InputStream stream) {
		try (Scanner scanner = new Scanner(stream)) {
			int n = scanner.nextInt();
			int stairs[] = new int[n];
			for (int i = 0; i < n; i++) {
				stairs[i] = scanner.nextInt();
			}
			int[] result = new int[stairs.length + 1];
			result[0] = 0;
			result[1] = stairs[0];
			for (int i = 1; i < stairs.length; i++) {
				int first = result[i - 1] + stairs[i];
				int second = result[i] + stairs[i];
				result[i + 1] = Math.max(first, second);
			}
			return result[stairs.length];
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		String root = System.getProperty("user.dir") + "/src/";
		InputStream stream = new FileInputStream(root + "by/it/group151004/burbouski/lesson08/dataC.txt");
		C_Stairs instance = new C_Stairs();
		int res = instance.getMaxSum(stream);
		System.out.println(res);
	}
}