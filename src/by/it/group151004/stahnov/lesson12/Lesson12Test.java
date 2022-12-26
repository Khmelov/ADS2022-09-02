package by.it.group151004.stahnov.lesson12;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Lesson12Test {
    @Test(timeout = 2000)
    public void taskA() {
        TaskA taskA = new TaskA();
        for (int i = 0; i < 8; i++) {
            taskA.adj.add(new ArrayList<>());
        }

        taskA.adj.get(0).add(new TaskA.Pair(4, 4));
        taskA.adj.get(0).add(new TaskA.Pair(5, 8));
        taskA.adj.get(0).add(new TaskA.Pair(1, 1));
        taskA.adj.get(1).add(new TaskA.Pair(5, 6));
        taskA.adj.get(1).add(new TaskA.Pair(6, 6));
        taskA.adj.get(1).add(new TaskA.Pair(2, 2));
        taskA.adj.get(2).add(new TaskA.Pair(6, 2));
        taskA.adj.get(2).add(new TaskA.Pair(3, 1));
        taskA.adj.get(3).add(new TaskA.Pair(6, 1));
        taskA.adj.get(3).add(new TaskA.Pair(7, 4));
        taskA.adj.get(6).add(new TaskA.Pair(5, 1));
        taskA.adj.get(6).add(new TaskA.Pair(7, 1));
        taskA.adj.get(4).add(new TaskA.Pair(5, 5));

        taskA.dijkstra(0);
        int[] correctDist = {0, 1, 3, 4, 4, 6, 5, 6};
        for (int i = 0; i < taskA.dist.length; i++) {
            System.out.println("Shortest path from A to " + (char) ('A' + i) + ": " + taskA.dist[i]);
            assertEquals("Failed " + i, correctDist[i], taskA.dist[i]);
            System.out.println("Correct");
        }
    }
    @Test(timeout = 2000)
    public void taskB() {
        TaskB B = new TaskB();
        B.shortestPaths(0);

        int[] correctDist = {0, 4, -2, 0, 100, -1, 1, 0, 0, 100};

        for (int i = 0; i < 10; i++) {
            assertEquals("Failed " + i, correctDist[i], B.dist[i]);
        }
    }
    @Test(timeout = 2000)
    public void taskC() {
        int expected = 10;

        for (int i = 0; i < 8; i++) {
            TaskC.graph.add(new ArrayList<>());
        }
        TaskC.graph.get(0).add(new TaskC.Edge(1, 1));
        TaskC.graph.get(0).add(new TaskC.Edge(4, 4));
        TaskC.graph.get(0).add(new TaskC.Edge(5, 8));
        TaskC.graph.get(1).add(new TaskC.Edge(5, 6));
        TaskC.graph.get(1).add(new TaskC.Edge(2, 2));
        TaskC.graph.get(2).add(new TaskC.Edge(6, 2));
        TaskC.graph.get(2).add(new TaskC.Edge(3, 1));
        TaskC.graph.get(3).add(new TaskC.Edge(6, 1));
        TaskC.graph.get(3).add(new TaskC.Edge(7, 4));
        TaskC.graph.get(4).add(new TaskC.Edge(5, 5));
        TaskC.graph.get(6).add(new TaskC.Edge(1, 6));
        TaskC.graph.get(6).add(new TaskC.Edge(5, 1));
        TaskC.graph.get(6).add(new TaskC.Edge(7, 1));

        TaskC.getPath(TaskC.v);

        assertEquals("Failed", expected, TaskC.findTheSmallestCycle());
    }
}
