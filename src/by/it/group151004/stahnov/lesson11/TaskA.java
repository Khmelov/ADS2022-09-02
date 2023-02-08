package by.it.group151004.stahnov.lesson11;

import java.util.Arrays;
import java.util.LinkedList;

public class TaskA {
    public LinkedList<Integer>[] adjacencyLists;
    public String[] vertexNames;

    public TaskA(int numVertices) {
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

    public static class DFS {
        private final boolean[] visited;
        private final int[] pre;
        private final int[] post;
        private int counter;
        public StringBuilder str = new StringBuilder();

        public DFS(TaskA g) {
            visited = new boolean[g.getNumVertices()];
            pre = new int[g.getNumVertices()];
            post = new int[g.getNumVertices()];
            counter = 0;

            for (int v : g.getVerticesInAlphabeticalOrder()) {
                if (!visited[v]) {
                    Explore(g, v);
                }
            }
        }

        public void Explore(TaskA g, int v) {
            visited[v] = true;
            pre[v] = counter++;

            for (int neighbor : g.getNeighborsInAlphabeticalOrder(v)) {
                if (!visited[neighbor]) {
                    str.append(g.getVertexName(v)).append(" - ").append(g.getVertexName(neighbor)).append(" (tree edge) ");
                    //System.out.println(g.getVertexName(v) + " - " + g.getVertexName(neighbor) + " (tree edge)");
                    Explore(g, neighbor);
                } else {
                    str.append(g.getVertexName(v)).append(" - ").append(g.getVertexName(neighbor)).append(" (back edge) ");
                    //System.out.println(g.getVertexName(v) + " - " + g.getVertexName(neighbor) + " (back edge)");
                }
            }

            post[v] = counter++;
        }

        public int getPre(int v) {
            return pre[v];
        }

        public int getPost(int v) {
            return post[v];
        }

        public String getString() {
            return str.toString();
        }
    }

    public static void main(String[] args) {
        TaskA taskA = new TaskA(9);
        taskA.setVertexName(0, "A");
        taskA.setVertexName(1, "B");
        taskA.setVertexName(2, "C");
        taskA.setVertexName(3, "D");
        taskA.setVertexName(4, "E");
        taskA.setVertexName(5, "F");
        taskA.setVertexName(6, "G");
        taskA.setVertexName(7, "H");
        taskA.setVertexName(8, "I");
        taskA.addEdge(3, 7);
        taskA.addEdge(3, 6);
        taskA.addEdge(6, 7);
        taskA.addEdge(0, 1);
        taskA.addEdge(0, 4);
        taskA.addEdge(1, 4);
        taskA.addEdge(1, 2);
        taskA.addEdge(2, 5);
        taskA.addEdge(5, 8);
        taskA.addEdge(4, 5);

        DFS dfs = new DFS(taskA);

        System.out.println(dfs.getString());
        for (int v : taskA.getVerticesInAlphabeticalOrder()) {
            System.out.println(taskA.getVertexName(v) + ": pre = " + dfs.getPre(v) + ", post = " + dfs.getPost(v));
        }
    }
}
