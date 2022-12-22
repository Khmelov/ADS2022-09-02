package by.it.group151001.beryozkin.lesson12;
import java.util.*;

public class TaskA {
    public static int[] dest;
    public static boolean[] visited;
    public static int INF = Integer.MAX_VALUE;
    public static List<List<Node>> a = new ArrayList<>();

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
            a.add(new ArrayList<>());
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

        dejkstraAlg(0);

        for (int i = 0; i < dest.length; i++) {
            System.out.println("The most short path from 0 to " + i + ": " + dest[i]);
        }
    }

    public static void dejkstraAlg(int start) {
        dest = new int[a.size()];
        Arrays.fill(dest, INF);
        visited = new boolean[a.size()];

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(Node p1, Node p2) {
                return p1.cost - p2.cost;
            }
        });

        pq.offer(new Node(start, 0));
        dest[start] = 0;

        while (!pq.isEmpty()) {
            Node p = pq.poll();
            int here = p.here;
            if (visited[here])
                continue;

            visited[here] = true;
            for (int i = 0; i < a.get(here).size(); i++) {
                int there = a.get(here).get(i).here;
                int cost = a.get(here).get(i).cost;

                if (dest[there] > dest[here] + cost) {
                    dest[there] = dest[here] + cost;
                    pq.offer(new Node(there, dest[there]));
                }
            }
        }
    }
}
