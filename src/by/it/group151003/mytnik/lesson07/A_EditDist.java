package by.it.group151003.mytnik.lesson07;

import static by.it.group151003.mytnik.lesson07.EditDistUtils.min;
import static by.it.group151003.mytnik.lesson07.EditDistUtils.substitutionCost;

public class A_EditDist {
    public int getDistanceEditing(String one, String two) {
        if (one.isEmpty()) {
            return two.length();
        }

        if (two.isEmpty()) {
            return one.length();
        }

        return min(
                getDistanceEditing(one.substring(1), two) + 1,
                getDistanceEditing(one, two.substring(1)) + 1,
                getDistanceEditing(one.substring(1), two.substring(1)) + substitutionCost(one.charAt(0), two.charAt(0))
        );
    }
}