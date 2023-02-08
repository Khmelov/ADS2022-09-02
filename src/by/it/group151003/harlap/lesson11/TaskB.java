package by.it.group151003.harlap.lesson11;
import java.util.Arrays;

public class TaskB {
    public boolean[] visited;
    public int depth;
    public int[] topology;
    public int counter = 0;
    public int[][] matrix;
    public int[] prev;
    public int[] post;
    public int start = 0;
    int clock = 0;

    public static void main(String[] args)
    {
        TaskB graph = new TaskB();
        int v = 8;
        graph.depth = v;
        graph.matrix = new int[v][v];
        graph.addNode(0, 2);
        graph.addNode(1, 2);
        graph.addNode(2, 3);
        graph.addNode(2, 4);
        graph.addNode(3, 5);
        graph.addNode(4, 5);
        graph.addNode(5, 6);
        graph.addNode(5, 7);
        graph.sortVerch();
        System.out.println(Arrays.toString(graph.topology));
    }

    void addNode(int x, int y)
    {
        matrix[x][y] = 1;
        matrix[y][x] = 0;
    }

    public void sortVerch()
    {
        visited = new boolean[depth];
        prev = new int[depth];
        post = new int[depth];
        topology = new int[depth];
        for (int i = 0; i < depth; i++)
        {
            if (!visited[i])
            {
                search(i);
            }
        }

        topology = reverse(topology);
    }

    public int[] reverse(int[] arr)
    {
        int[] res = new int[depth];
        for (int i = depth - 1; i > -1; i--)
        {
            res[depth - i - 1] = arr[i];
        }

        return res;
    }

    public void search(int vertex)
    {
        visited[vertex] = true;
        prev[vertex] = clock++;
        for (int i = 0; i < depth; i++)
        {
            if (matrix[vertex][i] == 1 && !visited[i])
            {
                search(i);
            }
        }

        post[vertex] = clock++;
        topology[counter++] = vertex;
    }
}
