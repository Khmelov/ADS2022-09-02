package by.it.group151001.novik.lesson12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TaskC {
    public static final int AMOUNT_OF_VERTICES = 8;
    private static final char START_VERTEX = 'A';
    public static List<List<TaskC.Node>> graph = new ArrayList<>();
    public static int[] dist;
    public static boolean[] visited;
    public static final int v = 2;
    public static final int u = 1;

    public static class Node {
        public int dest;
        public int cost;

        Node (int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    public static void dijkstra(int start) {
        dist = new int[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            dist[i] = 100;
        }
        visited = new boolean[graph.size()];
        dist[start] = 0;

        PriorityQueue<TaskC.Node> H = new PriorityQueue<>(Comparator.comparingInt(p -> p.cost));

        H.offer(new TaskC.Node(start, 0));

        while (!H.isEmpty()) {
            TaskC.Node node = H.poll();
            int curr = node.dest;
            if (visited[curr])
                continue;

            visited[curr] = true;
            for (int i = 0; i < graph.get(curr).size(); i++) {
                int next = graph.get(curr).get(i).dest;
                int cost = graph.get(curr).get(i).cost;

                if (dist[next] > dist[curr] + cost) {
                    dist[next] = dist[curr] + cost;
                    H.offer(new TaskC.Node(next, dist[next]));
                }
            }
        }
    }

    public static int findTheSmallestCycle() {
        int res = 0;
        for (int i = 0; i < graph.get(u).size(); i++)
            if (v == graph.get(u).get(i).dest)
                res = graph.get(u).get(i).cost;
        return res + dist[u];
    }

    public static void main(String[] args) {
        for (int i = 0; i < AMOUNT_OF_VERTICES; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new TaskC.Node(1, 1));
        graph.get(0).add(new TaskC.Node(4, 4));
        graph.get(0).add(new TaskC.Node(5, 8));

        graph.get(1).add(new TaskC.Node(5, 6));
        graph.get(1).add(new TaskC.Node(2, 2));

        graph.get(2).add(new TaskC.Node(6, 2));
        graph.get(2).add(new TaskC.Node(3, 1));

        graph.get(3).add(new TaskC.Node(6, 1));
        graph.get(3).add(new TaskC.Node(7, 4));

        graph.get(4).add(new TaskC.Node(5, 5));

        graph.get(6).add(new TaskC.Node(1, 6));
        graph.get(6).add(new TaskC.Node(5, 1));
        graph.get(6).add(new TaskC.Node(7, 1));

        dijkstra(v);

        System.out.print("The smallest length of the cycle between " + (char)(u + START_VERTEX) + " and " + (char)(v + START_VERTEX) + " = " + findTheSmallestCycle());
    }
}