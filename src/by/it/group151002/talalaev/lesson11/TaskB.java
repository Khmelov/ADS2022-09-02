package by.it.group151002.talalaev.lesson11;

import java.util.Arrays;

public class TaskB {
    int[] tsort;
    int counter = 0;
    public boolean[] visited;
    public int v;

    public int[][] arr;
    public int[] pre;
    public int[] post;
    public int start = 0;
    int clock = 0;

    void addEdge(int x, int y)
    {
        arr[x][y] = 1;
        arr[y][x] = 0;
    }

    public void sort()
    {
        visited = new boolean[v];
        pre = new int[v];
        post = new int[v];
        tsort = new int[v];
        for (int i = 0; i < v; i++)
        {
            if (!visited[i])
            {
                explore(i);
            }
        }

        tsort = reverse(tsort);
    }

    public int[] reverse(int[] arr)
    {
        int[] res = new int[v];
        for (int i = v - 1; i > -1; i--)
        {
            res[v - i - 1] = arr[i];
        }

        return res;
    }

    public void explore(int vertex)
    {
        visited[vertex] = true;
        pre[vertex] = clock++;
        for (int i = 0; i < v; i++)
        {
            if (arr[vertex][i] == 1 && !visited[i])
            {
                explore(i);
            }
        }

        post[vertex] = clock++;
        tsort[counter++] = vertex;
    }

    public static void main(String[] args)
    {
        TaskB graph = new TaskB();
        int v = 8;
        graph.v = v;
        graph.arr = new int[v][v];
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.sort();
        System.out.println(Arrays.toString(graph.tsort));
    }
}


