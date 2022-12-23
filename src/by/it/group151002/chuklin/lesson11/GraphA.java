package by.it.group151002.chuklin.lesson11;

import java.util.*;

public class GraphA {
    public static boolean[] visited;
    public static int[] pre;
    public static int[] post;
    public static String res;
    public static int clock;

    public static void dfs(Graph g, int vertex) {
        res = new String();
        visited = new boolean[g.getVertexCount()];
        pre = new int[g.getVertexCount()];
        post = new int[g.getVertexCount()];
        clock = 0;
        for (int i = 0; i < g.getVertexCount(); i++)
            if (!visited[i]) {
                search(g, i);
                res = res + "\n";
            }
    }

    public static void search(Graph g, int vertex) {
        visited[vertex] = true;
        pre[vertex] = clock++;
        for (int i : g.getNeighbors(vertex)) {
            if (!visited[i]) {
                res = res + g.getName(vertex) + " - " + g.getName(i) + " (tree) ";
                search(g, i);
            } else if (pre[vertex] > pre[i] && post[vertex] <= post[i])
                res = res + g.getName(vertex) + " - " + g.getName(i) + " (back) ";

        }
        post[vertex] = clock++;
    }

    public static void main(String[] args) {
        Graph g = new Graph(9);
        g.setName(0, 'A');
        g.setName(1, 'B');
        g.setName(2, 'C');
        g.setName(3, 'D');
        g.setName(4, 'E');
        g.setName(5, 'F');
        g.setName(6, 'G');
        g.setName(7, 'H');
        g.setName(8, 'I');
        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 4);
        g.addEdge(1, 2);
        g.addEdge(2, 5);
        g.addEdge(4, 5);
        g.addEdge(5, 8);
        g.addEdge(3, 6);
        g.addEdge(7, 6);
        g.addEdge(3, 7);
        dfs(g, 0);
        System.out.println(res);
        for (int i = 0; i < g.getVertexCount(); i++)
            System.out.println(g.getName(i) + ": pre = " + pre[i] + "; post = " + post[i]);
    }
}
