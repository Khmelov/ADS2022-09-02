package by.it.group151002.strukov.lesson12;

import java.util.*;

public class TaskA {
    public static int INFINITY = Integer.MAX_VALUE;
    public static List<List<Node>> matrix = new ArrayList<>();
    public static int[] destination;
    public static boolean[] visited;

    public static class Node {
        int here;
        int cost;

        Node(int here, int cost) {
            this.here = here;
            this.cost = cost;
        }
    }

    public static void pathDeikstra(int start) {
        destination = new int[matrix.size()];
        Arrays.fill(destination, INFINITY);
        visited = new boolean[matrix.size()];

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node p1, Node p2) {
                return p1.cost - p2.cost;
            }
        });

        pq.offer(new Node(start, 0));
        destination[start] = 0;

        while (!pq.isEmpty()) {
            Node p = pq.poll();
            int here = p.here;
            if (visited[here])
                continue;
            visited[here] = true;
            for (int i = 0; i < matrix.get(here).size(); i++) {
                int there = matrix.get(here).get(i).here;
                int cost = matrix.get(here).get(i).cost;

                if (destination[there] > destination[here] + cost) {
                    destination[there] = destination[here] + cost;
                    pq.offer(new Node(there, destination[there]));
                }
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            matrix.add(new ArrayList<Node>());
        }
        matrix.get(0).add(new Node(4, 4));
        matrix.get(0).add(new Node(5, 8));
        matrix.get(0).add(new Node(1, 1));
        matrix.get(1).add(new Node(5, 6));
        matrix.get(1).add(new Node(6, 6));
        matrix.get(1).add(new Node(2, 2));
        matrix.get(2).add(new Node(6, 2));
        matrix.get(2).add(new Node(3, 1));
        matrix.get(3).add(new Node(6, 1));
        matrix.get(3).add(new Node(7, 4));
        matrix.get(6).add(new Node(5, 1));
        matrix.get(6).add(new Node(7, 1));
        matrix.get(4).add(new Node(5, 5));

        pathDeikstra(0);

        for (int i = 0; i < destination.length; i++) {
            System.out.println("Best road from 0 to " + i + ": " + destination[i]);
        }
    }
}
