package by.it.group151002.kuvshinov.lesson11;

import java.util.*;

public class GraphC {
    public static List<Character> sources = new ArrayList<>();
    public static List<Character> sinks = new ArrayList<>();
    public static int[] pre;
    public static int[] post;
    public static int cl;
    public static boolean[] visited;
    public static List<Character> topological;
    public static int[] indegree;
    public static int linearizationsNum;

    public static void sort(Graph g){
        visited = new boolean[g.getVertCount()];
        post = new int[g.getVertCount()];
        pre = new int[g.getVertCount()];
        cl = 0;
        int i = 0;
        while (i < g.getVertCount())
            if (!visited[i++])
                task(g, i-1);
        Collections.reverse(topological);
    }
    public static void task(Graph g, int vert) {
        visited[vert] = true;
        pre[vert] = cl++;
        for (int i: g.getNeighbors(vert))
            if (!visited[i])
                task(g, i);
        topological.add(g.getName(vert));
        post[vert] = cl++;
    }

    public static void sinkAndSources(Graph g) {
        for (int i = 0; i < g.getVertCount(); i++){
            if (g.getNeighbors(i).length == 0)
                sinks.add(g.getName(i));
            boolean isSource = true;
            for (int j = 0; j < g.getVertCount(); j++)
                if (g.graph.get(j).contains(i)){
                    isSource = false;
                    break;
                }
            if (isSource)
                sources.add(g.getName(i));
        }
        System.out.println("Sources: " + sources);
        System.out.println("Sinks: " + sinks);
    }

    public static void countLinearizationsNum(Graph g){
        boolean flag = false;
        int i = 0;
        while (i < g.getVertCount()) {
            if (!visited[i] && indegree[i] == 0) {
                visited[i] = true;
                for (int j : g.graph.get(i)) {
                    indegree[j]--;
                }
                countLinearizationsNum(g);
                visited[i] = false;
                for (int j: g.graph.get(i)) {
                    indegree[j]++;
                }
                flag = true;
            }
            i++;
        }
        if (!flag)
            linearizationsNum++;
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
        int i = 0;
        while (i < g.getVertCount()) {
            System.out.println(g.getName(i) + ": pre = " + pre[i] + "; post = " + post[i]);
            i++;
        }
        sinkAndSources(g);
        System.out.println("Topological order: " + topological);
        indegree = new int[g.getVertCount()];
        i = 0;
        while (i < g.getVertCount()) {
            visited[i] = false;
            for (int j: g.graph.get(i))
                indegree[j]++;
            i++;
        }
        countLinearizationsNum(g);
        System.out.println("Number of linearizations: " + linearizationsNum);
    }
}
