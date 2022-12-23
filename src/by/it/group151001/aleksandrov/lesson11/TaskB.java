package by.it.group151001.aleksandrov.lesson11;

import java.util.Arrays;

public class TaskB {
    int[] t;
    int c = 0;
    public boolean[] vis;
    public int v;

    public int[][] a;
    public int[] pre;
    public int[] post;
    public int start = 0;
    int clock = 0;

    void addEdge(int x, int y)
    {
        a[x][y] = 1;
        a[y][x] = 0;
    }

    public void sort()
    {
        vis = new boolean[v];
        pre = new int[v];
        post = new int[v];
        t = new int[v];
        for (int i = 0; i < v; i++)
        {
            if (!vis[i])
            {
                explore(i);
            }
        }

        t = reverse(t);
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
        vis[vertex] = true;
        pre[vertex] = clock++;
        for (int i = 0; i < v; i++)
        {
            if (a[vertex][i] == 1 && !vis[i])
            {
                explore(i);
            }
        }

        post[vertex] = clock++;
        t[c++] = vertex;
    }

    public static void main(String[] args)
    {
        TaskB graph = new TaskB();
        int v = 8;
        graph.v = v;
        graph.a = new int[v][v];
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(5, 7);
        graph.sort();
        System.out.println(Arrays.toString(graph.t));
    }
}
