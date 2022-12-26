package by.it.group151004.karvatskaya.lesson12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TaskC {
    public static final int number = 8;
    private static final char START_VERT = 'A';
    public static List<List<TaskA.Nodus>> graph = new ArrayList<>();
    public static int[] distance;
    public static boolean[] checked;
    public static final int x = 2;
    public static final int y = 1;

    public static class Nodus {
        public int destination;
        public int cost;

        Nodus (int destination, int cost) {
            this.destination = destination;
            this.cost = cost;
        }
    }

    public static void deikstra(int start) {
        distance = new int[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            distance[i] = 100;
        }
        checked = new boolean[graph.size()];
        distance[start] = 0;

        PriorityQueue<Nodus> H = new PriorityQueue<>(Comparator.comparingInt(p -> p.cost));

        H.offer(new Nodus(start, 0));

        while (!H.isEmpty()) {
            Nodus nodus = H.poll();
            int current = nodus.destination;
            if (checked[current])
                continue;

            checked[current] = true;
            for (int i = 0; i < graph.get(current).size(); i++) {
                int next = graph.get(current).get(i).destination;
                int cost = graph.get(current).get(i).cost;

                if (distance[next] > distance[current] + cost) {
                    distance[next] = distance[current] + cost;
                    H.offer(new Nodus(next, distance[next]));
                }
            }
        }
    }

    public static int findTheSmallestCycle() {
        int res = 0;
        for (int i = 0; i < graph.get(y).size(); i++)
            if (x == graph.get(y).get(i).destination)
                res = graph.get(y).get(i).cost;
        return res + distance[y];
    }

    public static void main(String[] args) {
        for (int i = 0; i < number; i++) {
            graph.add(new ArrayList<>());
        }

        graph.get(0).add(new TaskA.Nodus(1, 1)); //from A to B
        graph.get(0).add(new TaskA.Nodus(4, 4)); //from A to E
        graph.get(0).add(new TaskA.Nodus(5, 8)); //from A to F

        graph.get(1).add(new TaskA.Nodus(5, 6)); //from B to F
        graph.get(1).add(new TaskA.Nodus(2, 2)); //from B to C

        graph.get(2).add(new TaskA.Nodus(6, 2)); //from C to G
        graph.get(2).add(new TaskA.Nodus(3, 1)); //from C to D

        graph.get(3).add(new TaskA.Nodus(6, 1)); //from D to G
        graph.get(3).add(new TaskA.Nodus(7, 4)); //from D to H

        graph.get(4).add(new TaskA.Nodus(5, 5)); //from E to F

        graph.get(6).add(new TaskA.Nodus(1, 6)); //from G to B
        graph.get(6).add(new TaskA.Nodus(5, 1)); //from G to F
        graph.get(6).add(new TaskA.Nodus(7, 1)); //from G to H

        deikstra(x);

        System.out.print("The smallest length of the cycle between " + (char)(y + START_VERT) + " and " + (char)(x + START_VERT) + " = " + findTheSmallestCycle());
    }
}
