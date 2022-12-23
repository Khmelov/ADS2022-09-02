package by.it.group151001.kononova.lesson11;

public class TaskA {
    public int[][] matrix;
    public int depth;
    public int[] prev;
    public int[] next;
    public boolean[] visited;
    public String graphInString = "";
    public int start = 0;
    int clock = 0;

    public void setNodes() {
        visited = new boolean[depth];
        prev = new int[depth];
        next = new int[depth];
        for (int i = 0; i < depth; i++)
            if (!visited[i])
                obhod(i);
    }

    public void obhod(int vertex) {
        visited[vertex] = true;
        prev[vertex] = clock++;
        graphInString = graphInString + vertex + " ";
        for (int i = 0; i < depth; i++)
            if (matrix[vertex][i] == 1 && !visited[i])
                obhod(i);
        next[vertex] = clock++;
    }

    void addNode(int x, int y) {
        matrix[x][y] = 1;
        matrix[y][x] = 1;
    }

    public static void main(String[] args) {
        int depth = 9;
        TaskA graph = new TaskA();
        graph.depth = depth;
        graph.matrix = new int[depth][depth];
        graph.addNode(0, 1);
        graph.addNode(0, 4);
        graph.addNode(1,2);
        graph.addNode(1,4);;
        graph.addNode(2,5);
        graph.addNode(4, 5);
        graph.addNode(5, 8);
        graph.addNode(3,6);
        graph.addNode(3,7);
        graph.addNode(6,7);

        graph.setNodes();
        System.out.println(graph.graphInString);
    }
}
