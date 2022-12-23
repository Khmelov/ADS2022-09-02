package by.it.group151002.chuklin.lesson11;
import java.util.*;
public class GraphB {
    public static boolean[] visited;
    public static List<Character> topological;

    public static void sort(Graph g){
        visited = new boolean[g.getVertexCount()];
        for (int i = 0; i < g.getVertexCount(); i++){
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
        topological.add(g.getName(vertex));
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
