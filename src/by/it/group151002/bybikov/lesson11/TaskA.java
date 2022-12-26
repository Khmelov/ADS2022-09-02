package by.it.group151002.bybikov.lesson11;

public class TaskA {
    public static boolean[] visited;
    public static int[] pre;
    public static int[] post;
    public static String res;
    public static int clock;

    public static void explore(Graph graph, int vertex)
    {
        visited[vertex] = true;
        pre[vertex] = clock++;
        for (int i : graph.getNeighbors(vertex)) {
            if (visited[i] ==false) {
                res += graph.getName(vertex) + " - " + graph.getName(i) + " (tree) ";
                explore(graph, i);
            } else if (pre[vertex] > pre[i] && post[vertex] <= post[i])
                res += graph.getName(vertex) + " - " + graph.getName(i) + " (back) ";

        }
        post[vertex] = clock++;
    }

    public static void  DFS(Graph graph,int start)
    {
        visited = new boolean[graph.getVertexCount()];
        pre = new int[graph.getVertexCount()];
        res = "";
        post = new int[graph.getVertexCount()];
        clock = 0;
        for (int i = start; i < graph.getVertexCount(); i++)
            if (visited[i] ==false) {
                explore(graph, i);
                res += "\n";
            }
    }
    public static void main(String[] args) {
        Graph graph = new Graph(9);
        graph.setName(0, 'A');
        graph.setName(1, 'B');
        graph.setName(2, 'C');
        graph.setName(3, 'D');
        graph.setName(4, 'E');
        graph.setName(5, 'F');
        graph.setName(6, 'G');
        graph.setName(7, 'H');
        graph.setName(8, 'I');
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 4);
        graph.addEdge(1, 2);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(5, 8);
        graph.addEdge(3, 6);
        graph.addEdge(7, 6);
        graph.addEdge(3, 7);
        DFS(graph, 0);
        System.out.println(res);
        for (int i = 0; i < graph.getVertexCount(); i++)
            System.out.println(graph.getName(i) + ": pre = " + pre[i] + ", post = " + post[i]);
    }
}

