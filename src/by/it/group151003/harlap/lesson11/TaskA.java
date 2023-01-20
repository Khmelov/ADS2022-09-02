package by.it.group151003.harlap.lesson11;

public class TaskA {
    public int vetchCount;
    public int[][] matrix;
    public String obhod = "";
    public int[] prev;
    public int[] post;
    public boolean[] visited;
    public int start = 0;
    int clock = 0;

    public static void main(String[] args) {
        int verchCount = 9;
        TaskA graph = new TaskA();
        graph.vetchCount = verchCount;
        graph.matrix = new int[verchCount][verchCount];
        graph.addNode(0, 1);
        graph.addNode(0, 5);
        graph.addNode(1,3);
        graph.addNode(1,4);
        graph.addNode(2,5);
        graph.addNode(3, 5);
        graph.addNode(4, 8);
        graph.addNode(4,6);
        graph.addNode(5,7);
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
        visited = new boolean[vetchCount];
        prev = new int[vetchCount];
        post = new int[vetchCount];
        for (int i = 0; i < vetchCount; i++)
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
        prev[vertex] = clock++;
        obhod = obhod + vertex + " ";
        for (int i = 0; i < vetchCount; i++)
        {
            if (matrix[vertex][i] == 1 && !visited[i])
            {
                search(i);
            }
        }
        post[vertex] = clock++;
    }
}
