package by.it.group151002.haiduk.lesson12;

public class TaskB {
    public static final int NUMBER_OF_VERTICES = 10;
    private static final char START_VERTEX = 'A';
    public int MAX = 100;
    public int[] dist = new int[NUMBER_OF_VERTICES];
    public int[] path = new int[NUMBER_OF_VERTICES];

    public int[][] graph = {
            {MAX, 4, -2, MAX, MAX, MAX, MAX, MAX, MAX, MAX}, //A
            {MAX, MAX, MAX, MAX, MAX, MAX, -2, -4, MAX, MAX}, //B
            {MAX, MAX, MAX, 2, MAX, 1, MAX, MAX, MAX, MAX}, //C
            {MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX}, //D
            {MAX, MAX, MAX, MAX, MAX, -2, MAX, 3, MAX, MAX}, //E
            {MAX, MAX, MAX, 3, MAX, MAX, MAX, MAX, MAX, MAX}, //F
            {MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, -1, MAX}, //G
            {MAX, MAX, MAX, MAX, MAX, MAX, 1, MAX, MAX, MAX}, //H
            {MAX, MAX, MAX, MAX, MAX, MAX, MAX, 1, MAX, MAX}, //I
            {7, MAX, 6, MAX, 6, 5, MAX, MAX, MAX, MAX} //S
    };

    public void shortestPaths(int start) {
        for (int i = 0; i < NUMBER_OF_VERTICES; i++) {
            dist[i] = MAX;
            path[i] = -1;
        }
        dist[start] = 0;
        for (int i = 0; i < NUMBER_OF_VERTICES - 1; i++)
            for (int j = 0; j < NUMBER_OF_VERTICES; j++)
                for (int k = 0; k < NUMBER_OF_VERTICES; k++)
                    if (graph[j][k] != MAX && dist[k] > dist[j] + graph[j][k]) {
                        dist[k] = dist[j] + graph[j][k];
                        path[k] = j;
                    }
    }

    public static void main(String[] args) {
        TaskB taskB = new TaskB();
        taskB.shortestPaths(0);
        for (int i = 0; i < NUMBER_OF_VERTICES; i++) {
            char nextVertex = (char)(START_VERTEX + i);
            if (nextVertex == 'J')
                nextVertex = 'S';
            if (taskB.dist[i] != taskB.MAX)
                System.out.print("The shortest path from " + START_VERTEX + " to " + nextVertex + " = " +  taskB.dist[i]);
            else
                System.out.print("There's no path from " + START_VERTEX + " to " + nextVertex);
            System.out.print("\n");
        }
    }
}
