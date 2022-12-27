package by.it.group151003.mytnik.lesson11;

public class TaskA {
    public static final int AMOUNT_OF_VERTICES = 9;
    private static boolean[] visited;
    private static int[] pre;
    private static int[] post;

    public static StringBuilder result;
    private static int clock;

    public static void dfs(Graph graph, int start) {
        result = new StringBuilder();
        visited = new boolean[graph.getVertexCount()];
        pre = new int[graph.getVertexCount()];
        post = new int[graph.getVertexCount()];
        clock = 0;

        for (int i = start; i < graph.getVertexCount(); i++) {
            if (!visited[i]) {
                search(graph, i);
                result.append("\n");
            }
        }
    }

    private static void search(Graph graph, int vertex) {
        visited[vertex] = true;
        pre[vertex] = clock++;

        for (int i : graph.getNeighbors(vertex)) {
            if (!visited[i]) {
                result.append(graph.getName(vertex)).append(" - ").append(graph.getName(i)).append(" (tree) ");
                search(graph, i);
            } else if (pre[vertex] > pre[i] && post[vertex] <= post[i]) {
                result.append(graph.getName(vertex)).append(" - ").append(graph.getName(i)).append(" (back) ");
            }
        }

        post[vertex] = clock++;
    }
}