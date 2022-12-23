package by.it.group151003.matoshko.lesson12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TaskC {
    public static List<List<Edge>> graph = new ArrayList<>();
    public static int[] destination;
    public static boolean[] visited;
    public static final int node = 2;
    public static final int u = 1;

    public static class Edge {
        public int dest;
        public int cost;

        Edge (int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    public void main(String[] args) {
        for (int i = 0; i < 8; i++)
            graph.add(new ArrayList<>());

        graph.get(0).add(new Edge(1, 1));
        graph.get(0).add(new Edge(4, 4));
        graph.get(0).add(new Edge(5, 8));

        graph.get(1).add(new Edge(5, 6));
        graph.get(1).add(new Edge(2, 2));

        graph.get(2).add(new Edge(6, 2));
        graph.get(2).add(new Edge(3, 1));

        graph.get(3).add(new Edge(6, 1));
        graph.get(3).add(new Edge(7, 4));

        graph.get(4).add(new Edge(5, 5));

        graph.get(6).add(new Edge(1, 6));
        graph.get(6).add(new Edge(5, 1));
        graph.get(6).add(new Edge(7, 1));

        getPath(node);
        System.out.print("Length of the smallest cycle between "
                + (char)(u + 'A') + " and " + (char)(node + 'A') + " = " + findSmallestCycle());
    }

    public static void getPath(int start) {
        destination = new int[graph.size()];
        for (int i = 0; i < graph.size(); i++)
            destination[i] = 100;
        visited = new boolean[graph.size()];
        destination[start] = 0;

        PriorityQueue<Edge> H = new PriorityQueue<>(Comparator.comparingInt(p -> p.cost));
        H.offer(new Edge(start, 0));

        while (!H.isEmpty()) {
            Edge edge = H.poll();
            int curr = edge.dest;
            if (visited[curr])
                continue;

            visited[curr] = true;
            for (int i = 0; i < graph.get(curr).size(); i++) {
                int next = graph.get(curr).get(i).dest;
                int cost = graph.get(curr).get(i).cost;

                if (destination[next] > destination[curr] + cost) {
                    destination[next] = destination[curr] + cost;
                    H.offer(new Edge(next, destination[next]));
                }
            }
        }
    }
    public static int findSmallestCycle() {
        int cycle = 0;
        for (int i = 0; i < graph.get(u).size(); i++)
            if (node == graph.get(u).get(i).dest)
                cycle = graph.get(u).get(i).cost;
        return cycle + destination[u];
    }
}
