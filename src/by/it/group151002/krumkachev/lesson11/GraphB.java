package by.it.group151002.krumkachev.lesson11;

import java.util.*;
public class GraphB {
    public static boolean[] visited;
    public static List<Character> topological;

    public static void sort(Graph g){
        visited = new boolean[g.getNumberOfVertex()];
        for (int i = 0; i < g.getNumberOfVertex(); i++){
            if (!visited[i])
                dfs(g, i);
        }
        Collections.reverse(topological);
    }
    public static void dfs(Graph g, int vertex) {
        visited[vertex] = true;
        for (int i: g.getNeighbors(vertex))
            if (!visited[i])
                dfs(g, i);
        topological.add(g.getChar(vertex));
    }

    public static void main(String[] args) {
        Graph g = new Graph(8);
        g.setChar(0, 'A');
        g.setChar(1, 'B');
        g.setChar(2, 'C');
        g.setChar(3, 'D');
        g.setChar(4, 'E');
        g.setChar(5, 'F');
        g.setChar(6, 'G');
        g.setChar(7, 'H');
        g.addOrientedEdge(0, 2);
        g.addOrientedEdge(1, 2);
        g.addOrientedEdge(2, 3);
        g.addOrientedEdge(2, 4);
        g.addOrientedEdge(3, 5);
        g.addOrientedEdge(4, 5);
        g.addOrientedEdge(5, 6);
        g.addOrientedEdge(5, 7);
        topological = new ArrayList<>();
        sort(g);
        System.out.println(topological);
    }
}
