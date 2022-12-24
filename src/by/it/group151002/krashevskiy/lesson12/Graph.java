package by.it.group151002.krashevskiy.lesson12;

import java.util.*;

public class Graph {

    public static class Node {
        public int dest;
        public int cost;

        Node (int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    public static final int AMOUNT_OF_VERTICES = 10;

    public ArrayList<ArrayList<Node>> list = new ArrayList<>();

    public int[] dist;

    private boolean[] visited;


    public void dijkstra(int start) {
        dist = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            dist[i] = 100;
        }
        visited = new boolean[list.size()];
        dist[start] = 0;

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(p -> p.cost));

        priorityQueue.offer(new Node(start, 0));

        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            int curr = node.dest;
            if (visited[curr])
                continue;

            visited[curr] = true;
            for (int i = 0; i < list.get(curr).size(); i++) {
                int next = list.get(curr).get(i).dest;
                int cost = list.get(curr).get(i).cost;

                if (dist[next] > dist[curr] + cost) {
                    dist[next] = dist[curr] + cost;
                    priorityQueue.offer(new Node(next, dist[next]));
                }
            }
        }
    }

    public int[] shortestPaths(int start) {
        int MAX = 100;
        int[] dist = new int[AMOUNT_OF_VERTICES];

        int[][] list = {
            {MAX, 4, -2, MAX, MAX, MAX, MAX, MAX, MAX, MAX}, //A
            {MAX, MAX, MAX, MAX, MAX, MAX, -2, -4, MAX, MAX}, //B
            {MAX, MAX, MAX, 2, MAX, 1, MAX, MAX, MAX, MAX}, //C
            {MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX}, //D
            {MAX, MAX, MAX, MAX, MAX, -2, MAX, 3, MAX, MAX}, //E
            {MAX, MAX, MAX, 3, MAX, MAX, MAX, MAX, MAX, MAX}, //F
            {MAX, MAX, MAX, MAX, MAX, MAX, MAX, MAX, -1, MAX}, //G
            {MAX, MAX, MAX, MAX, MAX, MAX, 1, MAX, MAX, MAX}, //H
            {MAX, MAX, MAX, MAX, MAX, MAX, MAX, 1, MAX, MAX}, //I
            {7, MAX, 6, MAX, 6, 5, MAX, MAX, MAX, MAX} //S
        };
        for (int i = 0; i < AMOUNT_OF_VERTICES; i++) {
            dist[i] = MAX;
        }
        dist[start] = 0;

        for (int i = 0; i < AMOUNT_OF_VERTICES - 1; i++) {
            for (int j = 0; j < AMOUNT_OF_VERTICES; j++) {
                for (int k = 0; k < AMOUNT_OF_VERTICES; k++) {
                    if (list[j][k] != MAX && dist[k] > dist[j] + list[j][k]) {
                        dist[k] = dist[j] + list[j][k];
                    }
                }
            }
        }

        return dist;
    }

    public int findTheSmallestCycle() {
        int res = 0;
        for (int i = 0; i < list.get(1).size(); i++)
            if (2 == list.get(1).get(i).dest)
                res = list.get(1).get(i).cost;
        return res + dist[1];
    }
}
