package by.it.group151001.trybchik.lesson12;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Lesson12Test {
    @Test(timeout = 2000)
    public void taskA() {
        TaskA  A =new TaskA();
        for (int i = 0; i < 8; i++) {
            A.graph.add(new ArrayList<>());
        }
        A.graph.get(0).add(new TaskA.Edge(1, 1)); //A -> B
        A.graph.get(0).add(new TaskA.Edge(4, 4)); //A -> E
        A.graph.get(0).add(new TaskA.Edge(5, 8)); //A -> F
        A.graph.get(1).add(new TaskA.Edge(5, 6)); //B -> F
        A.graph.get(1).add(new TaskA.Edge(6, 6)); //B -> G
        A.graph.get(1).add(new TaskA.Edge(2, 2)); //B -> C
        A.graph.get(2).add(new TaskA.Edge(6, 2)); //C -> G
        A.graph.get(2).add(new TaskA.Edge(3, 1)); //C -> D
        A.graph.get(3).add(new TaskA.Edge(6, 1)); //D -> G
        A.graph.get(3).add(new TaskA.Edge(7, 4)); //D -> H
        A.graph.get(4).add(new TaskA.Edge(5, 5)); //E -> F
        A.graph.get(6).add(new TaskA.Edge(5, 1)); //G -> F
        A.graph.get(6).add(new TaskA.Edge(7, 1)); //G -> H

        A.getPath(0);

        int[] correctDist = {0, 1, 3, 4, 4, 6, 5, 6};

        for (int i = 0; i < 8; i++) {
            assertEquals("Failed " + i, correctDist[i], A.dist[i]);
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
