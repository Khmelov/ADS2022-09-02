package by.it.group151002.ravodin.lesson11;

import java.util.Arrays;
import java.util.ArrayList;

public class Graph {
    public ArrayList<ArrayList<Integer>> graph;
    public int vertexNumber;
    public char[] vertices;

    public Graph(int number) {
        graph = new ArrayList<ArrayList<Integer>>();
        vertexNumber = number;
        for (int i = 0; i < vertexNumber; i++) {
            graph.add(new ArrayList<Integer>());
        }
        vertices = new char[vertexNumber];
    }

    void setName(int index, char name) {
        vertices[index] = name;
    }

    char getName(int index) {
        return vertices[index];
    }

    void addEdge(int e1, int e2) {
        graph.get(e1).add(e2);
        graph.get(e2).add(e1);
    }
    void addOrEdge(int a, int b) {
        graph.get(a).add(b);
    }

    int getVertexNumber() {
        return vertexNumber;
    }

    int[] getNeighbors(int vert) {
        int[] neibs = new int[graph.get(vert).size()];
        int i = 0;
        for (int n : graph.get(vert))
            neibs[i++] = n;
        Arrays.sort(neibs);
        return neibs;
    }
}