package by.it.group151002.krashevskiy.lesson11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Graph {

    private ArrayList<ArrayList<Integer>> graph;
    private int size;
    private char[] vertexNames;

    private List<Character> topological = new ArrayList<>();
    private boolean[] visited;
    private int[] pre;
    private int[] post;
    private String res;
    private int clock;

    public Graph(int count) {
        size = count;
        graph = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            graph.add(new ArrayList<>());
        }
        vertexNames = new char[size];
    }

    public void setName(int index, char name) {
        vertexNames[index] = name;
    }

    public char getName(int index) {
        return vertexNames[index];
    }

    public void addEdge(int a, int b) {
        graph.get(a).add(b);
        graph.get(b).add(a);
    }

    public void addOrientedEdge(int a, int b) {
        graph.get(a).add(b);
    }

    public int getSize() {
        return size;
    }

    public int[] getNeighbors(int vertex) {
        int[] neighbors = new int[graph.get(vertex).size()];
        int i = 0;
        for (int n : graph.get(vertex))
            neighbors[i++] = n;
        Arrays.sort(neighbors);
        return neighbors;
    }

    public void dfs(int start) {
        res = "";
        visited = new boolean[getSize()];
        pre = new int[getSize()];
        post = new int[getSize()];
        clock = 0;
        for (int i = start; i < getSize(); i++)
            if (!visited[i]) {
                search(i);
                res += "\n";
            }
    }

    public void search(int vertex) {
        visited[vertex] = true;
        pre[vertex] = clock++;
        for (int i : getNeighbors(vertex)) {
            if (!visited[i]) {
                res += getName(vertex) + " - " + getName(i) + " (tree) ";
                search(i);
            } else if (pre[vertex] > pre[i] && post[vertex] <= post[i])
                res += getName(vertex) + " - " + getName(i) + " (back) ";

        }
        post[vertex] = clock++;
    }

    public String getRes() {
        return res;
    }

    public List<Character> getTopological() {
        return topological;
    }

    public void sort(int start){
        visited = new boolean[size];
        for (int i = start; i < size; i++){
            if (!visited[i])
                topologicalDFS(i);
        }
        Collections.reverse(topological);
    }
    public void topologicalDFS(int vertex) {
        visited[vertex] = true;
        for (int i: getNeighbors(vertex))
            if (!visited[i])
                topologicalDFS(i);
        topological.add(getName(vertex));
    }

    public List<Character> sinkAndSources() {
        List<Character> sources = new ArrayList<>();
        List<Character> sinks = new ArrayList<>();
        for (int i = 0; i < size; i++){
            if (getNeighbors(i).length == 0)
                sinks.add(getName(i));
            boolean isSource = true;
            for (int j = 0; j < size; j++)
                if (graph.get(j).contains(i)){
                    isSource = false;
                    break;
                }
            if (isSource)
                sources.add(getName(i));
        }
        System.out.println("Sources: " + sources);
        System.out.println("Sinks: " + sinks);
        return sources;
    }
}
