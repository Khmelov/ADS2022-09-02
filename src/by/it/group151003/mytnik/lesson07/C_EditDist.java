package by.it.group151003.mytnik.lesson07;

import static by.it.group151003.mytnik.lesson07.EditDistUtils.min;
import static by.it.group151003.mytnik.lesson07.EditDistUtils.substitutionCost;

public class C_EditDist {

    public String getDistanceEditing(String one, String two) {
        return buildPath(getDistances(one, two), one, two);
    }

    private String buildPath(int[][] distances, String one, String two) {
        StringBuilder path = new StringBuilder();

        int i = one.length();
        int j = two.length();

        while (i > 0 && j > 0) {
            path.append(',');

            int substitutionCost = substitutionCost(one.charAt(i - 1), two.charAt(j - 1));
            int deletion = distances[i - 1][j] + 1;
            int insertion = distances[i][j - 1] + 1;
            int substitution = distances[i - 1][j - 1] + substitutionCost;

            int min = min(
                    deletion,
                    insertion,
                    substitution
            );

            if (min == deletion) {
                path.append('-');
                i--;
            } else if (min == insertion) {
                path.append('+');
                j--;
            } else {
                path.append(substitutionCost == 0 ? '#' : '~');
                i--;
                j--;
            }
        }

        while (i > 0) {
            path.append(",-");
            i--;
        }

        while (j > 0) {
            path.append(",+");
            j--;
        }

        return path.reverse().toString();
    }

    public int[][] getDistances(String one, String two) {
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

        return distances;
    }
}