package by.it.group151002.kuvshinov.lesson11;
import java.util.*;
public class GraphB {
    public static boolean[] visited;
    public static List<Character> topological;

    public static void sort(Graph g){
        visited = new boolean[g.getVertCount()];
        int i = 0;
        while (i < g.getVertCount())
            if (!visited[i++])
                task(g, i-1);
        Collections.reverse(topological);
    }
    public static void task(Graph g, int vert) {
        visited[vert] = true;
        for (int i: g.getNeighbors(vert))
            if (!visited[i])
                task(g, i);
        topological.add(g.getName(vert));
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
