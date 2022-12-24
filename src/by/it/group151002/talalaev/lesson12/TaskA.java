package by.it.group151002.talalaev.lesson12;

import java.util.*;

public class TaskA {
    public static int INF = Integer.MAX_VALUE;
    public static List<List<Node>> arrList = new ArrayList<>();
    public static int[] distance;
    public static boolean[] visited;

    public static void dijkstra(int start) {
        distance = new int[arrList.size()];
        Arrays.fill(distance, INF);
        visited = new boolean[arrList.size()];

        PriorityQueue<Node> preorQ = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node p1, Node p2) {
                return p1.cost - p2.cost;
            }
        });

        preorQ.offer(new Node(start, 0));
        distance[start] = 0;

        while (!preorQ.isEmpty()) {
            Node p = preorQ.poll();
            int here = p.here;
            if (visited[here])
                continue;

            visited[here] = true;
            for (int i = 0; i < arrList.get(here).size(); i++) {
                int there = arrList.get(here).get(i).here;
                int cost = arrList.get(here).get(i).cost;

                if (distance[there] > distance[here] + cost) {
                    distance[there] = distance[here] + cost;
                    preorQ.offer(new Node(there, distance[there]));
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
            arrList.add(new ArrayList<Node>());
        }

        arrList.get(0).add(new Node(4, 4));
        arrList.get(0).add(new Node(5, 8));
        arrList.get(0).add(new Node(1, 1));
        arrList.get(1).add(new Node(5, 6));
        arrList.get(1).add(new Node(6, 6));
        arrList.get(1).add(new Node(2, 2));
        arrList.get(2).add(new Node(6, 2));
        arrList.get(2).add(new Node(3, 1));
        arrList.get(3).add(new Node(6, 1));
        arrList.get(3).add(new Node(7, 4));
        arrList.get(6).add(new Node(5, 1));
        arrList.get(6).add(new Node(7, 1));
        arrList.get(4).add(new Node(5, 5));

        dijkstra(0);

        for (int i = 0; i < distance.length; i++) {
            System.out.println("Shortest path from 0 to " + i + ": " + distance[i]);
        }
    }
}
