package by.it.group151001.yankova.lesson12;
import java.util.ArrayList;

public class TaskB {
    public static final int NUMBER_OF_VERTICES = 10;
    private static final char START_VERTEX = 'A';
    public int MAX = 100;
    public int[] dist = new int[NUMBER_OF_VERTICES];
    public static ArrayList<Node> edges = new ArrayList<>();
    public static class Node {
        public int v;
        public int u;
        public int cost;
        Node (int v, int u, int cost) {
            this.v = v;
            this.u = u;
            this.cost = cost;
        }
    }

    public void bellmanFord(int start) {
        for (int i = 0; i < NUMBER_OF_VERTICES; i++) {
            dist[i] = MAX;
        }
        dist[start] = 0;
        for (int i = 0; i < NUMBER_OF_VERTICES - 1; ++i) {
            for (int j = 0; j < edges.size(); ++j) {
                int v = edges.get(j).v,
                        u = edges.get(j).u,
                        cost = edges.get(j).cost;
                if (dist[u] > dist[v] + cost) {
                    dist[u] = dist[v] + cost;
                }
            }
        }
    }

    public static void main(String[] args) {
        TaskB taskB = new TaskB();

        edges.add(new Node(0,2, -2));
        edges.add(new Node(0,1, 4));
        edges.add(new Node(1,6, -2));
        edges.add(new Node(1,7, -4));
        edges.add(new Node(2,3, 2));
        edges.add(new Node(2,5, 1));
        edges.add(new Node(4,5, -2));
        edges.add(new Node(4,7, 3));
        edges.add(new Node(5,3, 3));
        edges.add(new Node(6,8, -1));
        edges.add(new Node(7,6, 1));
        edges.add(new Node(8,7, 1));
        edges.add(new Node(9,0, 7));
        edges.add(new Node(9,2, 6));
        edges.add(new Node(9,5, 5));
        edges.add(new Node(9,4, 6));


        taskB.bellmanFord(0);
        for (int i = 0; i < NUMBER_OF_VERTICES; i++) {
            char nextVertex = (char)(START_VERTEX + i);
            if (nextVertex == 'J')
                nextVertex = 'S';
            if (taskB.dist[i] != taskB.MAX)
                System.out.print("The shortest path from " + START_VERTEX + " to " + nextVertex + " = " +  taskB.dist[i]);
            else
                System.out.print("There's no path from " + START_VERTEX + " to " + nextVertex);
            System.out.print("\n");
        }
    }
}
