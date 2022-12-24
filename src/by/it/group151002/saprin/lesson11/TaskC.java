package by.it.group151002.saprin.lesson11;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskC {

    static class Graph {
        private final int vertices = 8;
        private final boolean[] visited = new boolean[vertices];

        private final int[] pre = new int[vertices];
        private final int[] post = new int[vertices];

        private int counter = 0;
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

        public void initDFS() {
            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    dfs(i);
                    System.out.println();
                }
            }

        }

        public void dfs(int vertex) {
            System.out.print((char) ((int) 'A' + vertex));
            visited[vertex] = true;
            pre[vertex] = counter++;
            for (int i = 0; i < vertices; i++) {
                if (matrix[vertex][i] == 1 && !visited[i]) {
                    dfs(i);
                }
            }
            post[vertex] = counter++;
        }

        public ArrayList<Character> findAllSinks(){
            ArrayList<Character> sinks = new ArrayList<>();
            for (int i = 0; i < vertices; i++){
                int j = 0;
                boolean isSink = true;
                while (j < vertices && isSink){
                    if (matrix[i][j] != 0){
                        isSink = false;
                    }
                    j++;
                }
                if (isSink){
                    sinks.add((char)((int) 'A' + i));
                }
            }
            return sinks;
        }

        public ArrayList<Character> findAllSources(){
            ArrayList<Character> sources = new ArrayList<>();
            for (int i = 0; i < vertices; i++){
                boolean isSource = true;
                int j = 0;
                while (j < vertices && isSource){
                    if (matrix[j][i] != 0){
                        isSource = false;
                    }
                    j++;
                }
                if (isSource){
                    sources.add((char)((int) 'A' + i));
                }
            }
            return sources;
        }
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.initDFS();
        System.out.print("Pre: ");
        System.out.println(Arrays.toString(graph.pre));
        System.out.print("Post: ");
        System.out.println(Arrays.toString(graph.post));
        System.out.print("Sinks: ");
        System.out.println(graph.findAllSinks().toString());
        System.out.print("Sources: ");
        System.out.println(graph.findAllSources().toString());
        System.out.println("Number of linearizations: " + graph.findAllSources().size() * 2 * 2);
    }
}
