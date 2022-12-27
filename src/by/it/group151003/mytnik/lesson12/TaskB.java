package by.it.group151003.mytnik.lesson12;

public class TaskB {
    public static final int AMOUNT_OF_VERTICES = 10;
    private static final char START_VERTEX = 'A';
    private final int MAX = 100;
    public int[] dist = new int[AMOUNT_OF_VERTICES];

    private final int[][] graph = {
            {MAX, 4, -2, MAX, MAX, MAX, MAX, MAX, MAX, MAX},
            {MAX, MAX, MAX, MAX, MAX, MAX, -2, -4, MAX, MAX},
            {MAX, MAX, MAX, 2, MAX, 1, MAX, MAX, MAX, MAX},
            {MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX},
            {MAX, MAX, MAX, MAX, MAX, -2, MAX, 3, MAX, MAX},
            {MAX, MAX, MAX, 3, MAX, MAX, MAX, MAX, MAX, MAX},
            {MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, -1, MAX},
            {MAX, MAX, MAX, MAX, MAX, MAX, 1, MAX, MAX, MAX},
            {MAX, MAX, MAX, MAX, MAX, MAX, MAX, 1, MAX, MAX},
            {7, MAX, 6, MAX, 6, 5, MAX, MAX, MAX, MAX}
    };

    public void shortestPaths(int start) {
        for (int i = 0; i < AMOUNT_OF_VERTICES; i++) {
            dist[i] = MAX;
        }

        dist[start] = 0;

        for (int i = 0; i < AMOUNT_OF_VERTICES - 1; i++) {
            for (int j = 0; j < AMOUNT_OF_VERTICES; j++) {
                for (int k = 0; k < AMOUNT_OF_VERTICES; k++) {
                    if (graph[j][k] != MAX && dist[k] > dist[j] + graph[j][k]) {
                        dist[k] = dist[j] + graph[j][k];
                    }
                }
            }
        }
    }
}
