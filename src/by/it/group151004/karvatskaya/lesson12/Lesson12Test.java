package by.it.group151004.karvatskaya.lesson12;

import by.it.group151004.karvatskaya.lesson12.TaskA.Nodus;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class Lesson12Test {

    @Test(timeout = 2000)
    public void taskA() {
        TaskA AClass = new TaskA();
        for (int i = 0; i < TaskA.number; i++) {
            AClass.graph.add(new ArrayList<>());
        }

        AClass.graph.get(0).add(new Nodus(1, 1)); //A -> B
        AClass.graph.get(0).add(new Nodus(4, 4)); //A -> E
        AClass.graph.get(0).add(new Nodus(5, 8)); //A -> F

        AClass.graph.get(1).add(new Nodus(5, 6)); //B -> F
        AClass.graph.get(1).add(new Nodus(6, 6)); //B -> G
        AClass.graph.get(1).add(new Nodus(2, 2)); //B -> C

        AClass.graph.get(2).add(new Nodus(6, 2)); //C -> G
        AClass.graph.get(2).add(new Nodus(3, 1)); //C -> D

        AClass.graph.get(3).add(new Nodus(6, 1)); //D -> G
        AClass.graph.get(3).add(new Nodus(7, 4)); //D -> H

        AClass.graph.get(4).add(new Nodus(5, 5)); //E -> F

        AClass.graph.get(6).add(new Nodus(5, 1)); //G -> F
        AClass.graph.get(6).add(new Nodus(7, 1)); //G -> H

        AClass.deikstra(0);

        int[] rightDist = {0, 1, 3, 4, 4, 6, 5, 6};

        for (int i = 0; i < TaskA.number; i++) {
            assertEquals("Failed " + i, rightDist[i], AClass.distance[i]);
        }
    }

    @Test(timeout = 2000)
    public void taskB() {
        TaskB BClass = new TaskB();
        BClass.shortestPaths(0);

        int[] correctDist = {0, 4, -2, 0, 100, -1, 1, 0, 0, 100};

        for (int i = 0; i < TaskB.number; i++) {
            assertEquals("Failed " + i, correctDist[i], BClass.distance[i]);
        }
    }

    @Test(timeout = 2000)
    public void taskC() {
        int expected = 10;

        for (int i = 0; i < TaskC.number; i++) {
            TaskC.graph.add(new ArrayList<>());
        }
        TaskC.graph.get(0).add(new Nodus(1, 1));
        TaskC.graph.get(0).add(new Nodus(4, 4));
        TaskC.graph.get(0).add(new Nodus(5, 8));
        TaskC.graph.get(1).add(new Nodus(5, 6));
        TaskC.graph.get(1).add(new Nodus(2, 2));
        TaskC.graph.get(2).add(new Nodus(6, 2));
        TaskC.graph.get(2).add(new Nodus(3, 1));
        TaskC.graph.get(3).add(new Nodus(6, 1));
        TaskC.graph.get(3).add(new Nodus(7, 4));
        TaskC.graph.get(4).add(new Nodus(5, 5));
        TaskC.graph.get(6).add(new Nodus(1, 6));
        TaskC.graph.get(6).add(new Nodus(5, 1));
        TaskC.graph.get(6).add(new Nodus(7, 1));

        TaskC.deikstra(TaskC.x);

        assertEquals("Failed", expected, TaskC.findTheSmallestCycle());
    }
}
