package by.it.group151003.halai.lesson11;

import java.util.Arrays;

public class TaskB {
    public boolean[] visited;
    public int depth;
    public int[] topology;
    public int counter = 0;
    public int[][] matrix;
    public int[] prev;
    public int[] next;
    public int start = 0;
    int iterator = 0;

    public int[] reverse(int[] arr) {
        int[] reverseArr = new int[depth];
        for (int i = depth - 1; i > -1; i--)
            reverseArr[depth - i - 1] = arr[i];

        return reverseArr;
    }

    public void search(int vertex) {
        visited[vertex] = true;
        prev[vertex] = iterator++;
        for (int i = 0; i < depth; i++)
            if (matrix[vertex][i] == 1 && !visited[i])
                search(i);

        next[vertex] = iterator++;
        topology[counter++] = vertex;
    }

    void addNode(int x, int y) {
        matrix[x][y] = 1;
        matrix[y][x] = 0;
    }

    public void sort() {
        visited = new boolean[depth];
        prev = new int[depth];
        next = new int[depth];
        topology = new int[depth];
        for (int i = 0; i < depth; i++)
            if (!visited[i])
                search(i);
        topology = reverse(topology);
    }

    public static void main(String[] args) {
        TaskB graph = new TaskB();
        int depth = 8;
        graph.depth = depth;
        graph.matrix = new int[depth][depth];
        graph.addNode(0, 2);
        graph.addNode(1, 2);
        graph.addNode(2, 3);
        graph.addNode(2, 4);
        graph.addNode(3, 5);
        graph.addNode(4, 5);
        graph.addNode(5, 6);
        graph.addNode(5, 7);
        graph.sort();
        System.out.println(Arrays.toString(graph.topology));
    }
}
