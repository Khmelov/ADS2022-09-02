package by.it.group151001.danko.lesson11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskC {
    public static boolean[] checked;
    public static int[] pred;
    public static int[] post;
    public static String root;
    public static int n;
    public static List<Character> line = new ArrayList<>();

    public static void main(String[] args) {
        GraphBasic graph = new GraphBasic(8);
        graph.setNode(0, 'A');
        graph.setNode(1, 'B');
        graph.setNode(2, 'C');
        graph.setNode(3, 'D');
        graph.setNode(4, 'E');
        graph.setNode(5, 'F');
        graph.setNode(6, 'G');
        graph.setNode(7, 'H');
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        DFS(graph,0);
        for (int i = 0; i < graph.getCount(); i++)
            System.out.println(graph.getNode(i) + ": pre = " + pred[i] + ", post = " + post[i]);
        List<Character> sources = sourcesAndSinks(graph);
        System.out.println("One of linearizations of graph: " + line);
        System.out.println("Number of linearizations: " + sources.size() * 2 * 2);
    }

    public static void search(GraphBasic graph, int ID) {
        checked[ID] = true;
        pred[ID] = n++;
        for (int i : graph.getNeighbors(ID)) {
            if (!checked[i]) {
                search(graph, i);
            }
            line.add(graph.getNode(ID));
            post[ID] = n++;
        }
    }

    public static List<Character> sourcesAndSinks(GraphBasic graph) {
        List<Character> sources = new ArrayList<>();
        List<Character> sinks = new ArrayList<>();
        for (int i = 0; i < graph.getCount(); i++){
            if (graph.getNeighbors(i).length == 0)
                sinks.add(graph.getNode(i));
            boolean isSource = true;
            for (int j = 0; j < graph.getCount(); j++)
                if (graph.graph.get(j).contains(i)){
                    isSource = false;
                    break;
                }
            if (isSource)
                sources.add(graph.getNode(i));
        }
        System.out.println("Sources: " + sources);
        System.out.println("Sinks: " + sinks);
        return sources;
    }

    public static void DFS (GraphBasic graph,int start)
    {
        checked = new boolean[graph.getCount()];
        pred = new int[graph.getCount()];
        post = new int[graph.getCount()];
        n = 0;
        for (int i = start; i < graph.getCount(); i++)
            if (!checked[i]) {
                search(graph, i);
            }
        Collections.reverse(line);
    }
}
