package by.it.group151003.radionov.lesson11;

public class TaskA {
    public int v;

    public int[][] a;
    public String r = "";
    public int[] pre;
    public int[] post;
    public boolean[] vis;
    public int start = 0;
    int clock = 0;

    void addEdge(int x, int y)
    {
        a[x][y] = 1;
        a[y][x] = 1;
    }

    public void initDFS()
    {
        vis = new boolean[v];
        pre = new int[v];
        post = new int[v];
        for (int i = 0; i < v; i++)
        {
            if (!vis[i])
            {
                explore(i);
            }
        }

    }

    public void explore(int vertex)
    {
        vis[vertex] = true;
        pre[vertex] = clock++;
        r = r + vertex + " ";
        for (int i = 0; i < v; i++)
        {
            if (a[vertex][i] == 1 && !vis[i])
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
        graph.a = new int[v][v];
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
        System.out.println(graph.r);
    }
}
