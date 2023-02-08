package by.it.group151003.patiyuk.lesson11;

import java.util.HashMap;
import java.util.LinkedList;

public class TaskB {
    public static class Graph {
        private HashMap<Character, LinkedList<Character>> graph;
        private boolean[] visited;
        private LinkedList<Character> topologicalOrder;

        public Graph(int numVertices) {
            graph = new HashMap<>();
            topologicalOrder = new LinkedList<>();
            visited = new boolean[numVertices];
        }

        public void addEdge(Character v1, Character v2) {
            if (!graph.containsKey(v1)) {
                graph.put(v1, new LinkedList<>());
            }
            if (!graph.containsKey(v2)) {
                graph.put(v2, new LinkedList<>());
            }
            graph.get(v1).add(v2);
        }

        public void dfs(Character vertex) {
            visited[vertex - 'A'] = true;
            for (Character neighbor : graph.get(vertex)) {
                if (!visited[neighbor - 'A']) {
                    dfs(neighbor);
                }
            }
            topologicalOrder.addFirst(vertex);
        }

        public void sort() {
            for (Character vertex : graph.keySet()) {
                if (!visited[vertex - 'A']) {
                    dfs(vertex);
                }
            }
        }

        public String getOrder() {
            return topologicalOrder.toString();
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph(8);
        graph.addEdge('A', 'C');
        graph.addEdge('B', 'C');
        graph.addEdge('C', 'D');
        graph.addEdge('C', 'E');
        graph.addEdge('D', 'F');
        graph.addEdge('E', 'F');
        graph.addEdge('F', 'G');
        graph.addEdge('F', 'H');
        graph.sort();
        System.out.println(graph.getOrder());
    }
}
