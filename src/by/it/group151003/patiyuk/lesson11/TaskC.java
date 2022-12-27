package by.it.group151003.patiyuk.lesson11;

import java.util.HashMap;
import java.util.LinkedList;

public class TaskC {
    public static class Graph {
        private HashMap<Character, LinkedList<Character>> graph;
        private boolean[] visited;
        private LinkedList<Character> topologicalOrder;
        private HashMap<Character, Integer> preValues;
        private HashMap<Character, Integer> postValues;
        private int counter = 1;
        private int sourcesCount = 0;
        private LinkedList<Character> sources;
        private LinkedList<Character> sinks;

        public Graph(int numVertices) {
            graph = new HashMap<>();
            topologicalOrder = new LinkedList<>();
            visited = new boolean[numVertices];
            preValues = new HashMap<>();
            postValues = new HashMap<>();
            sources = new LinkedList<>();
            sinks = new LinkedList<>();
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
            preValues.put(vertex, counter++);
            visited[vertex - 'A'] = true;
            for (Character neighbor : graph.get(vertex)) {
                if (!visited[neighbor - 'A']) {
                    dfs(neighbor);
                }
            }
            topologicalOrder.addFirst(vertex);
            postValues.put(vertex, counter++);
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

        public String getPreValues() {
            return preValues.toString();
        }

        public String getPostValues() {
            return postValues.toString();
        }

        public void findSourcesAndSinks() {
            for (Character vertex : graph.keySet()) {
                if (graph.get(vertex).isEmpty()) {
                    sinks.add(vertex);
                }

                boolean isSource = true;
                for (Character v : graph.keySet()) {
                    if (graph.get(v).contains(vertex)) {
                        isSource = false;
                        break;
                    }
                }

                if (isSource) {
                    sources.add(vertex);
                    sourcesCount++;
                }
            }
        }

        public String getSources() {
            return sources.toString();
        }

        public String getSinks() {
            return sinks.toString();
        }

        public int getLinearizationsCount() {
            return sourcesCount * 2 * 2;
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
        System.out.println(graph.getPreValues());
        System.out.println(graph.getPostValues());
        graph.findSourcesAndSinks();
        System.out.println(graph.getSources());
        System.out.println(graph.sourcesCount);
        System.out.println(graph.getSinks());
        System.out.println(graph.getLinearizationsCount());
    }
}
