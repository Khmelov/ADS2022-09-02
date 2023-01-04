package by.it.group151003.baranouski.lesson11;

import java.util.Arrays;
import java.util.LinkedList;

public class TaskA {
    public LinkedList<Integer>[] m_adjacencyLists;
    public String[] m_vertexNames;

    public TaskA(int numVertices) {
        m_adjacencyLists = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            m_adjacencyLists[i] = new LinkedList<>();
        }
        m_vertexNames = new String[numVertices];
    }

    public void addEdge(int v1, int v2) {
        m_adjacencyLists[v1].add(v2);
        m_adjacencyLists[v2].add(v1);
    }

    public void setVertexName(int v, String name) {
        m_vertexNames[v] = name;
    }

    public String getVertexName(int v) {
        return m_vertexNames[v];
    }

    public int getNumVertices() {
        return m_adjacencyLists.length;
    }

    public int[] getVerticesInAlphabeticalOrder() {
        int[] vertices = new int[getNumVertices()];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = i;
        }
        return vertices;
    }

    public int[] getNeighborsInAlphabeticalOrder(int v) {
        int[] neighbors = new int[m_adjacencyLists[v].size()];
        int i = 0;
        for (int neighbor : m_adjacencyLists[v]) {
            neighbors[i] = neighbor;
            i++;
        }
        Arrays.sort(neighbors);
        return neighbors;
    }

    public static class DFS {
        public boolean[] m_visited;
        public int[] m_pre;
        public int[] m_post;
        public int m_preCounter;
        public int m_postCounter;

        public DFS(TaskA g) {
            m_visited = new boolean[g.getNumVertices()];
            m_pre = new int[g.getNumVertices()];
            m_post = new int[g.getNumVertices()];
            m_preCounter = 0;
            m_postCounter = 0;

            for (int v : g.getVerticesInAlphabeticalOrder()) {
                if (!m_visited[v]) {
                    dfs(g, v);
                }
            }
        }

        public void dfs(TaskA g, int v) {
            m_visited[v] = true;
            m_pre[v] = m_preCounter++;

            for (int neighbor : g.getNeighborsInAlphabeticalOrder(v)) {
                if (!m_visited[neighbor]) {
                    System.out.println(g.getVertexName(v) + " - " + g.getVertexName(neighbor) + " (tree edge)");
                    dfs(g, neighbor);
                } else {
                    System.out.println(g.getVertexName(v) + " - " + g.getVertexName(neighbor) + " (back edge)");
                }
            }

            m_post[v] = m_postCounter++;
        }

        public int getPre(int v) {
            return m_pre[v];
        }

        public int getPost(int v) {
            return m_post[v];
        }
    }

    public static void main(String[] args) {
        TaskA g = new TaskA(9);
        g.setVertexName(0, "A");
        g.setVertexName(1, "B");
        g.setVertexName(2, "C");
        g.setVertexName(3, "D");
        g.setVertexName(4, "E");
        g.setVertexName(5, "F");
        g.setVertexName(6, "G");
        g.setVertexName(7, "H");
        g.setVertexName(8, "I");
        g.addEdge(3, 7);
        g.addEdge(3, 6);
        g.addEdge(6, 7);
        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 4);
        g.addEdge(1, 2);
        g.addEdge(2, 5);
        g.addEdge(5, 8);

        DFS dfs = new DFS(g);

        for (int v : g.getVerticesInAlphabeticalOrder()) {
            System.out.println(g.getVertexName(v) + ": pre = " + dfs.getPre(v) + ", post = " + dfs.getPost(v));
        }
    }
}
