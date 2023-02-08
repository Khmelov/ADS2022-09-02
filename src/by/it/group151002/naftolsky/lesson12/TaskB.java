package by.it.group151002.naftolsky.lesson12;

public class TaskB {
    public static final int VERTICES = 10;
    private static final char START = 'A';
    public int MAX_VALUE = 9999;
    public int[] dist = new int[VERTICES];
    public int[] path = new int[VERTICES];

    public int[][] graphMatrix =  {
                                {MAX_VALUE, 4, -2, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE},    //A
                                {MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, -2, -4, MAX_VALUE, MAX_VALUE},   //B
                                {MAX_VALUE, MAX_VALUE, MAX_VALUE, 2, MAX_VALUE, 1, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE},     //C
                                {MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE}, //D
                                {MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, -2, MAX_VALUE, 3, MAX_VALUE, MAX_VALUE},    //E
                                {MAX_VALUE, MAX_VALUE, MAX_VALUE, 3, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE},   //F
                                {MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, -1, MAX_VALUE},  //G
                                {MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, 1, MAX_VALUE, MAX_VALUE, MAX_VALUE},   //H
                                {MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, 1, MAX_VALUE, MAX_VALUE},   //I
                                {7, MAX_VALUE, 6, MAX_VALUE, 6, 5, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE} //S
                            };

    public void shortPaths(int start) {
        for (int i = 0; i < VERTICES; i++) {
            dist[i] = MAX_VALUE;
            path[i] = -1;
        }
        dist[start] = 0;

        for (int i = 0; i < VERTICES - 1; i++) {
            for (int j = 0; j < VERTICES; j++) {
                for (int m = 0; m < VERTICES; m++) {
                    if (graphMatrix[j][m] != MAX_VALUE && dist[m] > dist[j] + graphMatrix[j][m]) {
                        path[m] = j;
                        dist[m] = dist[j] + graphMatrix[j][m];
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        TaskB taskB = new TaskB();
        taskB.shortPaths(0);

        for (int i = 0; i < VERTICES; i++) {
            char nextVer = (char)(START + i);
            if (nextVer == 'J')
                nextVer = 'S';
            System.out.print("The shortest path " + START + " " + nextVer + " equals " +  taskB.dist[i]);
            if (taskB.dist[i] == taskB.MAX_VALUE)
                System.out.print(" (no path)");


            System.out.print("\n");
        }
    }
}
