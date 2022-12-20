package by.it.group151002.bobrik.lesson11;

import java.util.*;
import java.io.*;

public class TaskA {

    public int v; //amount of vertices

    // adjacency matrix
    public int[][] adj; //матрица смежности
    public String res = ""; //результат обхода в строке
    public int[] pre;
    public int[] post;
    public boolean[] visited;
    public int start = 0;
    int clock = 0;

    // function to add edge to the graph
    void addEdge(int x, int y)
    {
        adj[x][y] = 1;
        adj[y][x] = 1;
    }

    public void initDFS()
    {
        visited = new boolean[v];
        pre = new int[v];
        post = new int[v];
        for (int i = 0; i < v; i++)
        {
            if (!visited[i])
            {
                explore(i);
            }
        }

    }

    public void explore(int vertex)
    {
        visited[vertex] = true;
        pre[vertex] = clock++;
        res = res + vertex + " ";
        for (int i = 0; i < v; i++)
        {
            if (adj[vertex][i] == 1 && !visited[i])
            {
                explore(i);
            }
        }

        post[vertex] = clock++;
    }

    public static void main(String[] args) {
        int v = 9;
        TaskA graph = new TaskA();
        graph.v = v;
        graph.adj = new int[v][v];
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1,2);
        graph.addEdge(1,4);;
        graph.addEdge(2,5);
        graph.addEdge(4, 5);
        graph.addEdge(5, 8);
        graph.addEdge(3,6);
        graph.addEdge(3,7);
        graph.addEdge(6,7);

        graph.initDFS();
        System.out.println(graph.res);
    }
}
