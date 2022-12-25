package by.it.group151002.ravodin.lesson11;

import java.util.*;

public class GraphB {

    public static void dfs(Graph graph, int vert) {
        isVisited[vert] = true;
        int j = 0;
        for (int i: graph.getNeighbors(vert)){
            j++;
            if (!isVisited[i])
                dfs(graph, i);
        }
        topological.add(graph.getName(vert));
    }

    public static List<Character> topological;
    public static boolean[] isVisited;

    public static void sort(Graph g){
        isVisited = new boolean[g.getVertexNumber()];
        for (int i = 0; i < g.getVertexNumber(); i++){
            if (!isVisited[i])
                dfs(g, i);
        }
        Collections.reverse(topological);
    }

    public static void main(String[] args) {
        Graph g = new Graph(8);
        g.setName(0, 'A');
        g.setName(1, 'B');
        g.setName(2, 'C');
        g.setName(3, 'D');
        g.setName(4, 'E');
        g.setName(5, 'F');
        g.setName(6, 'G');
        g.setName(7, 'H');
        g.addOrEdge(0, 2);
        g.addOrEdge(1, 2);
        g.addOrEdge(2, 3);
        g.addOrEdge(2, 4);
        g.addOrEdge(3, 5);
        g.addOrEdge(4, 5);
        g.addOrEdge(5, 6);
        g.addOrEdge(5, 7);
        topological = new ArrayList<>();
        sort(g);
        System.out.println(topological);
    }
}