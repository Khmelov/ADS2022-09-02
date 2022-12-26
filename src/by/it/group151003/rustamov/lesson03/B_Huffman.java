package by.it.group151003.rustamov.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class B_Huffman {

    Character getIndex(String val) {
        for (Map.Entry<Character, Integer> pair : codes.entrySet()) {
            if (val.equals(Integer.toString(pair.getValue()))) {
                return pair.getKey();
            }
        }
        return 'f';
    }


    static private Map<Character, Integer> codes = new TreeMap<>();

    String decode(File file) throws FileNotFoundException {

        StringBuilder res = new StringBuilder();
        Scanner scanner = new Scanner(file);
        Integer count = scanner.nextInt();
        Integer length = scanner.nextInt();

        for (int i = 0; i < count; i++) {
            codes.put(scanner.next().charAt(0), scanner.nextInt());
        }

        String str = scanner.next();
        String tmp = "";
        int i = 0, amount = 0;

        while (i < str.length()) {

            while ((str.charAt(i) != '0') && (amount < count - 2)) {
                amount++;
                i++;
            }

            res.append(getIndex(str.substring(0, i + 1)));
            str = str.substring(i + 1);
            amount = 0;
            i = 0;
        }

        return res.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/group151003/rustamov/lesson03/encodeHuffman-2.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }


}
