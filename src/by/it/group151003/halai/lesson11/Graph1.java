package by.it.group151003.halai.lesson11;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph1 {
    public ArrayList<ArrayList<Integer>> graph;
    public int depth;
    public char[] nodeName;

    public Graph1(int count) {
        depth = count;
        graph = new ArrayList<>();
        for (int i = 0; i < depth; i++) {
            graph.add(new ArrayList<>());
        }
        nodeName = new char[depth];
    }

    void addOrientedEdge(int a, int b) {
        graph.get(a).add(b);
    }

    char getName(int index) {
        return nodeName[index];
    }

    int getVertexCount() {
        return depth;
    }

    int[] getNeighbors(int vertex) {
        int[] neighbors = new int[graph.get(vertex).size()];
        int i = 0;
        for (int n : graph.get(vertex))
            neighbors[i++] = n;
        Arrays.sort(neighbors);
        return neighbors;
    }

    void setName(int index, char name) {
        nodeName[index] = name;
    }
}