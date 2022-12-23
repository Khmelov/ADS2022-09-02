package by.it.group151004.pyshny.lesson12;

import java.util.Arrays;

public class TaskB {
    public int INF = 1000000000;
    public int[] len = new int[10];
    public int[] path = new int[10];

    public int[][] map = {
            { INF, 4, -2, INF, INF, INF, INF, INF, INF, INF },
            { INF, INF, INF, INF, INF, INF, -2, -4, INF, INF },
            { INF, INF, INF, 2, INF, 1, INF, INF, INF, INF },
            { INF, INF, INF, INF, INF, INF, INF, INF, INF, INF },
            { INF, INF, INF, INF, INF, -2, INF, 3, INF, INF },
            { INF, INF, INF, 3, INF, INF, INF, INF, INF, INF },
            { INF, INF, INF, INF, INF, INF, INF, INF, -1, INF },
            { INF, INF, INF, INF, INF, INF, 1, INF, INF, INF },
            { INF, INF, INF, INF, INF, INF, INF, 1, INF, INF },
            { 7, INF, 6, INF, 6, 5, INF, INF, INF, INF } };

    public void GetWay(int s) {
        Arrays.fill(len, INF);
        Arrays.fill(path, -1);
        len[s] = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if (len[k] > len[j] + map[j][k]) {
                        len[k] = len[j] + map[j][k];
                        path[k] = j;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TaskB sus = new TaskB();
        sus.GetWay(0);
    }
}