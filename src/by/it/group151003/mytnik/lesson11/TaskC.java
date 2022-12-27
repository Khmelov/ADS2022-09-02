package by.it.group151003.mytnik.lesson11;

import java.util.*;

public class TaskC {
    public static final int AMOUNT_OF_VERTICES = 8;
    public static int[] pre;
    public static int[] post;
    public static int clock;
    public static boolean[] visited;
    public static List<Character> linearization = new ArrayList<>();

    public static void sort(Graph graph) {
        visited = new boolean[graph.getVertexCount()];
        pre = new int[graph.getVertexCount()];
        post = new int[graph.getVertexCount()];
        clock = 0;
        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (!visited[i]) {
                dfs(graph, i);
            }
        }
        Collections.reverse(linearization);
    }

    public static void dfs(Graph graph, int vertex) {
        visited[vertex] = true;
        pre[vertex] = clock++;
        for (int i : graph.getNeighbors(vertex)) {
            if (!visited[i]) {
                dfs(graph, i);
            }
        }
        linearization.add(graph.getName(vertex));
        post[vertex] = clock++;
    }

    public static List<Character> sinkAndSources(Graph graph) {
        List<Character> sources = new ArrayList<>();
        List<Character> sinks = new ArrayList<>();
        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (graph.getNeighbors(i).length == 0) {
                sinks.add(graph.getName(i));
            }
            boolean isSource = true;
            for (int j = 0; j < graph.getVertexCount(); j++) {
                if (graph.getGraph().get(j).contains(i)) {
                    isSource = false;
                    break;
                }
            }
            if (isSource) {
                sources.add(graph.getName(i));
            }
        }
        System.out.println("Sources: " + sources);
        System.out.println("Sinks: " + sinks);
        return sources;
    }
}