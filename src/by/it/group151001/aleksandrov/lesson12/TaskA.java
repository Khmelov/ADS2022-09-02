package by.it.group151001.aleksandrov.lesson12;

import java.util.*;

public class TaskA {
    public static int INF = Integer.MAX_VALUE;
    public static List<List<Node>> a = new ArrayList<>();
    public static int[] d;
    public static boolean[] vis;

    public static void dijkstra(int start) {
        d = new int[a.size()];
        Arrays.fill(d, INF);
        vis = new boolean[a.size()];

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node p1, Node p2) {
                return p1.cost - p2.cost;
            }
        });

        pq.offer(new Node(start, 0));
        d[start] = 0;

        while (!pq.isEmpty()) {
            Node p = pq.poll();
            int here = p.here;
            if (vis[here])
                continue;

            vis[here] = true;
            for (int i = 0; i < a.get(here).size(); i++) {
                int there = a.get(here).get(i).here;
                int cost = a.get(here).get(i).cost;

                if (d[there] > d[here] + cost) {
                    d[there] = d[here] + cost;
                    pq.offer(new Node(there, d[there]));
                }
            }
        }
    }

    public static class Node {
        int here;
        int cost;

        Node(int here, int cost) {
            this.here = here;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            a.add(new ArrayList<Node>());
        }

        a.get(0).add(new Node(4, 4));
        a.get(0).add(new Node(5, 8));
        a.get(0).add(new Node(1, 1));
        a.get(1).add(new Node(5, 6));
        a.get(1).add(new Node(6, 6));
        a.get(1).add(new Node(2, 2));
        a.get(2).add(new Node(6, 2));
        a.get(2).add(new Node(3, 1));
        a.get(3).add(new Node(6, 1));
        a.get(3).add(new Node(7, 4));
        a.get(6).add(new Node(5, 1));
        a.get(6).add(new Node(7, 1));
        a.get(4).add(new Node(5, 5));

        dijkstra(0);

        for (int i = 0; i < d.length; i++) {
            System.out.println("Shortest path from 0 to " + i + ": " + d[i]);
        }
    }
}
