package by.it.group151002.saprin.lesson11;

import java.util.Arrays;

public class TaskB {
    static class Graph {
        private final int vertices = 8;

        private final boolean[] visited = new boolean[vertices];

        private final int[][] matrix = {
                {0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
        };

        public char[] topologicalSort() {

            char[] order = new char[vertices];
            int index = vertices - 1;

            for (int i = 0; i < vertices; i++){
                if (!visited[i]){
                    index = visit(order, index, i);
                }
            }

            return order;
        }

        private int visit(char[] order, int index, int v) {

            if (visited[v]){
                return index;
            }
            visited[v] = true;

            for (int i = 0; i < vertices; i++) {
                if (matrix[i][i] != 0 && !visited[i]) {
                    index = visit(order, index, i);
                }
            }

            order[index--] = (char) ((int) 'A' + v);

            return index;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        System.out.print("Sorted: ");
        System.out.println(Arrays.toString(graph.topologicalSort()));
    }
}
