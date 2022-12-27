package by.it.group151003.patiyuk.lesson11;

// Apply the depth-first search algorithm to the given graph by going through the vertices in alphabetical order. Label each edge as wood or backward, and calculate the pre- and post-values for each vertex.
// Using undirected graph class

import java.io.ByteArrayInputStream;
import java.io.PrintStream;

public class TaskA {
    public static class DFS {
        private StringBuilder str = new StringBuilder();
        public boolean[] visited;
        public int[] pre;
        public int[] post;
        public int[] parent;
        public int counter;

        public DFS(UndirectedGraph graph) {
            visited = new boolean[graph.getNumVertices()];
            pre = new int[graph.getNumVertices()];
            post = new int[graph.getNumVertices()];
            parent = new int[graph.getNumVertices()];
            for (int i = 0; i < graph.getNumVertices(); i++) {
                parent[i] = -1;
            }
            counter = 0;
            for (int i = 0; i < graph.getNumVertices(); i++) {
                if (!visited[i]) {
                    explore(graph, i);
                }
            }
        }

        private void explore(UndirectedGraph graph, int v) {
            visited[v] = true;
            pre[v] = counter++;
            for (int w : graph.getNeighborsInAlphabeticalOrder(v)) {
                if (!visited[w]) {
                    parent[w] = v;
                    str.append(graph.getVertexName(v) + " - " + graph.getVertexName(w) + " (tree edge) ");
                    explore(graph, w);
                } else {
                    str.append(graph.getVertexName(v) + " - " + graph.getVertexName(w) + " (back edge) ");
                }
            }
            post[v] = counter++;
        }

        public int[] getPre() {
            return pre;
        }

        public int[] getPost() {
            return post;
        }

        public int[] getParent() {
            return parent;
        }

        public String getStr() {
            return str.toString();
        }
    }

    public static void main(String[] args) {
        UndirectedGraph graph = new UndirectedGraph(9);
        graph.loadGraph("GraphA.txt");

        DFS dfs = new DFS(graph);
        int[] pre = dfs.getPre();
        int[] post = dfs.getPost();
        for (int i = 0; i < graph.getNumVertices(); i++) {
            System.out.println(graph.getVertexName(i) + " pre: " + pre[i] + " post: " + post[i]);
        }
    }
}