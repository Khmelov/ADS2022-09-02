package by.it.group151002.shatko.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class B_Huffman {

    String decode(File file) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        Scanner scan = new Scanner(file);
        int count = scan.nextInt();
        int length = scan.nextInt();

        Map<String, String> values = new HashMap<>();
        String letter = "";
        String code = "";
        scan.nextLine();

        for (int i = 0; i < count; i++) {
            String[] letterInfo = scan.nextLine().split(": ");
            values.put(letterInfo[1], letterInfo[0]);
        }

        String encryptedString = scan.next(); //01001100100111
        String currCode = "";
        for (int i = 0; i < length; i++) {
            currCode += encryptedString.charAt(i);
            if (values.containsKey(currCode)) {
                letter = values.get(currCode);
                result.append(letter);
                currCode = "";
            }
        }

        return result.toString();
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/group151002/shatko/lesson03/encodeHuffman.txt");
        B_Huffman instance = new B_Huffman();
        String result = instance.decode(f);
        System.out.println(result);
    }


}
