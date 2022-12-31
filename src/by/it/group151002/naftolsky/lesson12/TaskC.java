package by.it.group151002.naftolsky.lesson12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TaskC {
    private static final char START = 'A';
    public static final int VERTICES = 8;
    public static List<List<TaskA.Node>> graphList = new ArrayList<>();

    public static int[] dist;
    public static boolean[] visitedNodesArr;
    public static final int v = 2;
    public static final int u = 1;

    public static class Node {
        public int dest;
        public int cost;

        Node (int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    public static void dijkstra(int start) {
        dist = new int[graphList.size()];
        visitedNodesArr = new boolean[graphList.size()];

        for (int i = 0; i < graphList.size(); i++) {
            dist[i] = 9999;
        }

        dist[start] = 0;
        PriorityQueue<TaskC.Node> queue = new PriorityQueue<>(Comparator.comparingInt(p -> p.cost));

        queue.offer(new TaskC.Node(start, 0));

        while (!queue.isEmpty()) {
            TaskC.Node node = queue.poll();
            int curr = node.dest;
            if (visitedNodesArr[curr])
                continue;

            visitedNodesArr[curr] = true;
            for (int i = 0; i < graphList.get(curr).size(); i++) {
                int next = graphList.get(curr).get(i).dest;
                int cost = graphList.get(curr).get(i).cost;

                if (dist[next] > dist[curr] + cost) {
                    dist[next] = dist[curr] + cost;
                    queue.offer(new TaskC.Node(next, dist[next]));
                }
            }
        }
    }

    public static int searchLowestCycle() {
        int res = 0;
        for (int i = 0; i < graphList.get(u).size(); i++)
            if (v == graphList.get(u).get(i).dest)
                res = graphList.get(u).get(i).cost;

        return res + dist[u];
    }

    public static void main(String[] args) {

        for (int i = 0; i < VERTICES; i++) {
            graphList.add(new ArrayList<>());
        }

        graphList.get(0).add(new TaskA.Node(1, 1)); //A -> B
        graphList.get(0).add(new TaskA.Node(4, 4)); //A -> E
        graphList.get(0).add(new TaskA.Node(5, 8)); //A -> F
        graphList.get(1).add(new TaskA.Node(5, 6)); //B -> F
        graphList.get(1).add(new TaskA.Node(2, 2)); //B -> C
        graphList.get(2).add(new TaskA.Node(6, 2)); //C -> G
        graphList.get(2).add(new TaskA.Node(3, 1)); //C -> D
        graphList.get(3).add(new TaskA.Node(6, 1)); //D -> G
        graphList.get(3).add(new TaskA.Node(7, 4)); //D -> H
        graphList.get(4).add(new TaskA.Node(5, 5)); //E -> F
        graphList.get(6).add(new TaskA.Node(1, 6)); //G -> B
        graphList.get(6).add(new TaskA.Node(5, 1)); //G -> F
        graphList.get(6).add(new TaskA.Node(7, 1)); //G -> H
        dijkstra(v);
        System.out.print("The smallest cycle: " + (char)(u + START) + " and " + (char)(v + START) + " = " + searchLowestCycle());
    }
}
