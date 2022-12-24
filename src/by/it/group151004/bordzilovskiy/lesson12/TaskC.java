package by.it.group151004.bordzilovskiy.lesson12;

public class TaskC {
    public int INF = 1000000000;
    public int u;
    public int v;

    public int[][] map = {
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

    public TaskC(int u, int v) {
        this.u = u;
        this.v = v;
    }

    public int GetWay() {
        for (int k = 0; k < 10; k++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
        return Math.min(map[u][v], Math.min(map[v][u], map[u][u] + map[v][v]));
    }

    public static void main(String[] args) {
        TaskC sus = new TaskC(2, 3);
        int minLength = sus.GetWay();
    }
}