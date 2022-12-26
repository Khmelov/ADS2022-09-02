package by.it.group151002.chuklin.lesson11;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    public ArrayList<ArrayList<Integer>> graph;
    public int nodeCount;
    public char[] nodeName;

    public Graph(int count) {
        nodeCount = count;
        graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < nodeCount; i++) {
            graph.add(new ArrayList<Integer>());
        }
        nodeName = new char[nodeCount];
    }

    void setName(int index, char name) {
        nodeName[index] = name;
    }

    char getName(int index) {

        return nodeName[index];
    }

    void addEdge(int a, int b) {
        graph.get(a).add(b);
        graph.get(b).add(a);
    }
    void addOrientedEdge(int a, int b) {
        graph.get(a).add(b);
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
}
