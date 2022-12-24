package by.it.group151003.matoshko.lesson12;

import java.util.Arrays;

public class TaskB {
    static final int INFINITY = 10000;
    public int[][] matrix = {
            { INFINITY, 4,   -2,  INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, INFINITY },
            { INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, -2 , -4 , INFINITY, INFINITY },
            { INFINITY, INFINITY, INFINITY, 2  , INFINITY, 1  , INFINITY, INFINITY, INFINITY, INFINITY },
            { INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, INFINITY },
            { INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, -2 , INFINITY, 3  , INFINITY, INFINITY },
            { INFINITY, INFINITY, INFINITY, 3  , INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, INFINITY },
            { INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, -1 , INFINITY },
            { INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, 1  , INFINITY, INFINITY, INFINITY },
            { INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, INFINITY, 1  , INFINITY, INFINITY },
            { 7  , INFINITY, 6  , INFINITY, 6  , 5  , INFINITY, INFINITY, INFINITY, INFINITY }
    };
    public int[] distance = new int[10];
    public int[] path = new int[10];

    public void ford(int s) {
        Arrays.fill(distance, INFINITY);
        Arrays.fill(path, -1);
        distance[s] = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if ((distance[k] > distance[j] + matrix[j][k]) && matrix[j][k] != INFINITY) {
                        distance[k] = distance[j] + matrix[j][k];
                        path[k] = j;
                    }
                }
            }
        }
    }

    public void printPath(int s, int e) {
        if (s == e)
            System.out.print( (s) + " ");
        else if (path[e] == -1)
            System.out.println("No path from " + (s) + " to " + (e));
        else {
            printPath(s, path[e]);
            System.out.print(e + " ");
        }
    }

    public static void main(String[] args) {
        TaskB graph = new TaskB();
        graph.ford(0);
        for (int i = 0; i < 10; i++) {
            System.out.println("Best road from 0 to " + i + ": " );
            graph.printPath(0, i);
            System.out.println("\nDistance: " + graph.distance[i]);
        }
    }
}
