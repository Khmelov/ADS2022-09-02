package by.it.group151001.loziuk.lesson11;

public class TaskA {
    public int size;
    public boolean[][] matrix;
    public String res = "";
    public int[] pre;
    public int[] post;
    public boolean[] visited;
    int clock = 0;
    void addNode(int src, int dest){
        matrix[src][dest] = true;
        matrix[dest][src] = true;
    }
    public void initDFS()
    {
        visited = new boolean[size];
        pre = new int[size];
        post = new int[size];
        for (int i = 0; i < size; i++)
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
        for (int i = 0; i < size; i++)
        {
            if (matrix[vertex][i] && !visited[i])
            {
                explore(i);
            }
        }

        post[vertex] = clock++;
    }
    public static void main(String[] args) {
        TaskA graph = new TaskA();
        graph.size = 9;
        graph.matrix = new boolean[graph.size][graph.size];
        graph.addNode(0,1);
        graph.addNode(0,4);
        graph.addNode(1,2);
        graph.addNode(1,4);
        graph.addNode(2,5);
        graph.addNode(4,5);
        graph.addNode(6,7);
        graph.addNode(3,7);
        graph.addNode(3,6);
        graph.addNode(5,8);
        graph.initDFS();
        System.out.println(graph.res);
    }
}
