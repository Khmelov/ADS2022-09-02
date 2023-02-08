package by.it.group151003.harlap.lesson12;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    public ArrayList<ArrayList<Integer>> graph;
    public int nodeCount;
    public char[] nodeNames;

    public Graph(int count) {
        nodeCount = count;
        graph = new ArrayList<>();
        for (int i = 0; i < nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
        nodeNames = new char[nodeCount];
    }

    void addOrientedEdge(int a, int b) {
        graph.get(a).add(b);
    }

    char getName(int index) {
        return nodeNames[index];
    }

    int getVertexCount() {
        return nodeCount;
    }

    int[] getNeighbors(int vertex) {
        int[] neighbors = new int[graph.get(vertex).size()];
        int i = 0;
        for (int n : graph.get(vertex))
            neighbors[i++] = n;
        Arrays.sort(neighbors);
        return neighbors;
    }

    void setName(int index, char name) { nodeNames[index] = name;
    }
}
