package by.it.group151004.karvatskaya.lesson11;

public class TaskA {
    public static final int number = 9;
    public static boolean[] checked;
    public static int[] pred;
    public static int[] after;
    public static String tmp;
    public static int clock;

    public static void dfs(Start graph, int first) {
        tmp = "";
        checked = new boolean[graph.getVertCount()];
        pred = new int[graph.getVertCount()];
        after = new int[graph.getVertCount()];
        clock = 0;
        for (int i = first; i < graph.getVertCount(); i++)
            if (!checked[i]) {
                search(graph, i);
                tmp += "\n";
            }
    }

    public static void search(Start graph, int vert) {
        checked[vert] = true;
        pred[vert] = clock++;
        for (int i : graph.getNear(vert)) {
            if (!checked[i]) {
                tmp += graph.getName(vert) + " - " + graph.getName(i) + " (tree) ";
                search(graph, i);
            } else if (pred[vert] > pred[i] && after[vert] <= after[i])
                tmp += graph.getName(vert) + " - " + graph.getName(i) + " (back) ";

        }
        after[vert] = clock++;
    }

    public static void main(String[] args) {
        Start graph = new Start(number);
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
        dfs(graph, 0);
        System.out.println(tmp);
        for (int i = 0; i < graph.getVertCount(); i++)
            System.out.println(graph.getName(i) + ": pre = " + pred[i] + ", post = " + after[i]);
    }
}

