package by.it.group151003.patiyuk.lesson12;

public class TaskA {

    public static class Dijkstra {
        private Graph graph;
        private int[] distance;
        private boolean[] visited;
        private int[] previous;

        public Dijkstra(Graph graph) {
            this.graph = graph;
            distance = new int[graph.vertices.size()];
            visited = new boolean[graph.vertices.size()];
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
                visited[i] = false;
                previous[i] = -1;
            }
            distance[fromIndex] = 0;
            for (int i = 0; i < graph.vertices.size(); i++) {
                int minDistance = Integer.MAX_VALUE;
                int minIndex = -1;
                for (int j = 0; j < graph.vertices.size(); j++) {
                    if (!visited[j] && distance[j] < minDistance) {
                        minDistance = distance[j];
                        minIndex = j;
                    }
                }
                if (minIndex == -1) {
                    break;
                }
                visited[minIndex] = true;
                for (Graph.Edge edge : graph.vertices.get(minIndex).edges) {
                    int toIndexInGraph = graph.vertices.indexOf(edge.to);
                    if (!visited[toIndexInGraph] && distance[minIndex] != Integer.MAX_VALUE && distance[minIndex] + edge.weight < distance[toIndexInGraph]) {
                        distance[toIndexInGraph] = distance[minIndex] + edge.weight;
                        previous[toIndexInGraph] = minIndex;
                    }
                }
            }
            if (distance[toIndex] == Integer.MAX_VALUE) {
                return null;
            }
            String[] vertices = new String[graph.vertices.size()];
            int count = 0;
            int index = toIndex;
            while (index != -1) {
                vertices[count++] = graph.vertices.get(index).name;
                index = previous[index];
            }
            String[] result = new String[count];
            for (int i = 0; i < count; i++) {
                result[i] = vertices[count - i - 1];
            }
            return new Graph.Path(distance[toIndex], result);
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.loadGraph("GraphA.txt");
        Dijkstra dijkstra = new Dijkstra(graph);
        Graph.Path path = dijkstra.findShortestPath("A", "F");
        System.out.println(path.weight);
        System.out.println(path.toString());
    }

}
