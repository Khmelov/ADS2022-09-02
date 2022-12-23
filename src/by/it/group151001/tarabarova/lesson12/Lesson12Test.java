package by.it.group151001.tarabarova.lesson12;

import by.it.group151001.tarabarova.lesson12.TaskA.Node;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Lesson12Test {

    @Test(timeout = 2000)
    public void taskA() {
        TaskA AClass = new TaskA();
        for (int i = 0; i < TaskA.amount; i++) {
            AClass.graph.add(new ArrayList<>());
        }

        AClass.graph.get(0).add(new Node(1, 1)); //A -> B
        AClass.graph.get(0).add(new Node(4, 4)); //A -> E
        AClass.graph.get(0).add(new Node(5, 8)); //A -> F

        AClass.graph.get(1).add(new Node(5, 6)); //B -> F
        AClass.graph.get(1).add(new Node(6, 6)); //B -> G
        AClass.graph.get(1).add(new Node(2, 2)); //B -> C

        AClass.graph.get(2).add(new Node(6, 2)); //C -> G
        AClass.graph.get(2).add(new Node(3, 1)); //C -> D

        AClass.graph.get(3).add(new Node(6, 1)); //D -> G
        AClass.graph.get(3).add(new Node(7, 4)); //D -> H

        AClass.graph.get(4).add(new Node(5, 5)); //E -> F

        AClass.graph.get(6).add(new Node(5, 1)); //G -> F
        AClass.graph.get(6).add(new Node(7, 1)); //G -> H

        AClass.dijkstra(0);

        int[] correctDist = {0, 1, 3, 4, 4, 6, 5, 6};

        for (int i = 0; i < TaskA.amount; i++) {
            assertEquals("Failed " + i, correctDist[i], AClass.dist[i]);
        }
    }

    @Test(timeout = 2000)
    public void taskB() {
        TaskB BClass = new TaskB();
        BClass.shortestPaths(0);

        int[] correctDist = {0, 4, -2, 0, 100, -1, 1, 0, 0, 100};

        for (int i = 0; i < TaskB.amount; i++) {
            assertEquals("Failed " + i, correctDist[i], BClass.dist[i]);
        }
    }

    @Test(timeout = 2000)
    public void taskC() {
        int expected = 10;

        for (int i = 0; i < TaskC.amount; i++) {
            TaskC.graph.add(new ArrayList<>());
        }
        TaskC.graph.get(0).add(new Node(1, 1));
        TaskC.graph.get(0).add(new Node(4, 4));
        TaskC.graph.get(0).add(new Node(5, 8));
        TaskC.graph.get(1).add(new Node(5, 6));
        TaskC.graph.get(1).add(new Node(2, 2));
        TaskC.graph.get(2).add(new Node(6, 2));
        TaskC.graph.get(2).add(new Node(3, 1));
        TaskC.graph.get(3).add(new Node(6, 1));
        TaskC.graph.get(3).add(new Node(7, 4));
        TaskC.graph.get(4).add(new Node(5, 5));
        TaskC.graph.get(6).add(new Node(1, 6));
        TaskC.graph.get(6).add(new Node(5, 1));
        TaskC.graph.get(6).add(new Node(7, 1));

        TaskC.dijkstra(TaskC.v);

        assertEquals("Failed", expected, TaskC.findTheSmallestCycle());
    }
}
