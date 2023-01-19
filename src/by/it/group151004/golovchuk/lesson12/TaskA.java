package by.it.group151004.golovchuk.lesson12;

import java.util.*;

public class TaskA {
    public int INF = 1000;
    public List<List<by.it.group151004.golovchuk.lesson12.TaskA.Pair>> adj = new ArrayList<>();
    public int[] dist;
    public boolean[] visited;

    public void dijkstra(int start) {
        dist = new int[adj.size()];
        Arrays.fill(dist, INF);
        visited = new boolean[adj.size()];

        PriorityQueue<by.it.group151004.golovchuk.lesson12.TaskA.Pair> pq = new PriorityQueue<>(new Comparator<by.it.group151004.golovchuk.lesson12.TaskA.Pair>() {
            @Override
            public int compare(by.it.group151004.golovchuk.lesson12.TaskA.Pair p1, by.it.group151004.golovchuk.lesson12.TaskA.Pair p2) {
                return p1.cost - p2.cost;
            }
        });

        pq.offer(new by.it.group151004.golovchuk.lesson12.TaskA.Pair(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            by.it.group151004.golovchuk.lesson12.TaskA.Pair p = pq.poll();
            int here = p.here;
            if (visited[here])
                continue;

            visited[here] = true;
            for (int i = 0; i < adj.get(here).size(); i++) {
                int there = adj.get(here).get(i).here;
                int cost = adj.get(here).get(i).cost;

                if (dist[there] > dist[here] + cost) {
                    dist[there] = dist[here] + cost;
                    pq.offer(new by.it.group151004.golovchuk.lesson12.TaskA.Pair(there, dist[there]));
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
        by.it.group151004.golovchuk.lesson12.TaskA sus = new by.it.group151004.golovchuk.lesson12.TaskA();
        for (int i = 0; i < 8; i++) {
            sus.adj.add(new ArrayList<by.it.group151004.golovchuk.lesson12.TaskA.Pair>());
        }

        sus.adj.get(0).add(new by.it.group151004.golovchuk.lesson12.TaskA.Pair(4, 4));
        sus.adj.get(0).add(new by.it.group151004.golovchuk.lesson12.TaskA.Pair(5, 8));
        sus.adj.get(0).add(new by.it.group151004.golovchuk.lesson12.TaskA.Pair(1, 1));
        sus.adj.get(1).add(new by.it.group151004.golovchuk.lesson12.TaskA.Pair(5, 6));
        sus.adj.get(1).add(new by.it.group151004.golovchuk.lesson12.TaskA.Pair(6, 6));
        sus.adj.get(1).add(new by.it.group151004.golovchuk.lesson12.TaskA.Pair(2, 2));
        sus.adj.get(2).add(new by.it.group151004.golovchuk.lesson12.TaskA.Pair(6, 2));
        sus.adj.get(2).add(new by.it.group151004.golovchuk.lesson12.TaskA.Pair(3, 1));
        sus.adj.get(3).add(new by.it.group151004.golovchuk.lesson12.TaskA.Pair(6, 1));
        sus.adj.get(3).add(new by.it.group151004.golovchuk.lesson12.TaskA.Pair(7, 4));
        sus.adj.get(6).add(new by.it.group151004.golovchuk.lesson12.TaskA.Pair(5, 1));
        sus.adj.get(6).add(new by.it.group151004.golovchuk.lesson12.TaskA.Pair(7, 1));
        sus.adj.get(4).add(new by.it.group151004.golovchuk.lesson12.TaskA.Pair(5, 5));

        sus.dijkstra(0);

        for (int i = 0; i < sus.dist.length; i++) {
            System.out.println("Shortest path from A to " + (char) ('A' + i) + ": " + sus.dist[i]);
        }
    }
}
