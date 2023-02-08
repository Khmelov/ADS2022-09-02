package by.it.group151001.milkovskaya.lesson12;

import java.util.*;

public class TaskA {
    public static int INF = Integer.MAX_VALUE;
    public static List<List<Node>> matr = new ArrayList<>();
    public static int[] dist;
    public static boolean[] visited;

    public static void Deikstra(int start) {
        dist = new int[matr.size()];
        Arrays.fill(dist, INF);
        visited = new boolean[matr.size()];

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.cost - o2.cost;
            }
        });

        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node p = pq.poll();
            int here = p.here;
            if (visited[here])
                continue;

            visited[here] = true;
            for (int i = 0; i < matr.get(here).size(); i++) {
                int there = matr.get(here).get(i).here;
                int cost = matr.get(here).get(i).cost;

                if (dist[there] > dist[here] + cost) {
                    dist[there] = dist[here] + cost;
                    pq.offer(new Node(there, dist[there]));
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
            matr.add(new ArrayList<Node>());
        }

        matr.get(0).add(new Node(4, 4));
        matr.get(0).add(new Node(5, 8));
        matr.get(0).add(new Node(1, 1));
        matr.get(1).add(new Node(5, 6));
        matr.get(1).add(new Node(6, 6));
        matr.get(1).add(new Node(2, 2));
        matr.get(2).add(new Node(6, 2));
        matr.get(2).add(new Node(3, 1));
        matr.get(3).add(new Node(6, 1));
        matr.get(3).add(new Node(7, 4));
        matr.get(6).add(new Node(5, 1));
        matr.get(6).add(new Node(7, 1));
        matr.get(4).add(new Node(5, 5));

        Deikstra(0);

        for (int i = 0; i < dist.length; i++) {
            System.out.println("Shortest path from 0 to " + i + ": " + dist[i]);
        }
    }
}
