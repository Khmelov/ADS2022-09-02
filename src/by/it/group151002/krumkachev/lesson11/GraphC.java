package by.it.group151002.krumkachev.lesson11;


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
    public static int numOfLinearizations;

    public static void sort(Graph g){
        visited = new boolean[g.getNumberOfVertex()];
        pre = new int[g.getNumberOfVertex()];
        post = new int[g.getNumberOfVertex()];
        clock = 0;
        for (int i = 0; i < g.getNumberOfVertex(); i++){
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
        topological.add(g.getChar(vertex));
        post[vertex] = clock++;
    }

    public static void sinkAndSources(Graph g) {
        for (int i = 0; i < g.getNumberOfVertex(); i++){
            if (g.getNeighbors(i).length == 0)
                sinks.add(g.getChar(i));
            boolean isSource = true;
            for (int j = 0; j < g.getNumberOfVertex(); j++)
                if (g.graph.get(j).contains(i)){
                    isSource = false;
                    break;
                }
            if (isSource)
                sources.add(g.getChar(i));
        }
        System.out.println("Sources: " + sources);
        System.out.println("Sinks: " + sinks);
    }

    public static void countNumOfLinearizations(Graph g){
        boolean flag = false;
        for (int i = 0; i < g.getNumberOfVertex(); i++) {
            if (!visited[i] && indegree[i] == 0) {
                visited[i] = true;
                for (int j : g.graph.get(i)) {
                    indegree[j]--;
                }
                countNumOfLinearizations(g);
                visited[i] = false;
                for (int j: g.graph.get(i)) {
                    indegree[j]++;
                }
                flag = true;
            }
        }
        if (!flag)
            numOfLinearizations++;
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
        for (int i = 0; i < g.getNumberOfVertex(); i++)
            System.out.println(g.getChar(i) + ": pre = " + pre[i] + "; post = " + post[i]);
        sinkAndSources(g);
        System.out.println("Topological order: " + topological);
        indegree = new int[g.getNumberOfVertex()];
        for (int i = 0; i < g.getNumberOfVertex(); i++) {
            visited[i] = false;
            for (int j: g.graph.get(i))
                indegree[j]++;
        }
        countNumOfLinearizations(g);
        System.out.println("Number of linearizations: " + numOfLinearizations);
    }
}