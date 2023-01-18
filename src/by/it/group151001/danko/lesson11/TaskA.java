package by.it.group151001.danko.lesson11;

public class TaskA {

    public int count;
    public int[][] matrix;

    public String checks = "";
    public int[] pred;
    public int[] post;
    public boolean[] checked;
    public int root = 0;
    int n = 0;

    public static void main(String[] args)
    {
        int count = 9;
        TaskA graph = new TaskA();
        graph.count = count;
        graph.matrix = new int[count][count];
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
        System.out.println(graph.checks);
    }

    void addNode(int x, int y)
    {
        matrix[x][y] = 1;
        matrix[y][x] = 1;
    }

    public void visitNodes()
    {
        checked = new boolean[count];
        pred = new int[count];
        post = new int[count];
        for (int i = 0; i < count; i++)
        {
            if (!checked[i])
            {
                search(i);
            }
        }
    }

    public void search(int vertex)
    {
        checked[vertex] = true;
        pred[vertex] = n++;
        checks = checks + vertex + " ";
        for (int i = 0; i < count; i++)
        {
            if (matrix[vertex][i] == 1 && !checked[i])
            {
                search(i);
            }
        }
        post[vertex] = n++;
    }
}
