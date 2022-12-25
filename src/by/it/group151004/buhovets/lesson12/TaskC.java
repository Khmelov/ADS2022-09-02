package by.it.group151004.buhovets.lesson12;

public class TaskC {
    static final int INF = 1000000000;

    public static void main(String[] args) {
        int[][] distances = new int[][] {
                { INF, 4, -2, INF, INF, INF, INF, INF, INF, INF },
                { INF, INF, INF, INF, INF, INF, -2, -4, INF, INF },
                { INF, INF, INF, 2, INF, 1, INF, INF, INF, INF },
                { INF, INF, INF, INF, INF, INF, INF, INF, INF, INF },
                { INF, INF, INF, INF, INF, -2, INF, 3, INF, INF },
                { INF, INF, INF, 3, INF, INF, INF, INF, INF, INF },
                { INF, INF, INF, INF, INF, INF, INF, INF, -1, INF },
                { INF, INF, INF, INF, INF, INF, 1, INF, INF, INF },
                { INF, INF, INF, INF, INF, INF, INF, 1, INF, INF },
                { 7, INF, 6, INF, 6, 5, INF, INF, INF, INF } };

        //Floyd Warshell
        for (int k = 0; k < 10; k++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                }

            }
        }

        int u = 0; // начальная вершина ребра (нумеруем с нуля по алфавиту, т.е. это А)
        int v = 3; // конечная вершина ребра (это Д)

        int minLength = Math.min(distances[u][v], Math.min(distances[v][u], distances[u][u] + distances[v][v]));

        System.out.println("Минимальная длина цикла, содержащего ребро (" + u + ", " + v + "): " + minLength);
    }
}
