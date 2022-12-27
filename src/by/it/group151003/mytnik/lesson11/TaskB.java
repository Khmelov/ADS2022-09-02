package by.it.group151003.mytnik.lesson11;

import java.util.*;

public class TaskB {
    public static final int AMOUNT_OF_VERTICES = 8;
    private static boolean[] visited;
    public static final List<Character> topological = new ArrayList<>();

    public static void sort(Graph graph, int start) {
        visited = new boolean[graph.getVertexCount()];
        for (int i = start; i < graph.getVertexCount(); i++) {
            if (!visited[i]) {
                dfs(graph, i);
            }
        }
        Collections.reverse(topological);
    }

    public static void dfs(Graph graph, int vertex) {
        visited[vertex] = true;
        for (int i : graph.getNeighbors(vertex)) {
            if (!visited[i]) {
                dfs(graph, i);
            }
        }
        topological.add(graph.getName(vertex));
    }
}