package by.it.group151002.krumkachev.lesson11;

public class GraphA {
    public static boolean[] visited;
    public static int[] pre;
    public static int[] post;
    public static String res;
    public static int clock;

    public static void dfs(Graph g, int vertex) {
        res = "";
        visited = new boolean[g.getNumberOfVertex()];
        pre = new int[g.getNumberOfVertex()];
        post = new int[g.getNumberOfVertex()];
        clock = 0;
        for (int i = 0; i < g.getNumberOfVertex(); i++)
            if (!visited[i]) {
                search(g, i);
                res = res + "\n";
            }
    }

    public static void search(Graph g, int vertex) {
        visited[vertex] = true;
        pre[vertex] = clock++;
        for (int i : g.getNeighbors(vertex)) {
            if (!visited[i]) {
                res = res + g.getChar(vertex) + " - " + g.getChar(i) + " (tree) ";
                search(g, i);
            } else if (pre[vertex] > pre[i] && post[vertex] <= post[i])
                res = res + g.getChar(vertex) + " - " + g.getChar(i) + " (back) ";

        }
        post[vertex] = clock++;
    }

    public static void main(String[] args) {
        Graph g = new Graph(9);
        g.setChar(0, 'A');
        g.setChar(1, 'B');
        g.setChar(2, 'C');
        g.setChar(3, 'D');
        g.setChar(4, 'E');
        g.setChar(5, 'F');
        g.setChar(6, 'G');
        g.setChar(7, 'H');
        g.setChar(8, 'I');
        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 4);
        g.addEdge(1, 2);
        g.addEdge(2, 5);
        g.addEdge(4, 5);
        g.addEdge(5, 8);
        g.addEdge(3, 6);
        g.addEdge(7, 6);
        g.addEdge(3, 7);
        dfs(g, 0);
        System.out.println(res);
        for (int i = 0; i < g.getNumberOfVertex(); i++)
            System.out.println(g.getChar(i) + ": pre = " + pre[i] + "; post = " + post[i]);
    }
}
