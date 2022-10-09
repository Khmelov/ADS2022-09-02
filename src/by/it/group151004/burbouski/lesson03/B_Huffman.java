package by.it.group151004.burbouski.lesson03;

import java.io.*;
import java.util.*;

public class B_Huffman {
	public String decode(File file) throws FileNotFoundException {
		StringBuilder result = new StringBuilder();
		try (Scanner scanner = new Scanner(file)) {
			Integer count = scanner.nextInt();
			Integer length = scanner.nextInt();
			Map<Character, String> code = new HashMap<>();
			String substr;
			scanner.nextLine();
			for (int i = 0; i < count; i++) {
				substr = scanner.nextLine();
				code.put(substr.charAt(0), substr.substring(3));
			}
			substr = scanner.nextLine();
			int i = 0, j = 1;
			while (i < length) {
				for (Map.Entry<Character, String> item : code.entrySet()) {
					if (substr.substring(i, j).equals(item.getValue())) {
						result.append(item.getKey());
						i = j;
						break;
					}
				}
				j++;
			}
		}
		return result.toString();
	}

	public static void main(String[] args) throws FileNotFoundException {
		String root = System.getProperty("user.dir") + "/src/";
		File f = new File(root + "by/it/a_khmelev/lesson03/encodeHuffman.txt");
		B_Huffman instance = new B_Huffman();
		String result = instance.decode(f);
		System.out.println(result);
	}
}