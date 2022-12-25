package by.it.group151002.ravodin.lesson11;

import java.util.*;

public class GraphC {
    public static List<Character> starts = new ArrayList<>();
    public static List<Character> sinks = new ArrayList<>();
    public static int[] before;
    public static int[] after;
    public static int time;
    public static boolean[] isVisited;
    public static List<Character> topological;
    public static int[] pow;

    public static int linearizationsNum;

    public static void sort(Graph g){
        isVisited = new boolean[g.getVertexNumber()];
        before = new int[g.getVertexNumber()];
        after = new int[g.getVertexNumber()];
        time = 0;
        for (int i = 0; i < g.getVertexNumber(); i++){
            if (!isVisited[i])
                dfs(g, i);
        }
        Collections.reverse(topological);
    }
    public static void dfs(Graph g, int vertex) {
        isVisited[vertex] = true;
        before[vertex] = time++;
        for (int i: g.getNeighbors(vertex))
            if (!isVisited[i])
                dfs(g, i);
        topological.add(g.getName(vertex));
        after[vertex] = time++;
    }

    public static void sinkAndSources(Graph graph) {
        for (int i = 0; i < graph.getVertexNumber(); i++){
            if (graph.getNeighbors(i).length == 0)
                sinks.add(graph.getName(i));
            boolean isStart = true;
            for (int j = 0; j < graph.getVertexNumber(); j++)
                if (graph.graph.get(j).contains(i)){
                    isStart = false;
                    break;
                }
            if (isStart)
                starts.add(graph.getName(i));
        }
        System.out.println("Sources: " + starts);
        System.out.println("Sinks: " + sinks);
    }

    public static void countLinearizationsNum(Graph g){
        boolean isChecked = false;
        for (int i = 0; i < g.getVertexNumber(); i++) {
            if (!isVisited[i] && pow[i] == 0) {
                isVisited[i] = true;
                for (int j : g.graph.get(i)) {
                    pow[j]--;
                }
                countLinearizationsNum(g);
                isVisited[i] = false;
                for (int j: g.graph.get(i)) {
                    pow[j]++;
                }
                isChecked = true;
            }
        }
        if (!isChecked)
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
        for (int i = 0; i < g.getVertexNumber(); i++)
            System.out.println(g.getName(i) + ": pre = " + before[i] + "; post = " + after[i]);
        sinkAndSources(g);
        System.out.println("Topological order: " + topological);
        pow = new int[g.getVertexNumber()];
        for (int i = 0; i < g.getVertexNumber(); i++) {
            isVisited[i] = false;
            for (int j: g.graph.get(i))
                pow[j]++;
        }
        countLinearizationsNum(g);
        System.out.println("Number of linearizations: " + linearizationsNum);
    }
}