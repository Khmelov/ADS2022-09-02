package by.it.group151001.saprankov.lesson12;

public class TaskB {

    private static final char START_VERTEX = 'A';
    public int INF = 100;
    public int[] dist = new int[10];
    public int[] path = new int[10];

    public int[][] matrix = {
            {INF, 4, -2, INF,INF,INF,INF,INF,INF,INF},
            {INF, INF,INF,INF,INF,INF, -2, -4, INF,INF},
            {INF,INF,INF, 2, INF, 1, INF,INF,INF,INF},
            {INF,INF,INF,INF,INF,INF,INF,INF,INF,INF},
            {INF,INF,INF,INF,INF, -2, INF, 3, INF, INF},
            {INF,INF,INF, 3,INF,INF,INF,INF,INF,INF,},
            {INF,INF,INF,INF,INF,INF,INF,INF, -1,INF},
            {INF,INF,INF,INF,INF,INF, 1,INF,INF,INF},
            {INF,INF,INF,INF,INF,INF,INF, 1, INF,INF},
            {7, INF, 6, INF, 6, 5, INF,INF,INF,INF}
    };

    public void shortestPaths(int start) {
        for (int i = 0; i < 10; i++) {
            dist[i] = INF;
            path[i] = -1;
        }
        dist[start] = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if (matrix[j][k] != INF && dist[k] > dist[j] + matrix[j][k]) {
                        dist[k] = dist[j] + matrix[j][k];
                        path[k] = j;
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        TaskB B =new TaskB();
        B.shortestPaths(0);
        for (int i = 0; i < 10; i++) {
            char nextVertex = (char)(START_VERTEX + i);
            if (nextVertex == 'J')
                nextVertex = 'S';
            System.out.print("The shortest path from " + START_VERTEX + " to " + nextVertex + " = " +  B.dist[i]);
            if (B.dist[i] == B.INF)
                System.out.print(" (no path)");
            System.out.print("\n");
        }
    }
}
