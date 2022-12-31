package by.it.group151004.akimenko.lesson12;

import java.util.*;

public class TaskC {
    public int INF = 1000000000;
    public int u;
    public int v;

    public int[][] matrix = {
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

    public TaskC(int u, int v) {
        this.u = u;
        this.v = v;
    }

    public int floydWarshell() {
        for (int k = 0; k < 10; k++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
        return Math.min(matrix[u][v], Math.min(matrix[v][u], matrix[u][u] + matrix[v][v]));
    }

    public static void main(String[] args) {
        TaskC cVar = new TaskC(2, 3);
        int minLength = cVar.floydWarshell();
        System.out.println("Minimum length (" + (char) ('A' + cVar.u) + ", " + (char) ('A' + cVar.v) + "): " + minLength);
    }
}
