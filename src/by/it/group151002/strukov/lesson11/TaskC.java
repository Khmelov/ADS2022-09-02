package by.it.group151002.strukov.lesson11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskC {
    public static boolean[] visited;
    public static int[] prev;
    public static int[] next;
    public static int iterator;
    public static List<Character> linearity = new ArrayList<>();

    public static void search(GraphClass graph, int vertex) {
        visited[vertex] = true;
        prev[vertex] = iterator++;
        for (int i : graph.getNeighbors(vertex)) {
            if (!visited[i])
                search(graph, i);
            linearity.add(graph.getName(vertex));
            next[vertex] = iterator++;
        }
    }

    public static List<Character> sourceAndStocks(GraphClass graph) {
        List<Character> sources = new ArrayList<>();
        List<Character> stocks = new ArrayList<>();
        for (int i = 0; i < graph.getVertexCount(); i++){
            if (graph.getNeighbors(i).length == 0)
                stocks.add(graph.getName(i));
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
        System.out.println("Stocks: " + stocks);
        return sources;
    }

    public static void DFS (GraphClass graph, int start) {
        visited = new boolean[graph.getVertexCount()];
        prev = new int[graph.getVertexCount()];
        next = new int[graph.getVertexCount()];
        iterator = 0;
        for (int i = start; i < graph.getVertexCount(); i++)
            if (!visited[i])
                search(graph, i);
        Collections.reverse(linearity);
    }

    public static void main(String[] args) {
        GraphClass graph = new GraphClass(8);
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
        DFS(graph,0);
        for (int i = 0; i < graph.getVertexCount(); i++)
            System.out.println(graph.getName(i) + ": previous = " + prev[i] + ", next = " + next[i]);
        List<Character> sources = sourceAndStocks(graph);
        System.out.println("One of linearity of graph: " + linearity);
        System.out.println("Number of linearity: " + sources.size() * 4);
    }
}
