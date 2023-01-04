package by.it.group151003.baranouski.lesson12;

public class TaskC {
    public int INF = 1000000000;
    public int m_u;
    public int m_v;

    public int[][] matrix = {
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
        this.m_u = u;
        this.m_v = v;
    }

    public int floydWarshell() {
        for (int k = 0; k < 10; k++) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }
        return Math.min(matrix[m_u][m_v], Math.min(matrix[m_v][m_u], matrix[m_u][m_u] + matrix[m_v][m_v]));
    }

    public static void main(String[] args) {
        TaskC sus = new TaskC(2, 3);
        int minLength = sus.floydWarshell();
        System.out.println("Minimum length of a cycle containing an edge (" + (char) ('A' + sus.m_u) + ", " + (char) ('A' + sus.m_v) + "): " + minLength);
    }
}
