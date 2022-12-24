package by.it.group151004.eremeichik.lesson11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Graph {
    private final int vertexCount;
    private final char[] vertexNames;
    public List<List<Integer>> graph;

    public Graph(int vertexCount) {
        this.vertexCount = vertexCount;
        vertexNames = new char[vertexCount];
        initializeGraph(vertexCount);
    }

    private void initializeGraph(int size){
        graph = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            graph.add(new ArrayList<>());
        }
    }

    char getVertexNameByIndex(int index) {
        return vertexNames[index];
    }

    void setVertexNameByIndex(int index, char name) {
        vertexNames[index] = name;
    }

    void addEdge(int a, int b) {
        graph.get(a).add(b);
        graph.get(b).add(a);
    }

    void addOrEdge(int a, int b) {
        graph.get(a).add(b);
    }

    int getVertexCount() {
        return vertexCount;
    }

    int[] getNeighbors(int vertex) {
        int[] neighbors = new int[graph.get(vertex).size()];
        for(int i = 0;i<neighbors.length;i++){
            neighbors[i] = graph.get(vertex).get(i);
        }
        Arrays.sort(neighbors);
        return neighbors;
    }
}
