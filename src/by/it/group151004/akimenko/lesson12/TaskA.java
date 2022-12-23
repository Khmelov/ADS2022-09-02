package by.it.group151004.akimenko.lesson12;

import java.util.*;

public class TaskA {
    public int INF = 1000;
    public List<List<Pair>> adj = new ArrayList<>();
    public int[] dist;
    public boolean[] visited;

    public void dijkstra(int start) {
        dist = new int[adj.size()];
        Arrays.fill(dist, INF);
        visited = new boolean[adj.size()];

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.cost));

        pq.offer(new Pair(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Pair pPair = pq.poll();
            int here = pPair.here;
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
        TaskA aVar = new TaskA();
        for (int i = 0; i < 8; i++) {
            aVar.adj.add(new ArrayList<Pair>());
        }

        aVar.adj.get(0).add(new Pair(4, 4));
        aVar.adj.get(0).add(new Pair(5, 8));
        aVar.adj.get(0).add(new Pair(1, 1));
        aVar.adj.get(1).add(new Pair(5, 6));
        aVar.adj.get(1).add(new Pair(6, 6));
        aVar.adj.get(1).add(new Pair(2, 2));
        aVar.adj.get(2).add(new Pair(6, 2));
        aVar.adj.get(2).add(new Pair(3, 1));
        aVar.adj.get(3).add(new Pair(6, 1));
        aVar.adj.get(3).add(new Pair(7, 4));
        aVar.adj.get(6).add(new Pair(5, 1));
        aVar.adj.get(6).add(new Pair(7, 1));
        aVar.adj.get(4).add(new Pair(5, 5));

        aVar.dijkstra(0);

        for (int i = 0; i < aVar.dist.length; i++) {
            System.out.println("Shortest path from A to " + (char) ('A' + i) + ": " + aVar.dist[i]);
        }
    }
}