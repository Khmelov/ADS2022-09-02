package by.it.group151004.eremeichik.lesson12;

import java.util.Arrays;

public class TaskB {
    public static final int INF = 10000000;
    private final static int[][] graph = new int[][]{
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
    private final static int size = 10;
    private final static int[] distArr = new int[size];
    private final static int[] routeArr = new int[size];

    public static int[] getDistArr(){
        return distArr;
    }

    static void fordBellman(int vertex) {
        Arrays.fill(distArr, INF);
        Arrays.fill(routeArr, -1);
        distArr[vertex] = 0;
        for (int i = 0; i < size-1; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    if (distArr[k] > distArr[j] + graph[j][k] && graph[j][k] != INF) {
                        distArr[k] = distArr[j] + graph[j][k];
                        routeArr[k] = j;
                    }
                }
            }
        }
    }

    static void printRouteArr(int start, int current) {
        if (start == current) {
            System.out.println((char) (start + 'A') + " ");
        } else if (routeArr[current] == -1) {
            System.out.println("No route " + (char) (start + 'A') + " to " + (char) (current + 'A'));
        } else {
            printRouteArr(start, routeArr[current]);
            System.out.println((char) (current + 'A') + " ");
        }
    }
}
