package by.it.group151001.milkovskaya.lesson11;

import java.util.Arrays;

public class TaskB {
    int[] topol;
    int counter = 0;
    public boolean[] visited;
    public int size;

    public int[][] matr;
    public int[] pre;
    public int[] post;
    public int start = 0;
    int clock = 0;

    void addEdge(int x, int y)
    {
        matr[x][y] = 1;
        matr[y][x] = 0;
    }

    public void sort()
    {
        visited = new boolean[size];
        pre = new int[size];
        post = new int[size];
        topol = new int[size];
        for (int i = 0; i < size; i++)
        {
            if (!visited[i])
            {
                explore(i);
            }
        }

        topol = reverse(topol);
    }

    public int[] reverse(int[] arr)
    {
        int[] res = new int[size];
        for (int i = size - 1; i > -1; i--)
        {
            res[size - i - 1] = arr[i];
        }

        return res;
    }

    public void explore(int v)
    {
        visited[v] = true;
        pre[v] = clock++;
        for (int i = 0; i < v; i++)
        {
            if (matr[v][i] == 1 && !visited[i])
            {
                explore(i);
            }
        }

        post[v] = clock++;
        topol[counter++] = v;
    }

    public static void main(String[] args)
    {
        TaskB graph = new TaskB();
        graph.size = 8;
        graph.matr = new int[graph.size][graph.size];
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.sort();
        System.out.println(Arrays.toString(graph.topol));
    }
}
