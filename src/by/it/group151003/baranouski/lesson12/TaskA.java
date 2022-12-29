package by.it.group151003.baranouski.lesson12;

import java.util.*;

public class TaskA {
    public int INF = 1000;
    public List<List<Pair>> m_adj = new ArrayList<>();
    public int[] m_dist;
    public boolean[] m_visited;

    public void dijkstra(int start) {
        m_dist = new int[m_adj.size()];
        Arrays.fill(m_dist, INF);
        m_visited = new boolean[m_adj.size()];

        PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.m_cost));

        pq.offer(new Pair(start, 0));
        m_dist[start] = 0;

        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int here = p.m_here;
            if (m_visited[here])
                continue;

            m_visited[here] = true;
            for (int i = 0; i < m_adj.get(here).size(); i++) {
                int there = m_adj.get(here).get(i).m_here;
                int cost = m_adj.get(here).get(i).m_cost;

                if (m_dist[there] > m_dist[here] + cost) {
                    m_dist[there] = m_dist[here] + cost;
                    pq.offer(new Pair(there, m_dist[there]));
                }
            }
        }
    }

    public static class Pair {
        int m_here;
        int m_cost;

        Pair(int here, int cost) {
            this.m_here = here;
            this.m_cost = cost;
        }
    }

    public static void main(String[] args) {
        TaskA sus = new TaskA();
        for (int i = 0; i < 8; i++) {
            sus.m_adj.add(new ArrayList<Pair>());
        }

        sus.m_adj.get(0).add(new Pair(4, 4));
        sus.m_adj.get(0).add(new Pair(5, 8));
        sus.m_adj.get(0).add(new Pair(1, 1));
        sus.m_adj.get(1).add(new Pair(5, 6));
        sus.m_adj.get(1).add(new Pair(6, 6));
        sus.m_adj.get(1).add(new Pair(2, 2));
        sus.m_adj.get(2).add(new Pair(6, 2));
        sus.m_adj.get(2).add(new Pair(3, 1));
        sus.m_adj.get(3).add(new Pair(6, 1));
        sus.m_adj.get(3).add(new Pair(7, 4));
        sus.m_adj.get(6).add(new Pair(5, 1));
        sus.m_adj.get(6).add(new Pair(7, 1));
        sus.m_adj.get(4).add(new Pair(5, 5));

        sus.dijkstra(0);

        for (int i = 0; i < sus.m_dist.length; i++) {
            System.out.println("Shortest path from A to " + (char) ('A' + i) + ": " + sus.m_dist[i]);
        }
    }
}
