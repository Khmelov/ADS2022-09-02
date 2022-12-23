package by.it.group151002.chuklin.lesson11;

import java.util.*;

public class GraphC {
    public static List<Character> sources = new ArrayList<>();
    public static List<Character> sinks = new ArrayList<>();
    public static int[] pre;
    public static int[] post;
    public static int clock;
    public static boolean[] visited;
    public static List<Character> topological;
    public static int[] indegree;
    public static int linearizationsNum;

    public static void sort(Graph g){
        visited = new boolean[g.getVertexCount()];
        pre = new int[g.getVertexCount()];
        post = new int[g.getVertexCount()];
        clock = 0;
        for (int i = 0; i < g.getVertexCount(); i++){
            if (!visited[i])
                dfs(g, i);
        }
        Collections.reverse(topological);
    }
    public static void dfs(Graph g, int vertex) {
        visited[vertex] = true;
        pre[vertex] = clock++;
        for (int i: g.getNeighbors(vertex))
            if (!visited[i])
                dfs(g, i);
        topological.add(g.getName(vertex));
        post[vertex] = clock++;
    }

    public static void sinkAndSources(Graph g) {
        for (int i = 0; i < g.getVertexCount(); i++){
            if (g.getNeighbors(i).length == 0)
                sinks.add(g.getName(i));
            boolean isSource = true;
            for (int j = 0; j < g.getVertexCount(); j++)
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
        for (int i = 0; i < g.getVertexCount(); i++) {
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
        for (int i = 0; i < g.getVertexCount(); i++)
            System.out.println(g.getName(i) + ": pre = " + pre[i] + "; post = " + post[i]);
        sinkAndSources(g);
        System.out.println("Topological order: " + topological);
        indegree = new int[g.getVertexCount()];
        for (int i = 0; i < g.getVertexCount(); i++) {
            visited[i] = false;
            for (int j: g.graph.get(i))
                indegree[j]++;
        }
        countLinearizationsNum(g);
        System.out.println("Number of linearizations: " + linearizationsNum);
    }
}
