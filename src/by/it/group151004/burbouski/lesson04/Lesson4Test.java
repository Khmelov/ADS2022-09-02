package by.it.group151004.burbouski.lesson04;

import static org.junit.Assert.assertTrue;

import java.io.*;
import java.util.Arrays;

import org.junit.Test;

public class Lesson4Test {
	@Test(timeout = 1000)
	public void A1() throws Exception {
		String root = System.getProperty("user.dir") + "/src/";
		InputStream stream = new FileInputStream(root + "by/it/group151004/burbouski/lesson04/dataA1.txt");
		A_BinaryFind instance = new A_BinaryFind();
		int[] result = instance.findIndex(stream);
		StringBuilder sb = new StringBuilder();
		for (int index : result) {
			sb.append(index).append(" ");
		}
		boolean ok = "3 1 -1 1 -1".equals(sb.toString().trim());
		assertTrue("A1 failed", ok);
	}

	@Test(timeout = 1000)
	public void A2() throws Exception {
		String root = System.getProperty("user.dir") + "/src/";
		InputStream stream = new FileInputStream(root + "by/it/group151004/burbouski/lesson04/dataA2.txt");
		A_BinaryFind instance = new A_BinaryFind();
		int[] result = instance.findIndex(stream);
		StringBuilder sb = new StringBuilder();
		for (int index : result) {
			sb.append(index).append(" ");
		}
		boolean ok = "-1 -1 -1 -1 -1 -1 -1 -1 -1 -1".equals(sb.toString().trim());
		assertTrue("A2 failed", ok);
	}

	@Test(timeout = 1000)
	public void A3() throws Exception {
		String root = System.getProperty("user.dir") + "/src/";
		InputStream stream = new FileInputStream(root + "by/it/group151004/burbouski/lesson04/dataA3.txt");
		A_BinaryFind instance = new A_BinaryFind();
		int[] result = instance.findIndex(stream);
		StringBuilder sb = new StringBuilder();
		for (int index : result) {
			sb.append(index).append(" ");
		}
		boolean ok = "1 2 3 4 5 6 7 8 9 10".equals(sb.toString().trim());
		assertTrue("A3 failed", ok);
	}

	@Test
	public void B1() throws Exception {
		String root = System.getProperty("user.dir") + "/src/";
		InputStream stream = new FileInputStream(root + "by/it/group151004/burbouski/lesson04/dataB1.txt");
		B_MergeSort instance = new B_MergeSort();
		int[] result = instance.getMergeSort(stream);
		boolean ok = result.length > 3;
		int test[] = new int[result.length];
		System.arraycopy(result, 0, test, 0, result.length);
		Arrays.sort(test);
		for (int i = 0; i < result.length; i++) {
			ok = ok && result[i] == test[i];
		}
		assertTrue("B1 failed", ok);
	}

	@Test(timeout = 1000)
	public void B2() throws Exception {
		String root = System.getProperty("user.dir") + "/src/";
		InputStream stream = new FileInputStream(root + "by/it/group151004/burbouski/lesson04/dataB2.txt");
		B_MergeSort instance = new B_MergeSort();
		int[] result = instance.getMergeSort(stream);
		boolean ok = result.length > 3;
		int test[] = new int[result.length];
		System.arraycopy(result, 0, test, 0, result.length);
		Arrays.sort(test);
		for (int i = 0; i < result.length; i++) {
			ok = ok && result[i] == test[i];
		}
		assertTrue("B2 failed", ok);
	}

	@Test
	public void C2() throws Exception {
		String root = System.getProperty("user.dir") + "/src/";
		InputStream stream = new FileInputStream(root + "by/it/group151004/burbouski/lesson04/dataC2.txt");
		C_GetInversions instance = new C_GetInversions();
		int result = instance.calc(stream);
		boolean ok = 6 == result;
		assertTrue("C2 failed", ok);
	}

	@Test
	public void C4() throws Exception {
		String root = System.getProperty("user.dir") + "/src/";
		InputStream stream = new FileInputStream(root + "by/it/group151004/burbouski/lesson04/dataC4.txt");
		C_GetInversions instance = new C_GetInversions();
		int result = instance.calc(stream);
		boolean ok = 0 == result;
		assertTrue("C4 failed", ok);
	}
}