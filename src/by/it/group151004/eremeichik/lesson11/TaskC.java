package by.it.group151004.eremeichik.lesson11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskC {
    public static int[] preArr;
    public static int[] postArr;
    public static int clock;
    public static boolean[] visitedVertecies;
    public static List<Character> topological = new ArrayList<>();
    public static int[] degree;

    private static void initializeSort(Graph graph){
        visitedVertecies = new boolean[graph.getVertexCount()];
        preArr = new int[graph.getVertexCount()];
        postArr = new int[graph.getVertexCount()];
        clock = 0;
    }

    public static void sort(Graph graph){
        initializeSort(graph);
        for (int i = 0; i < graph.getVertexCount(); i++){
            if (!visitedVertecies[i])
                deepFirstSearch(graph, i);
        }
        Collections.reverse(topological);
    }

    public static void deepFirstSearch(Graph graph, int vertex) {
        visitedVertecies[vertex] = true;
        preArr[vertex] = clock++;
        for (int i: graph.getNeighbors(vertex)) {
            if (!visitedVertecies[i]) {
                deepFirstSearch(graph, i);
            }
        }
        topological.add(graph.getVertexNameByIndex(vertex));
        postArr[vertex] = clock++;
    }

    public static void sinkAndSources(Graph graph) {
        List<Character> sourceList = new ArrayList<>();
        List<Character> sinkList = new ArrayList<>();
        for (int i = 0; i < graph.getVertexCount(); i++){
            if (graph.getNeighbors(i).length == 0) {
                sinkList.add(graph.getVertexNameByIndex(i));
            }
            int index = i;
            if (graph.graph.stream().anyMatch(x->x.contains(index))) {
                sourceList.add(graph.getVertexNameByIndex(i));
            }
        }
        System.out.println("Sources : " + sourceList);
        System.out.println("Sinks : " + sinkList);
    }

    public static int calculateLinNum(Graph graph){
        int count = 0;
        boolean isFound = false;
        for (int i = 0; i < graph.getVertexCount(); i++) {
            if (!visitedVertecies[i] && degree[i] == 0) {
                visitedVertecies[i] = true;
                for (int j : graph.graph.get(i)) {
                    degree[j]--;
                }
                count+= calculateLinNum(graph);
                visitedVertecies[i] = false;
                for (int j: graph.graph.get(i)) {
                    degree[j]++;
                }
                isFound = true;
            }
        }
        return count + (!isFound?1:0);
    }
}
