package by.it.group151004.karvatskaya.lesson11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskB {
    public static final int number = 8;
    public static boolean[] checked;
    public static List<Character> topologic = new ArrayList<>();

    public static void sort(Start graph, int first) {
        checked = new boolean[graph.getVertCount()];
        for (int i = first; i < graph.getVertCount(); i++)
            if (!checked[i])
                dfs(graph, i);
        Collections.reverse(topologic);
    }

    public static void dfs(Start graph, int vert) {
        checked[vert] = true;
        for (int i : graph.getNear(vert))
            if (!checked[i])
                dfs(graph, i);
        topologic.add(graph.getName(vert));
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
        sort(graph, 0);
        System.out.println(topologic);
    }
}