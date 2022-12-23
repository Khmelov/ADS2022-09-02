package by.it.group151004.akimenko.lesson11;

import java.util.*;

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

    public static class DFS {
        public boolean[] visited;
        public int[] pre;
        public int[] post;
        public int preCounter;
        public int postCounter;

        public DFS(TaskA g) {
            visited = new boolean[g.getNumVertices()];
            pre = new int[g.getNumVertices()];
            post = new int[g.getNumVertices()];
            preCounter = 0;
            postCounter = 0;

            for (int v : g.getVerticesInAlphabeticalOrder()) {
                if (!visited[v]) {
                    dfs(g, v);
                }
            }
        }

        public void dfs(TaskA g, int v) {
            visited[v] = true;
            pre[v] = preCounter++;

            for (int neighbor : g.getNeighborsInAlphabeticalOrder(v)) {
                if (!visited[neighbor]) {
                    System.out.println(g.getVertexName(v) + " - " + g.getVertexName(neighbor) + " (tree edge)");
                    dfs(g, neighbor);
                } else {
                    System.out.println(g.getVertexName(v) + " - " + g.getVertexName(neighbor) + " (back edge)");
                }
            }

            post[v] = postCounter++;
        }

        public int getPre(int v) {
            return pre[v];
        }

        public int getPost(int v) {
            return post[v];
        }
    }

    public static void main(String[] args) {
        TaskA aVar = new TaskA(9);
        aVar.setVertexName(0, "A");
        aVar.setVertexName(1, "B");
        aVar.setVertexName(2, "C");
        aVar.setVertexName(3, "D");
        aVar.setVertexName(4, "E");
        aVar.setVertexName(5, "F");
        aVar.setVertexName(6, "G");
        aVar.setVertexName(7, "H");
        aVar.setVertexName(8, "I");
        aVar.addEdge(3, 7);
        aVar.addEdge(3, 6);
        aVar.addEdge(6, 7);
        aVar.addEdge(0, 1);
        aVar.addEdge(0, 4);
        aVar.addEdge(1, 4);
        aVar.addEdge(1, 2);
        aVar.addEdge(2, 5);
        aVar.addEdge(5, 8);

        DFS dfs = new DFS(aVar);

        for (int v : aVar.getVerticesInAlphabeticalOrder()) {
            System.out.println(aVar.getVertexName(v) + ": pre = " + dfs.getPre(v) + ", post = " + dfs.getPost(v));
        }
    }
}
