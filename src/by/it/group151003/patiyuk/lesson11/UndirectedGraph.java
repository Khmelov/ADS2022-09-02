package by.it.group151003.patiyuk.lesson11;

import java.io.InputStream;
import java.util.*;

public class UndirectedGraph {
    public LinkedList<Integer>[] adjacencyLists;
    public String[] vertexNames;

    public UndirectedGraph(int numVertices) {
        adjacencyLists = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjacencyLists[i] = new LinkedList<>();
        }
        vertexNames = new String[numVertices];
    }

    public void addEdge(int v1, int v2) {
        adjacencyLists[v1].add(v2);
        adjacencyLists[v2].add(v1);
    }

    public void setVertexName(int v, String name) {
        vertexNames[v] = name;
    }

    public String getVertexName(int v) {
        return vertexNames[v];
    }

    public int getNumVertices() {
        return adjacencyLists.length;
    }

    public LinkedList<Integer> getNeighbors(int v) {
        return adjacencyLists[v];
    }

    public int[] getVerticesInAlphabeticalOrder() {
        int[] vertices = new int[getNumVertices()];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = i;
        }
        Arrays.sort(vertices);
        return vertices;
    }

    public int[] getNeighborsInAlphabeticalOrder(int v) {
        int[] neighbors = new int[adjacencyLists[v].size()];
        int i = 0;
        for (int neighbor : adjacencyLists[v]) {
            neighbors[i] = neighbor;
            i++;
        }
        Arrays.sort(neighbors);
        return neighbors;
    }

    public void loadGraph(String filename) {
        InputStream inputStream = UndirectedGraph.class.getResourceAsStream(filename);
        assert inputStream != null;
        Scanner scanner = new Scanner(inputStream);
        int numVertices = scanner.nextInt();
        for (int i = 0; i < numVertices; i++) {
            int vertex = scanner.nextInt();
            String vertexName = scanner.next();
            setVertexName(vertex, vertexName);
        }
        while (scanner.hasNext()) {
            int v1 = scanner.nextInt();
            int v2 = scanner.nextInt();
            addEdge(v1, v2);
        }
    }
}
