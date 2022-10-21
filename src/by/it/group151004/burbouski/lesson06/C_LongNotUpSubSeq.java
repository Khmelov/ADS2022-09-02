package by.it.group151004.burbouski.lesson06;

import java.io.*;
import java.util.Scanner;

public class C_LongNotUpSubSeq {
	public int ceilIndex(int[] subsequence, int startRight, int key) {
		int mid, left = 0, right = startRight, index = 0;
		boolean isIndex = false;
		for (mid = (left + right) / 2; left <= right && !isIndex; mid = (left + right) / 2) {
			if (subsequence[mid] < key) {
				right = mid - 1;
			} else if (mid + 1 <= right && subsequence[mid + 1] < key) {
				subsequence[mid + 1] = key;
				index = mid + 1;
				isIndex = true;
			} else {
				left = mid + 1;
			}
		}
		if (!isIndex) {
			if (mid == left) {
				subsequence[mid] = key;
				index = mid;
			} else {
				subsequence[mid + 1] = key;
				index = mid + 1;
			}
		}
		return index;
	}

	public int find(int[] m, int[] num) {
		if (m.length <= 1) {
			return 1;
		}
		int length = -1;
		int[] size = new int[m.length];
		for (int i = 0; i < m.length; ++i) {
			num[i] = -1;
			size[i] = -1;
		}
		num[0] = m[0];
		size[0] = 0;
		for (int i = 1; i < m.length; i++) {
			size[i] = ceilIndex(num, i, m[i]);
			if (length < size[i]) {
				length = size[i];
			}
		}
		return length + 1;
	}

	public int getNotUpSeqSize(InputStream stream) throws FileNotFoundException {
		try (Scanner scanner = new Scanner(stream)) {
			int n = scanner.nextInt();
			int[] m = new int[n];
			for (int i = 0; i < n; i++) {
				m[i] = scanner.nextInt();
			}
			int[] num = new int[m.length];
			int result;
			result = find(m, num);
			cycle: for (int i = 0; i < num.length; i++) {
				boolean cond = true;
				for (int j = i; j < m.length && cond; j++) {
					if (num[i] == m[j]) {
						System.out.print(j + 1 + " ");
						m[j] = 0;
						cond = false;
					}
					m[j] = 0;
				}
				if (i + 1 >= num.length || num[i + 1] == 0) {
					System.out.println();
					break cycle;
				}
			}
			return result;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		String root = System.getProperty("user.dir") + "/src/";
		InputStream stream = new FileInputStream(root + "by/it/group151004/burbouski/lesson06/dataC.txt");
		C_LongNotUpSubSeq instance = new C_LongNotUpSubSeq();
		int result = instance.getNotUpSeqSize(stream);
		System.out.print(result);
	}
}