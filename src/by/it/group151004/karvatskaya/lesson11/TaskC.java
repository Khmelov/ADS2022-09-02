package by.it.group151004.karvatskaya.lesson11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskC {
    public static final int number = 8;
    public static int[] pred;
    public static int[] after;
    public static int clock;
    public static boolean[] checked;
    public static List<Character> linear = new ArrayList<>();

    public static void sort(Start graph) {
        checked = new boolean[graph.getVertCount()];
        pred = new int[graph.getVertCount()];
        after = new int[graph.getVertCount()];
        clock = 0;
        for (int i = 0; i < graph.getVertCount(); i++) {
            if (!checked[i])
                dfs(graph, i);
        }
        Collections.reverse(linear);
    }

    public static void dfs(Start graph, int vert) {
        checked[vert] = true;
        pred[vert] = clock++;
        for (int i : graph.getNear(vert))
            if (!checked[i])
                dfs(graph, i);
        linear.add(graph.getName(vert));
        after[vert] = clock++;
    }

    public static List<Character> sinkAndSources(Start graph) {
        List<Character> source = new ArrayList<>();
        List<Character> sink = new ArrayList<>();
        for (int i = 0; i < graph.getVertCount(); i++) {
            if (graph.getNear(i).length == 0)
                sink.add(graph.getName(i));
            boolean isSource = true;
            for (int j = 0; j < graph.getVertCount(); j++)
                if (graph.graph.get(j).contains(i)) {
                    isSource = false;
                    break;
                }
            if (isSource)
                source.add(graph.getName(i));
        }
        System.out.println("Sources: " + source);
        System.out.println("Sinks: " + sink);
        return source;
    }

    public static void main(String[] args) {
        Start graph = new Start(number);
        graph.setName(0, 'A');
        graph.setName(1, 'B');
        graph.setName(2, 'C');
        graph.setName(3, 'D');
        graph.setName(4, 'E');
        graph.setName(5, 'F');
        graph.setName(6, 'G');
        graph.setName(7, 'H');
        graph.addOrientEdge(0, 2);
        graph.addOrientEdge(1, 2);
        graph.addOrientEdge(2, 3);
        graph.addOrientEdge(2, 4);
        graph.addOrientEdge(3, 5);
        graph.addOrientEdge(4, 5);
        graph.addOrientEdge(5, 6);
        graph.addOrientEdge(5, 7);
        sort(graph);
        for (int i = 0; i < graph.getVertCount(); i++)
            System.out.println(graph.getName(i) + ": pred = " + pred[i] + ", post = " + after[i]);
        List<Character> source = sinkAndSources(graph);
        System.out.println("1 of linearizations of graph: " + linear);
        System.out.println("All of linearizations: " + source.size() * 2 * 2);
    }
}
