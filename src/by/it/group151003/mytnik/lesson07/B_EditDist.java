package by.it.group151003.mytnik.lesson07;

import static by.it.group151003.mytnik.lesson07.EditDistUtils.min;
import static by.it.group151003.mytnik.lesson07.EditDistUtils.substitutionCost;

public class B_EditDist {
    public int getDistanceEditing(String one, String two) {
        int[][] distances = new int[one.length() + 1][two.length() + 1];

        for (int i = 0; i <= one.length(); i++) {
            distances[i][0] = i;
        }

        for (int j = 1; j <= two.length(); j++) {
            distances[0][j] = j;
        }

        for (int i = 1; i <= one.length(); i++) {
            for (int j = 1; j <= two.length(); j++) {
                distances[i][j] = min(
                        distances[i - 1][j] + 1,
                        distances[i][j - 1] + 1,
                        distances[i - 1][j - 1] + substitutionCost(one.charAt(i - 1), two.charAt(j - 1))
                );
            }
        }

        return distances[one.length()][two.length()];
    }
}