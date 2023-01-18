package by.it.group151002.ravodin.lesson12;

import java.util.Arrays;

public class GraphB {
    static final int INF = 1000000;
    static int[][] graph = new int[][]{
            {INF, 4, -2, INF, INF, INF, INF, INF, INF, INF},
            {INF, INF, INF, INF, INF, INF, -2, -4, INF, INF},
            {INF, INF, INF, 2, INF, 1, INF, INF, INF, INF},
            {INF, INF, INF, INF, INF, INF, INF, INF, INF, INF},
            {INF, INF, INF, INF, INF, -2, INF, 3, INF, INF},
            {INF, INF, INF, 3, INF, INF, INF, INF, INF, INF},
            {INF, INF, INF, INF, INF, INF, INF, INF, -1, INF},
            {INF, INF, INF, INF, INF, INF, 1, INF, INF, INF},
            {INF, INF, INF, INF, INF, INF, INF, 1, INF, INF},
            {7, INF, 6, INF, 6, 5, INF, INF, INF, INF}};
    static int[] distances = new int[10];
    static int[] nextVert = new int[10];

    static void fordBellman(int vertex) {
        Arrays.fill(distances, INF);
        Arrays.fill(nextVert, -1);
        distances[vertex] = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if (distances[k] > distances[j] + graph[j][k] && graph[j][k] != INF) {
                        distances[k] = distances[j] + graph[j][k];
                        nextVert[k] = j;
                    }
                }
            }
        }
    }

    static boolean printRoute(int begin, int now) {
        if (begin == now) {
            System.out.print((char) (begin + 'A') + " ");
        } else if (nextVert[now] == -1) {
            System.out.println("No route from " + (char) (begin + 'A') + " to " + (char) (now + 'A'));
            return false;
        } else {
            printRoute(begin, nextVert[now]);
            System.out.print((char) (now + 'A') + " ");
        }
        return true;
    }

    public static void main(String[] args) {
        fordBellman(0);
        for (int i = 0; i < 10; i++) {
            System.out.print("Shortest route from A to " + (char) (i + 'A') + ": ");
            if (printRoute(0, i))
                System.out.println("\nDistance: " + distances[i]);

        }
    }
}