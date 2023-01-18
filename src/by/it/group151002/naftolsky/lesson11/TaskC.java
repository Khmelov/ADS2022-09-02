package by.it.group151002.naftolsky.lesson11;

import java.util.*;

public class TaskC {
    public static final int VERTICES = 8;
    public static int[] pre;
    public static int[] post;
    public static int clock;
    public static boolean[] visited;
    public static List<Character> characterList = new ArrayList<>();

    public static void sort(Graph graph){
        visited = new boolean[graph.getVertexCount()];
        pre = new int[graph.getVertexCount()];
        post = new int[graph.getVertexCount()];
        clock = 0;
        for (int i = 0; i < graph.getVertexCount(); i++){
            if (!visited[i])
                dfs(graph, i);
        }
        Collections.reverse(characterList);
    }
    public static void dfs(Graph graph, int vertex) {
        visited[vertex] = true;
        pre[vertex] = clock++;
        for (int i: graph.getNeighbors(vertex))
            if (!visited[i])
                dfs(graph, i);
        characterList.add(graph.getName(vertex));
        post[vertex] = clock++;
    }

    public static List<Character> sinkAndSources(Graph graph) {
        List<Character> sources = new ArrayList<>();
        List<Character> sinks = new ArrayList<>();
        for (int i = 0; i < graph.getVertexCount(); i++){
            if (graph.getNeighbors(i).length == 0)
                sinks.add(graph.getName(i));
            boolean isSource = true;
            for (int j = 0; j < graph.getVertexCount(); j++)
                if (graph.graph.get(j).contains(i)){
                    isSource = false;
                    break;
                }
            if (isSource)
                sources.add(graph.getName(i));
        }
        System.out.println("Sources: " + sources);
        System.out.println("Sinks: " + sinks);
        return sources;
    }

    public static void main(String[] args) {
        Graph graph = new Graph(VERTICES);
        graph.setName(0, 'A');
        graph.setName(1, 'B');
        graph.setName(2, 'C');
        graph.setName(3, 'D');
        graph.setName(4, 'E');
        graph.setName(5, 'F');
        graph.setName(6, 'G');
        graph.setName(7, 'H');
        graph.addOrEdge(0, 2);
        graph.addOrEdge(1, 2);
        graph.addOrEdge(2, 3);
        graph.addOrEdge(2, 4);
        graph.addOrEdge(3, 5);
        graph.addOrEdge(4, 5);
        graph.addOrEdge(5, 6);
        graph.addOrEdge(5, 7);
        sort(graph);
        for (int i = 0; i < graph.getVertexCount(); i++)
            System.out.println(graph.getName(i) + ": pre = " + pre[i] + ", post = " + post[i]);
        List<Character> sources = sinkAndSources(graph);
        System.out.println("One of linearizations of graph: " + characterList);
        System.out.println("Number of linearizations: " + sources.size() * 2 * 2);
    }
}
