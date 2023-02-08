package by.it.group151002.kuvshinov.lesson11;

import java.util.*;

public class GraphA {

    public static String res;
    public static boolean[] visited;
    public static int[] pre;
    public static int clock;
    public static int[] post;

    public static void task(Graph g) {
        res = "";
        visited = new boolean[g.getVertCount()];
        pre = new int[g.getVertCount()];
        post = new int[g.getVertCount()];
        clock = 0;
        int i = 0;
        while (i < g.getVertCount()) {
            if (!visited[i]) {
                search(g, i);
                res += "\n";
            }
            i++;
        }
    }

    public static void search(Graph g, int vert) {
        visited[vert] = true;
        pre[vert] = clock;
        clock++;
        for (int i : g.getNeighbors(vert)) {
            if (!visited[i]) {
                res = res + g.getName(vert) + " - " + g.getName(i) + " (tree) ";
                search(g, i);
            } else if (pre[vert] > pre[i] && post[vert] <= post[i])
                res = res + g.getName(vert) + " - " + g.getName(i) + " (back) ";

        }
        post[vert] = clock++;
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
        task(g);
        System.out.println(res);
        int i = 0;
        while (i < g.getVertCount()) {
            System.out.println(g.getName(i) + ": pre = " + pre[i] + "; post = " + post[i]);
            i++;
        }
    }
}
