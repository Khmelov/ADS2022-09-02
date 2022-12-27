package by.it.group151003.mytnik.lesson11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Graph {
    private final ArrayList<ArrayList<Integer>> graph;
    private final int vertexCount;
    private final char[] vertexNames;

    public Graph(int count) {
        vertexCount = count;
        graph = new ArrayList<>();

        IntStream.range(0, vertexCount).forEach(i -> graph.add(new ArrayList<>()));

        vertexNames = new char[vertexCount];
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

    public int[] getNeighbors(int vertex) {
        int[] neighbors = new int[graph.get(vertex).size()];

        int i = 0;
        for (int n : graph.get(vertex)) {
            neighbors[i++] = n;
        }

        Arrays.sort(neighbors);
        return neighbors;
    }

    public ArrayList<ArrayList<Integer>> getGraph() {
        return graph;
    }

    public char[] getVertexNames() {
        return vertexNames;
    }


    public int getVertexCount() {
        return vertexCount;
    }
}
