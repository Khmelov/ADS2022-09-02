package by.it.group151004.eremeichik.lesson11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskB {
    public static List<Character> sort(Graph graph){
        List<Character> topological = new ArrayList<>();
        boolean[] visitedVertices = new boolean[graph.getVertexCount()];
        for (int i = 0; i < graph.getVertexCount(); i++){
            if (!visitedVertices[i])
                deepFirstSearch(graph, visitedVertices,i,topological);
        }
        Collections.reverse(topological);
        return topological;
    }

    public static void deepFirstSearch(Graph graph, boolean[] visited, int vertex, List<Character> topological) {
        visited[vertex] = true;
        for (int i: graph.getNeighbors(vertex)) {
            if (!visited[i]) {
                deepFirstSearch(graph, visited, i, topological);
            }
        }
        topological.add(graph.getVertexNameByIndex(vertex));
    }
}
