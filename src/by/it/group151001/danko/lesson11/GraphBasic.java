package by.it.group151001.danko.lesson11;

import java.util.ArrayList;
import java.util.Arrays;

public class GraphBasic {
    public ArrayList<ArrayList<Integer>> graph;

    public int nodeCount;
    public char[] nodes;

    public GraphBasic(int count) {
        nodeCount = count;
        graph = new ArrayList<>();
        for(int i = 0; i < nodeCount; i++) {
            graph.add(new ArrayList<>());
        }
        nodes = new char[nodeCount];
    }

    void addEdge(int a, int b) {
        graph.get(a).add(b);
    }

    char getNode(int index) {return nodes[index];}

    int getCount() { return nodeCount; }

    int[] getNeighbors(int ID)
    {
        int[] neighbors = new int[graph.get(ID).size()];
        int size = 0;
        for(int n : graph.get(ID))
            neighbors[size++] = n;
        Arrays.sort(neighbors);
        return neighbors;
    }

    void setNode(int index, char name) { nodes[index] = name;}
}
