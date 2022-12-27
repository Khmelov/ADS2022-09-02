package by.it.group151004.karvatskaya.lesson12;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TaskA {
    public static final int number = 8;
    private static final char START_VERT = 'A';
    public List<List<Nodus>> graph = new ArrayList<>();
    public int[] distance;
    public boolean[] checked;

    public static class Nodus {
        public int destination;
        public int cost;

        Nodus (int destination, int cost) {
            this.destination = destination;
            this.cost = cost;
        }
    }

    public void deikstra(int start) {
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
                int nxt = graph.get(current).get(i).destination;
                int cost = graph.get(current).get(i).cost;

                if (distance[nxt] > distance[current] + cost) {
                    distance[nxt] = distance[current] + cost;
                    H.offer(new Nodus(nxt, distance[nxt]));
                }
            }
        }
    }

    public static void main(String[] args) {
        TaskA taskA = new TaskA();
        for (int i = 0; i < number; i++) {
            taskA.graph.add(new ArrayList<>());
        }

        taskA.graph.get(0).add(new Nodus(1, 1)); //A -> B
        taskA.graph.get(0).add(new Nodus(4, 4)); //A -> E
        taskA.graph.get(0).add(new Nodus(5, 8)); //A -> F

        taskA.graph.get(1).add(new Nodus(5, 6)); //B -> F
        taskA.graph.get(1).add(new Nodus(6, 6)); //B -> G
        taskA.graph.get(1).add(new Nodus(2, 2)); //B -> C

        taskA.graph.get(2).add(new Nodus(6, 2)); //C -> G
        taskA.graph.get(2).add(new Nodus(3, 1)); //C -> D

        taskA.graph.get(3).add(new Nodus(6, 1)); //D -> G
        taskA.graph.get(3).add(new Nodus(7, 4)); //D -> H

        taskA.graph.get(4).add(new Nodus(5, 5)); //E -> F

        taskA.graph.get(6).add(new Nodus(5, 1)); //G -> F
        taskA.graph.get(6).add(new Nodus(7, 1)); //G -> H

        taskA.deikstra(0);

        for (int i = 0; i < taskA.distance.length; i++) {
            System.out.println("The shortest path from " + START_VERT + " to " + (char)(START_VERT + i) + " = " + taskA.distance[i]);
        }
    }
}
