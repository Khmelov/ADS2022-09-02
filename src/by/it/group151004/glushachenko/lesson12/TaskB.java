package by.it.group151004.glushachenko.lesson12;

import java.util.Arrays;

public class TaskB {
    static final int INF = 1000000;
    static int[][] graph = new int[][]{
            {INF, 4,   -2,  INF, INF, INF, INF, INF, INF, INF},
            {INF, INF, INF, INF, INF, INF,  -2, -4,  INF, INF},
            {INF, INF, INF,  2, INF,   1,  INF, INF, INF, INF},
            {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF},
            {INF, INF, INF, INF, INF,  -2, INF,  3,  INF, INF},
            {INF, INF, INF,  3,  INF, INF, INF, INF, INF, INF},
            {INF, INF, INF, INF, INF, INF, INF, INF,  -1, INF},
            {INF, INF, INF, INF, INF, INF,  1,  INF, INF, INF},
            {INF, INF, INF, INF, INF, INF, INF,  1,  INF, INF},
            { 7,  INF,  6,  INF,  6,   5,  INF, INF, INF, INF}};
    static int[] dist = new int[10];
    static int[] route = new int[10];

    static void fordBellman(int vertex) {
        Arrays.fill(dist, INF);
        Arrays.fill(route, -1);
        dist[vertex] = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if (dist[k] > dist[j] + graph[j][k] && graph[j][k] != INF) {
                        dist[k] = dist[j] + graph[j][k];
                        route[k] = j;
                    }
                }
            }
        }
    }

    static boolean printRoute(int start, int current) {
        if (start == current) {
            System.out.print((char) (start + 'A') + " ");
        } else if (route[current] == -1) {
            System.out.println("No route from " + (char) (start + 'A') + " to " + (char) (current + 'A'));
            return false;
        } else {
            printRoute(start, route[current]);
            System.out.print((char) (current + 'A') + " ");
        }
        return true;
    }

    public static void main(String[] args) {
        fordBellman(0);
        for (int i = 0; i < 10; i++) {
            System.out.print("Shortest route from A to " + (char) (i + 'A') + ": ");
            if (printRoute(0, i))
                System.out.println("\nDistance: " + dist[i]);

        }
    }
}