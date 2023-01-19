package by.it.group151002.saprin.lesson12;


public class TaskA {
    static class Graph {
        private final int vertices = 8;
        private final int[][] matrix = {
                {0, 1, 0, 0, 4, 8, 0, 0},
                {6, 0, 2, 0, 0, 0, 6, 0},
                {0, 0, 0, 1, 0, 0, 2, 0},
                {0, 0, 0, 0, 0, 0, 1, 4},
                {0, 0, 0, 0, 0, 5, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
        };

        private int getMinimumVertex(boolean[] mst, int[] key) {
            int minKey = Integer.MAX_VALUE;
            int vertex = -1;
            for (int i = 0; i < vertices; i++) {
                if (!mst[i] && minKey > key[i]) {
                    minKey = key[i];
                    vertex = i;
                }
            }

            return vertex;
        }

        public void dijkstra_GetMinDistances(int sourceVertex) {
            boolean[] spt = new boolean[vertices];
            int[] distance = new int[vertices];
            int INFINITY = Integer.MAX_VALUE;

            for (int i = 0; i < vertices; i++) {
                distance[i] = INFINITY;
            }

            distance[sourceVertex] = 0;

            for (int i = 0; i < vertices; i++) {

                int vertex_1 = getMinimumVertex(spt, distance);

                spt[vertex_1] = true;

                for (int vertex_2 = 0; vertex_2 < vertices; vertex_2++) {
                    if (matrix[vertex_1][vertex_2] > 0) {

                        if (!spt[vertex_2] && matrix[vertex_1][vertex_2] != INFINITY) {

                            int newKey = matrix[vertex_1][vertex_2] + distance[vertex_1];
                            if (newKey < distance[vertex_2])
                                distance[vertex_2] = newKey;
                        }
                    }
                }
            }
            printDijkstra(sourceVertex, distance);
        }

        public void printDijkstra(int sourceVertex, int[] key) {
            System.out.println("Dijkstra Algorithm: (Adjacency Matrix)");
            for (int i = 0; i < vertices; i++) {
                System.out.println("Source Vertex: " + (char) ((int) 'A' + sourceVertex) + " to vertex " + (char) ((int) 'A' + i) +
                        " distance: " + key[i]);
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.dijkstra_GetMinDistances(0);
    }

}
