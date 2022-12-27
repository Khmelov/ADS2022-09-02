package by.it.group151003.mytnik.lesson12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TaskC {
    public static final int AMOUNT_OF_VERTICES = 8;
    public static List<List<TaskA.Node>> graph = new ArrayList<>();
    public static int[] dist;
    public static boolean[] visited;
    public static final int v = 2;
    public static final int u = 1;

    public static class Node {
        public int dest;
        public int cost;

        public Node(int dest, int cost) {
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

        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(p -> p.cost));

        queue.offer(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int curr = node.dest;
            if (visited[curr])
                continue;

            visited[curr] = true;
            for (int i = 0; i < graph.get(curr).size(); i++) {
                int next = graph.get(curr).get(i).dest;
                int cost = graph.get(curr).get(i).cost;

                if (dist[next] > dist[curr] + cost) {
                    dist[next] = dist[curr] + cost;
                    queue.offer(new Node(next, dist[next]));
                }
            }
        }
    }

    public static int findTheSmallestCycle() {
        int res = 0;
        for (int i = 0; i < graph.get(u).size(); i++) {
            if (v == graph.get(u).get(i).dest) {
                res = graph.get(u).get(i).cost;
            }
        }
        return res + dist[u];
    }
}