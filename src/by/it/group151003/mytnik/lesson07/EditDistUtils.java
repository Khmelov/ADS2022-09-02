package by.it.group151003.mytnik.lesson07;

import java.util.Arrays;

public class EditDistUtils {
    public static int min(int... numbers) {
        return Arrays.stream(numbers).min().orElseThrow(() -> new IllegalArgumentException("No numbers"));
    }

    public static int substitutionCost(char a, char b) {
        return a == b ? 0 : 1;
    }
}