package by.it.group151001.beryozkin.lesson11;

public class TaskA {
    public int verchCount;
    public int[][] matrix;
    public String obhod = "";
    public int[] pred;
    public int[] post;
    public boolean[] visited;
    public int start = 0;
    int koef = 0;

    public static void main(String[] args) {
        int verchCount = 9;
        TaskA graph = new TaskA();
        graph.verchCount = verchCount;
        graph.matrix = new int[verchCount][verchCount];
        graph.addNode(0, 1);
        graph.addNode(0, 4);
        graph.addNode(1,2);
        graph.addNode(1,4);
        graph.addNode(2,5);
        graph.addNode(4, 5);
        graph.addNode(5, 8);
        graph.addNode(3,6);
        graph.addNode(3,7);
        graph.addNode(6,7);
        graph.visitNodes();
        System.out.println(graph.obhod);
    }

    // adding root to matrix
    void addNode(int x, int y)
    {
        matrix[x][y] = 1;
        matrix[y][x] = 1;
    }

    public void visitNodes()
    {
        visited = new boolean[verchCount];
        pred = new int[verchCount];
        post = new int[verchCount];
        for (int i = 0; i < verchCount; i++)
        {
            if (!visited[i])
            {
                search(i);
            }
        }
    }

    public void search(int vertex)
    {
        visited[vertex] = true;
        pred[vertex] = koef++;
        obhod = obhod + vertex + " ";
        for (int i = 0; i < verchCount; i++)
        {
            if (matrix[vertex][i] == 1 && !visited[i])
            {
                search(i);
            }
        }
        post[vertex] = koef++;
    }
}
