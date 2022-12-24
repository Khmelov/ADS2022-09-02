package by.it.group151001.kononova.lesson12;
import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    public ArrayList<ArrayList<Integer>> graph;
    public int vertexCount;
    public char[] vertexNames;

    public Graph(int count) {
        vertexCount = count;
        graph = new ArrayList<>();
        for (int i = 0; i < vertexCount; i++) {
            graph.add(new ArrayList<>());
        }
        vertexNames = new char[vertexCount];
    }

    void addOrientedEdge(int a, int b) {
        graph.get(a).add(b);
    }

    char getName(int index) {
        return vertexNames[index];
    }

    int getVertexCount() {
        return vertexCount;
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
        vertexNames[index] = name;
    }
}
