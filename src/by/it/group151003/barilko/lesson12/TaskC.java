package by.it.group151003.barilko.lesson12;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Vector;

public class TaskC {
    public static final int AMOUNT_OF_VERTICES = 8;
    private static final char START_VERTEX = 'A';
    private static final int MAX = 99999;

    public static Vector<Vector<TaskA.Node>> graph = new Vector<>();
    public static int[] dist;
    public static boolean[] visited;


    public static class Node {
        public int dest;
        public int cost;

        Node(int dest, int cost) {
            this.dest = dest;
            this.cost = cost;
        }
    }

    public static void dijkstra(int start) {
        dist = new int[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            dist[i] = MAX;
        }
        visited = new boolean[graph.size()];
        dist[start] = 0;

        PriorityQueue<Node> H = new PriorityQueue<>(Comparator.comparingInt(p -> p.cost));

        H.offer(new Node(start, 0));

        while (!H.isEmpty()) {
            TaskC.Node node = H.poll();
            int curr = node.dest;
            if (visited[curr])
                continue;

            visited[curr] = true;
            for (int i = 0; i < graph.get(curr).size(); i++) {
                int next = graph.get(curr).get(i).dest;
                int cost = graph.get(curr).get(i).cost;

                if (dist[next] > dist[curr] + cost) {
                    dist[next] = dist[curr] + cost;
                    H.offer(new TaskC.Node(next, dist[next]));
                }
            }
        }
    }

    public static int findTheSmallestCycle(int VertBeg, int VertEnd) {
        int res = 0;
        for (int i = 0; i < graph.get(VertBeg).size(); i++)
            if (VertEnd == graph.get(VertBeg).get(i).dest)
                res = graph.get(VertBeg).get(i).cost;
        return res + dist[VertBeg];
    }

    public static void main(String[] args) {
        for (int i = 0; i < AMOUNT_OF_VERTICES; i++) {
            graph.add(new Vector<>());
        }
        System.out.print("Enter first vertex of edge: ");
        Scanner in = new Scanner(System.in);
        int VertBeg = (int)((in.next().toUpperCase().charAt(0)) - START_VERTEX);
        System.out.print("Enter second vertex of edge: ");
        int VertEnd = (int)((in.next().toUpperCase().charAt(0)) - START_VERTEX);
        

        graph.get(0).add(new TaskA.Node(1, 1)); //A -> B
        graph.get(0).add(new TaskA.Node(4, 4)); //A -> E
        graph.get(0).add(new TaskA.Node(5, 8)); //A -> F

        graph.get(1).add(new TaskA.Node(5, 6)); //B -> F
        graph.get(1).add(new TaskA.Node(2, 2)); //B -> C
        graph.get(1).add(new TaskA.Node(6, 6)); //B -> G

        graph.get(2).add(new TaskA.Node(6, 2)); //C -> G
        graph.get(2).add(new TaskA.Node(3, 1)); //C -> D

        graph.get(3).add(new TaskA.Node(6, 1)); //D -> G
        //graph.get(3).add(new TaskA.Node(7, 4)); //D -> H

        graph.get(7).add(new TaskA.Node(3, 4)); //H -> D

        graph.get(4).add(new TaskA.Node(5, 5)); //E -> F

        graph.get(6).add(new TaskA.Node(5, 1)); //G -> F
        graph.get(6).add(new TaskA.Node(7, 1)); //G -> H

        dijkstra(VertEnd);
        int path = findTheSmallestCycle(VertBeg, VertEnd);
        System.out.print("The smallest length of the cycle between " + (char) (VertBeg + START_VERTEX) + " and " + (char) (VertEnd + START_VERTEX) + " = " + (path > MAX ? "no cicles" : path));
    }
}
