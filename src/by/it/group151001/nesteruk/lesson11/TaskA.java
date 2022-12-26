package by.it.group151001.nesteruk.lesson11;

public class TaskA {
    public int verchCount;
    public int[][] matrix;
    public String graphInString = "";
    public int[] prev;
    public int[] post;
    int iterator = 0;
    public boolean[] visited;
    public int start = 0;

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
        System.out.println(graph.graphInString);
    }

    // adding root to matrix
    void addNode(int x, int y)
    {
        matrix[x][y] = 1;
        matrix[y][x] = 1;
    }

    public void dfs(int vertex)
    {
        visited[vertex] = true;
        prev[vertex] = iterator++;
        graphInString = graphInString + vertex + " ";
        for (int i = 0; i < verchCount; i++)
        {
            if (matrix[vertex][i] == 1 && !visited[i])
            {
                dfs(i);
            }
        }
        post[vertex] = iterator++;
    }

    public void visitNodes()
    {
        visited = new boolean[verchCount];
        prev = new int[verchCount];
        post = new int[verchCount];
        for (int i = 0; i < verchCount; i++)
        {
            if (!visited[i])
            {
                dfs(i);
            }
        }
    }
}
