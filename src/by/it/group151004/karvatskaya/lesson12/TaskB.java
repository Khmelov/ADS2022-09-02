package by.it.group151004.karvatskaya.lesson12;

public class TaskB {
    public static final int number = 10;
    private static final char START_VERT = 'A';
    public int MAX = 100;
    public int[] distance = new int[number];
    public int[] path = new int[number];

    public int[][] graph = {
            {MAX, 4, -2, MAX, MAX, MAX, MAX, MAX, MAX, MAX}, //FOR A
            {MAX, MAX, MAX, MAX, MAX, MAX, -2, -4, MAX, MAX}, //FOR B
            {MAX, MAX, MAX, 2, MAX, 1, MAX, MAX, MAX, MAX}, //FOR C
            {MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX}, //FOR D
            {MAX, MAX, MAX, MAX, MAX, -2, MAX, 3, MAX, MAX}, //FOR E
            {MAX, MAX, MAX, 3, MAX, MAX, MAX, MAX, MAX, MAX}, //FOR F
            {MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, -1, MAX}, //FOR G
            {MAX, MAX, MAX, MAX, MAX, MAX, 1, MAX, MAX, MAX}, //FOR H
            {MAX, MAX, MAX, MAX, MAX, MAX, MAX, 1, MAX, MAX}, //FOR I
            {7, MAX, 6, MAX, 6, 5, MAX, MAX, MAX, MAX} //FOR S
    };

    public void shortestPaths(int first) {
        for (int i = 0; i < number; i++) {
            distance[i] = MAX;
            path[i] = -1;
        }
        distance[first] = 0;

        for (int i = 0; i < number - 1; i++) {
            for (int j = 0; j < number; j++) {
                for (int k = 0; k < number; k++) {
                    if (graph[j][k] != MAX && distance[k] > distance[j] + graph[j][k]) {
                        distance[k] = distance[j] + graph[j][k];
                        path[k] = j;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TaskB taskB = new TaskB();
        taskB.shortestPaths(0);

        for (int i = 0; i < number; i++) {
            char nextVertex = (char)(START_VERT + i);
            if (nextVertex == 'J')
                nextVertex = 'S';
            System.out.print("The shortest path from " + START_VERT + " to " + nextVertex + " = " +  taskB.distance[i]);
            if (taskB.distance[i] == taskB.MAX)
                System.out.print(" (no path)");
            System.out.print("\n");
        }
    }
}
