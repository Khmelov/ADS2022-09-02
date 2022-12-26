package by.it.group151002.talalaev.lesson12;

import java.util.Arrays;

public class TaskB {
    static final int INF = 100000;
    public int[][] a = {
            { INF, 4,   -2,  INF, INF, INF, INF, INF, INF, INF },
            { INF, INF, INF, INF, INF, INF, -2 , -4 , INF, INF },
            { INF, INF, INF, 2  , INF, 1  , INF, INF, INF, INF },
            { INF, INF, INF, INF, INF, INF, INF, INF, INF, INF },
            { INF, INF, INF, INF, INF, -2 , INF, 3  , INF, INF },
            { INF, INF, INF, 3  , INF, INF, INF, INF, INF, INF },
            { INF, INF, INF, INF, INF, INF, INF, INF, -1 , INF },
            { INF, INF, INF, INF, INF, INF, 1  , INF, INF, INF },
            { INF, INF, INF, INF, INF, INF, INF, 1  , INF, INF },
            { 7  , INF, 6  , INF, 6  , 5  , INF, INF, INF, INF }
    };
    public int[] distance = new int[10];
    public int[] path = new int[10];

    public void ford(int s) {
        Arrays.fill(distance, INF);
        Arrays.fill(path, -1);
        distance[s] = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if ((distance[k] > distance[j] + a[j][k]) && a[j][k] != INF) {
                        distance[k] = distance[j] + a[j][k];
                        path[k] = j;
                    }
                }
            }
        }
    }

    public void printPath(int s, int e) {
        if (s == e) {
            System.out.print( (s) + " ");
        } else if (path[e] == -1) {
            System.out.println("No path from " + (s) + " to " + (e));
        } else {
            printPath(s, path[e]);
            System.out.print(e + " ");
        }
    }

    public static void main(String[] args)
    {
        TaskB graph = new TaskB();
        graph.ford(0);
        for (int i = 0; i < 10; i++)
        {
            System.out.println("Shortest path from 0 to " + i + ": " );
            graph.printPath(0, i);
            System.out.println("\nDistance: " + graph.distance[i]);
        }
    }
}
