package by.it.group151003.romanko.lesson12;

public class TaskB {

    private static final char START_VERTEX = 'A';
    public int MAX = 100;
    public int[] dist = new int[10];
    public int[] path = new int[10];

    public int[][] matrix = {
            {MAX, 4, -2, MAX, MAX, MAX, MAX, MAX, MAX, MAX},
            {MAX, MAX, MAX, MAX, MAX, MAX, -2, -4, MAX, MAX},
            {MAX, MAX, MAX, 2, MAX, 1, MAX, MAX, MAX, MAX},
            {MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX},
            {MAX, MAX, MAX, MAX, MAX, -2, MAX, 3, MAX, MAX},
            {MAX, MAX, MAX, 3, MAX, MAX, MAX, MAX, MAX, MAX,},
            {MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, -1, MAX},
            {MAX, MAX, MAX, MAX, MAX, MAX, 1, MAX, MAX, MAX},
            {MAX, MAX, MAX, MAX, MAX, MAX, MAX, 1, MAX, MAX},
            {7, MAX, 6, MAX, 6, 5, MAX, MAX, MAX, MAX}
    };

    public void shortestPaths(int start) {
        for (int i = 0; i < 10; i++) {
            dist[i] = MAX;
            path[i] = -1;
        }
        dist[start] = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if (matrix[j][k] != MAX && dist[k] > dist[j] + matrix[j][k]) {
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
            if (B.dist[i] == B.MAX)
                System.out.print(" (no path)");
            System.out.print("\n");
        }
    }
}
