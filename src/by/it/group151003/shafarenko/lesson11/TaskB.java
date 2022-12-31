package by.it.group151003.shafarenko.lesson11;

import java.util.*;

public class TaskB {
    public static final int AMOUNT_OF_VERTICES = 8;
    public static boolean[] visited;
    public static List<Character> topological = new ArrayList<>();

    public static void sort(Graph graph, int start) {
        visited = new boolean[graph.getVertexCount()];
        for (int i = start; i < graph.getVertexCount(); i++) {
            if (!visited[i])
                dfs(graph, i);
        }
        Collections.reverse(topological);
    }

    public static void dfs(Graph graph, int vertex) {
        visited[vertex] = true;
        for (int i : graph.getNeighbors(vertex))
            if (!visited[i])
                dfs(graph, i);
        topological.add(graph.getName(vertex));
    }

    public static void main(String[] args) {
        Graph graph = new Graph(AMOUNT_OF_VERTICES);
        graph.setName(0, 'A');
        graph.setName(1, 'B');
        graph.setName(2, 'C');
        graph.setName(3, 'D');
        graph.setName(4, 'E');
        graph.setName(5, 'F');
        graph.setName(6, 'G');
        graph.setName(7, 'H');
        graph.addOrientedEdge(0, 2);
        graph.addOrientedEdge(1, 2);
        graph.addOrientedEdge(2, 3);
        graph.addOrientedEdge(2, 4);
        graph.addOrientedEdge(3, 5);
        graph.addOrientedEdge(4, 5);
        graph.addOrientedEdge(5, 6);
        graph.addOrientedEdge(5, 7);
        sort(graph, 0);
        System.out.println(topological);
    }
}
