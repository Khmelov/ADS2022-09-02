package by.it.group151001.novik.lesson11;

public class TaskA {
    public int l;
    public boolean[][] matrix;
    public String res = "";
    public int[] pre;
    public int[] post;
    public boolean[] visited;
    public int start = 0;
    int clock = 0;
    void addEdge(int src, int dest){
        matrix[src][dest] = true;
        matrix[dest][src] = true;
    }
    public void initDFS()
    {
        visited = new boolean[l];
        pre = new int[l];
        post = new int[l];
        for (int i = 0; i < l; i++)
        {
            if (!visited[i])
            {
                explore(i);
            }
            System.out.println("Pre " + i + " = "+ pre.toString());

            System.out.println("Post " + i + " = "+ post.toString());
        }

    }
    public void explore(int vertex)
    {
        visited[vertex] = true;
        pre[vertex] = clock++;
        res = res + vertex + " ";
        for (int i = 0; i < l; i++)
        {
            if (matrix[vertex][i] && !visited[i])
            {
                explore(i);
            }
        }

        post[vertex] = clock++;
    }
    public static void main(String[] args) {
        TaskA gr = new TaskA();
        gr.l = 9;
        gr.matrix = new boolean[gr.l][gr.l];
        gr.addEdge(0,1);
        gr.addEdge(0,4);
        gr.addEdge(1,2);
        gr.addEdge(1,4);
        gr.addEdge(2,5);
        gr.addEdge(4,5);
        gr.addEdge(6,7);
        gr.addEdge(3,7);
        gr.addEdge(3,6);
        gr.addEdge(5,8);
        gr.initDFS();
        System.out.println(gr.res);
    }
}
