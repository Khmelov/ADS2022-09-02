package by.it.group151004.buhovets.lesson12;

import java.util.*;

public class TaskA {
    public static int INF = Integer.MAX_VALUE;
    public static List<List<Pair>> adj = new ArrayList<>();
    public static int[] dist;
    public static boolean[] visited;

    public static void dijkstra(int start) {
        dist = new int[adj.size()];
        Arrays.fill(dist, INF);
        visited = new boolean[adj.size()];

        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                return p1.cost - p2.cost;
            }
        });

        pq.offer(new Pair(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int here = p.here;
            if (visited[here])
                continue;

            visited[here] = true;
            for (int i = 0; i < adj.get(here).size(); i++) {
                int there = adj.get(here).get(i).here;
                int cost = adj.get(here).get(i).cost;

                if (dist[there] > dist[here] + cost) {
                    dist[there] = dist[here] + cost;
                    pq.offer(new Pair(there, dist[there]));
                }
            }
        }
    }

    public static class Pair {
        int here;
        int cost;

        Pair(int here, int cost) {
            this.here = here;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            adj.add(new ArrayList<Pair>());
        }

        adj.get(0).add(new Pair(4, 4));
        adj.get(0).add(new Pair(5, 8));
        adj.get(0).add(new Pair(1, 1));
        adj.get(1).add(new Pair(5, 6));
        adj.get(1).add(new Pair(6, 6));
        adj.get(1).add(new Pair(2, 2));
        adj.get(2).add(new Pair(6, 2));
        adj.get(2).add(new Pair(3, 1));
        adj.get(3).add(new Pair(6, 1));
        adj.get(3).add(new Pair(7, 4));
        adj.get(6).add(new Pair(5, 1));
        adj.get(6).add(new Pair(7, 1));
        adj.get(4).add(new Pair(5, 5));

        dijkstra(0);

        for (int i = 0; i < dist.length; i++) {
            System.out.println("Shortest path from A to " + (char) ('A' + i) + ": " + dist[i]);
        }
    }
}