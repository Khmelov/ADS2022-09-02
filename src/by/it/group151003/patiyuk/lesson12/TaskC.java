package by.it.group151003.patiyuk.lesson12;

public class TaskC {
    public static int MinCycleAlgorithm(Graph graph, String from, String to) {
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
            return -1;
        }
        int[][] distance = new int[graph.vertices.size()][graph.vertices.size()];
        for (int i = 0; i < graph.vertices.size(); i++) {
            for (int j = 0; j < graph.vertices.size(); j++) {
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < graph.vertices.size(); i++) {
            for (Graph.Edge edge : graph.vertices.get(i).edges) {
                int fromIndexInGraph = graph.vertices.indexOf(edge.from);
                int toIndexInGraph = graph.vertices.indexOf(edge.to);
                distance[fromIndexInGraph][toIndexInGraph] = edge.weight;
            }
        }
        for (int k = 0; k < graph.vertices.size(); k++) {
            for (int i = 0; i < graph.vertices.size(); i++) {
                for (int j = 0; j < graph.vertices.size(); j++) {
                    if (distance[i][k] != Integer.MAX_VALUE && distance[k][j] != Integer.MAX_VALUE && distance[i][k] + distance[k][j] < distance[i][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }
        int minCycle = Integer.MAX_VALUE;
        for (int i = 0; i < graph.vertices.size(); i++) {
            if (distance[i][i] < minCycle) {
                minCycle = distance[i][i];
            }
        }
        if (minCycle == Integer.MAX_VALUE) {
            return -1;
        }
        return minCycle;
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.loadGraph("GraphC.txt");

        System.out.println(MinCycleAlgorithm(graph, "B", "C"));
    }
}
