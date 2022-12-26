package by.it.group151002.kuvshinov.lesson12;

import java.util.Arrays;

public class GraphB {
    static final int INF = 1000000;
    static int[] dist = new int[10];
    static int[] route = new int[10];
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

    static boolean printRoute(int st, int curr) {
        if (st == curr) {
            System.out.print((char) (st + 'A') + " ");
        } else if (route[curr] == -1) {
            System.out.println("No route from " + (char) (st + 'A') + " to " + (char) (curr + 'A'));
            return false;
        } else {
            printRoute(st, route[curr]);
            System.out.print((char) (curr + 'A') + " ");
        }
        return true;
    }

    static void bellman(int vert) {
        Arrays.fill(dist, INF);
        Arrays.fill(route, -1);
        dist[vert] = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if (graph[j][k] != INF && dist[k] > dist[j] + graph[j][k]) {
                        route[k] = j;
                        dist[k] = dist[j] + graph[j][k];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        bellman(0);
        for (int i = 0; i < 10; i++) {
            System.out.print("Shortest route from A to " + (char) (i + 'A') + ": ");
            if (printRoute(0, i))
                System.out.println("\nDistance: " + dist[i]);
        }
    }
}
