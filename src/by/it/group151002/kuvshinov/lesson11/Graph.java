package by.it.group151002.kuvshinov.lesson11;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    public ArrayList<ArrayList<Integer>> graph;
    public int vertCount;
    public char[] vertexNames;

    public Graph(int count) {
        vertCount = count;
        graph = new ArrayList<>();
        for (int i = 0; i < vertCount; i++) {
            graph.add(new ArrayList<>());
        }
        vertexNames = new char[vertCount];
    }

    int[] getNeighbors(int vertex) {
        int[] neighbors = new int[graph.get(vertex).size()];
        int n = 0;
        for (int i : graph.get(vertex)) {
            neighbors[n++] = i;
        }
        Arrays.sort(neighbors);
        return neighbors;
    }

    int getVertCount() {
        return vertCount;
    }

    void setName(int index, char name) {
        vertexNames[index] = name;
    }

    void addEdge(int a, int b) {
        graph.get(a).add(b);
        graph.get(b).add(a);
    }

    char getName(int index) {
        return vertexNames[index];
    }

    void addOrEdge(int a, int b) {
        graph.get(a).add(b);
    }
}
