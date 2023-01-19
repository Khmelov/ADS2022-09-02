package by.it.group151002.ravodin.lesson11;

public class GraphA {

    public static int[] before;
    public static int time;
    public static int[] after;
    public static String res;
    public static boolean[] isVisited;


    public static void search(Graph graph, int vert) {
        before[vert] = time++;
        isVisited[vert] = true;
        for (int i : graph.getNeighbors(vert)) {
            if (!isVisited[i]) {
                res += graph.getName(vert) + " - " + graph.getName(i) + " (tree) ";
                search(graph, i);
            } else if (before[vert] > before[i] && after[vert] <= after[i])
                res += graph.getName(vert) + " - " + graph.getName(i) + " (back) ";
        }
        after[vert] = time++;
    }

    public static void dfs(Graph graph, int vertex) {
        after = new int[graph.getVertexNumber()];
        res = new String();
        isVisited = new boolean[graph.getVertexNumber()];
        time = 0;
        before = new int[graph.getVertexNumber()];
        for (int i = 0; i < graph.getVertexNumber(); i++)
            if (!isVisited[i]) {
                search(graph, i);
                res += "\n";
            }
    }

    public static void main(String[] args) {
        Graph g = new Graph(9);
        g.setName(0, 'A');
        g.setName(1, 'B');
        g.setName(2, 'C');
        g.setName(3, 'D');
        g.setName(4, 'E');
        g.setName(5, 'F');
        g.setName(6, 'G');
        g.setName(7, 'H');
        g.setName(8, 'I');
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
        for (int i = 0; i < g.getVertexNumber(); i++)
            System.out.println(g.getName(i) + ": pre = " + before[i] + "; post = " + after[i]);
    }
}