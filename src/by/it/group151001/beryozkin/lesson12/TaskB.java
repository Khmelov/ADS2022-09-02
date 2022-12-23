package by.it.group151001.beryozkin.lesson12;
import java.util.Arrays;

public class TaskB {
    public int[] dest = new int[10];
    public int[] path = new int[10];
    static final int INF = 10000;
    public int[][] matrix = {
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

    public static void main(String[] args)
    {
        TaskB graph = new TaskB();
        graph.ford(0);
        for (int i = 0; i < 10; i++)
        {
            System.out.println("The most short path from 0 to " + i + ": " );
            graph.outputPath(0, i);
            System.out.println("\nDistance: " + graph.dest[i]);
        }
    }

    public void ford(int s) {
        Arrays.fill(dest, INF);
        Arrays.fill(path, -1);
        dest[s] = 0;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    if ((dest[k] > dest[j] + matrix[j][k]) && matrix[j][k] != INF) {
                        dest[k] = dest[j] + matrix[j][k];
                        path[k] = j;
                    }
                }
            }
        }
    }

    public void outputPath(int s, int e) {
        if (s == e) {
            System.out.print( (s) + " ");
        } else if (path[e] == -1) {
            System.out.println("No path from " + (s) + " to " + (e));
        } else {
            outputPath(s, path[e]);
            System.out.print(e + " ");
        }
    }
}
