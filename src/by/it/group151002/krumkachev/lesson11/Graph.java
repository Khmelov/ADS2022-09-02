package by.it.group151002.krumkachev.lesson11;
import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
    public ArrayList<ArrayList<Integer>> graph;
    public int numberOfVertex;
    public char[] vertexChars;

    public Graph(int count) {
        numberOfVertex = count;
        graph = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < numberOfVertex; i++) {
            graph.add(new ArrayList<Integer>());
        }
        vertexChars = new char[numberOfVertex];
    }

    int getNumberOfVertex() {
        return numberOfVertex;
    }

    void setChar(int index, char c) {
        vertexChars[index] = c;
    }

    char getChar(int index) {
        return vertexChars[index];
    }

    void addEdge(int a, int b) {
        graph.get(a).add(b);
        graph.get(b).add(a);
    }
    void addOrientedEdge(int a, int b) {
        graph.get(a).add(b);
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