package by.it.group151002.saprin.lesson11;


import java.util.Arrays;

public class TaskA {

    static class Graph {
        private final int vertices = 9;

        private final boolean[] visited = new boolean[vertices];

        private final int[] pre = new int[vertices];
        private final int[] post = new int[vertices];

       private int counter = 0;
        private final int[][] matrix = {
                {0, 1, 0, 0, 1, 0, 0, 0, 0},
                {1, 0, 1, 0, 1, 0, 0, 0, 0},
                {0, 1, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 0},
                {1, 1, 0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 0, 1, 0, 0, 0, 1},
                {0, 0, 0, 1, 0, 0, 0, 1, 0},
                {0, 0, 0, 1, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0, 0},
        };

        public void initDFS() {
            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    dfs(i);
                    System.out.println();
                }
            }

        }

        public void dfs(int vertex) {
            System.out.print((char)((int) 'A' + vertex));
            visited[vertex] = true;
            pre[vertex] = counter++;
            for (int i = 0; i < vertices; i++) {
                if (matrix[vertex][i] == 1 && !visited[i]) {
                    dfs(i);
                }
            }

            post[vertex] = counter++;
        }

        public void showPreArr(){
            System.out.println(Arrays.toString(pre));
        }

        public void showPostArr(){
            System.out.println(Arrays.toString(post));
        }

    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        System.out.println("DFS:");
        graph.initDFS();
        System.out.println("Pre:");
        graph.showPreArr();
        System.out.println("Post:");
        graph.showPostArr();
    }
}
