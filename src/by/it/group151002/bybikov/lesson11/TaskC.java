package by.it.group151002.bybikov.lesson11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskC {

    public static boolean[] visited;
    public static int[] pre;
    public static int[] post;
    public static String res;
    public static int clock;
    public static List<Character> linearization = new ArrayList<>();

    public static void explore(Graph graph, int vertex) {
        visited[vertex] = true;
        pre[vertex] = clock++;
        for (int i : graph.getNeighbors(vertex)) {
            if (visited[i] == false) {
                explore(graph, i);
            }
            linearization.add(graph.getName(vertex));
            post[vertex] = clock++;
        }
    }

        public static void DFS (Graph graph,int start)
        {
            visited = new boolean[graph.getVertexCount()];
            pre = new int[graph.getVertexCount()];
            post = new int[graph.getVertexCount()];
            clock = 0;
            for (int i = start; i < graph.getVertexCount(); i++)
                if (visited[i] == false) {
                    explore(graph, i);
                }
            Collections.reverse(linearization);
        }

    public static List<Character> sinkAndSources(Graph graph) {
        List<Character> sources = new ArrayList<>();
        List<Character> sinks = new ArrayList<>();
        for (int i = 0; i < graph.getVertexCount(); i++){
            if (graph.getNeighbors(i).length == 0)
                sinks.add(graph.getName(i));
            boolean isSource = true;
            for (int j = 0; j < graph.getVertexCount(); j++)
                if (graph.graph.get(j).contains(i)){
                    isSource = false;
                    break;
                }
            if (isSource)
                sources.add(graph.getName(i));
        }
        System.out.println("Sources: " + sources);
        System.out.println("Sinks: " + sinks);
        return sources;
    }
    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.setName(0, 'A');
        graph.setName(1, 'B');
        graph.setName(2, 'C');
        graph.setName(3, 'D');
        graph.setName(4, 'E');
        graph.setName(5, 'F');
        graph.setName(6, 'G');
        graph.setName(7, 'H');
        graph.addOrientedEdge(0, 2);
        graph.addOrientedEdge(1, 2);
        graph.addOrientedEdge(2, 3);
        graph.addOrientedEdge(2, 4);
        graph.addOrientedEdge(3, 5);
        graph.addOrientedEdge(4, 5);
        graph.addOrientedEdge(5, 6);
        graph.addOrientedEdge(5, 7);
        DFS(graph,0);
        for (int i = 0; i < graph.getVertexCount(); i++)
            System.out.println(graph.getName(i) + ": pre = " + pre[i] + ", post = " + post[i]);
        List<Character> sources = sinkAndSources(graph);
        System.out.println("One of linearizations of graph: " + linearization);
        System.out.println("Number of linearizations: " + sources.size() * 2 * 2);
    }
    }

