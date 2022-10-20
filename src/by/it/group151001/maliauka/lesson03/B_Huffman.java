package by.it.group151001.maliauka.lesson03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class B_Huffman {

    public String decode(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        int count = scanner.nextInt();
        int length = scanner.nextInt();
        scanner.nextLine();

        Map<String, Character> map = new HashMap<>();

        for (int i = 0; i < count; i++) {
            String[] line = scanner.nextLine().split(": ");
            map.put(line[1], line[0].charAt(0));
        }
        String code = scanner.nextLine();

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            String key = "";
            for (int j = i; j < code.length(); j++) {
                key += code.charAt(j);
                if (map.containsKey(key)) {
                    result.append(map.get(key));
                    i = j;
                    break;
                }
            }
        }

        return result.toString();
    }
}
