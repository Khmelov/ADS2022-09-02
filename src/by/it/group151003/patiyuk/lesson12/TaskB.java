package by.it.group151003.patiyuk.lesson12;

import java.util.LinkedList;

public class TaskB {

    public static class BellmanFord {
        private Graph graph;
        private int[] distance;
        private int[] previous;

        public BellmanFord(Graph graph) {
            this.graph = graph;
            distance = new int[graph.vertices.size()];
            previous = new int[graph.vertices.size()];
        }

        public Graph.Path findShortestPath(String from, String to) {
            int fromIndex = -1;
            int toIndex = -1;
            for (int i = 0; i < graph.vertices.size(); i++) {
                if (graph.vertices.get(i).name.equals(from)) {
                    fromIndex = i;
                }
                if (graph.vertices.get(i).name.equals(to)) {
                    toIndex = i;
                }
            }
            if (fromIndex == -1 || toIndex == -1) {
                return null;
            }
            for (int i = 0; i < graph.vertices.size(); i++) {
                distance[i] = Integer.MAX_VALUE;
                previous[i] = -1;
            }
            distance[fromIndex] = 0;
            for (int i = 0; i < graph.vertices.size() - 1; i++) {
                for (Graph.Vertex vertex : graph.vertices) {
                    for (Graph.Edge edge : vertex.edges) {
                        int fromIndexInGraph = graph.vertices.indexOf(edge.from);
                        int toIndexInGraph = graph.vertices.indexOf(edge.to);
                        if (distance[fromIndexInGraph] != Integer.MAX_VALUE && distance[fromIndexInGraph] + edge.weight < distance[toIndexInGraph]) {
                            distance[toIndexInGraph] = distance[fromIndexInGraph] + edge.weight;
                            previous[toIndexInGraph] = fromIndexInGraph;
                        }
                    }
                }
            }
            for (Graph.Vertex vertex : graph.vertices) {
                for (Graph.Edge edge : vertex.edges) {
                    int fromIndexInGraph = graph.vertices.indexOf(edge.from);
                    int toIndexInGraph = graph.vertices.indexOf(edge.to);
                    if (distance[fromIndexInGraph] != Integer.MAX_VALUE && distance[fromIndexInGraph] + edge.weight < distance[toIndexInGraph]) {
                        return null;
                    }
                }
            }
            if (distance[toIndex] == Integer.MAX_VALUE) {
                return null;
            }
            LinkedList<String> path = new LinkedList<>();
            int currentIndex = toIndex;
            while (currentIndex != -1) {
                path.addFirst(graph.vertices.get(currentIndex).name);
                currentIndex = previous[currentIndex];
            }
            return new Graph.Path(distance[toIndex], path.toArray(String[]::new));
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.loadGraph("GraphB.txt");

        BellmanFord bellmanFord = new BellmanFord(graph);
        Graph.Path path = bellmanFord.findShortestPath("A", "E");
        System.out.println(path);
    }

}
