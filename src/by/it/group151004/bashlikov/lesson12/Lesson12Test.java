package by.it.group151004.bashlikov.lesson12;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class Lesson12Test {
    @Test(timeout = 3000)
    public void testTaskA() {
        for (int i = 0; i < 8; i++) {
            TaskA.matrix.add(new ArrayList<>());
        }
        TaskA.matrix.get(0).add(new TaskA.Node(4, 4));
        TaskA.matrix.get(0).add(new TaskA.Node(5, 8));
        TaskA.matrix.get(0).add(new TaskA.Node(1, 1));
        TaskA.matrix.get(1).add(new TaskA.Node(5, 6));
        TaskA.matrix.get(1).add(new TaskA.Node(6, 6));
        TaskA.matrix.get(1).add(new TaskA.Node(2, 2));
        TaskA.matrix.get(2).add(new TaskA.Node(6, 2));
        TaskA.matrix.get(2).add(new TaskA.Node(3, 1));
        TaskA.matrix.get(3).add(new TaskA.Node(6, 1));
        TaskA.matrix.get(3).add(new TaskA.Node(7, 4));
        TaskA.matrix.get(6).add(new TaskA.Node(5, 1));
        TaskA.matrix.get(6).add(new TaskA.Node(7, 1));
        TaskA.matrix.get(4).add(new TaskA.Node(5, 5));
        TaskA.pathDijkstra(0);
        boolean ok;
        ok = (TaskA.destination[0] == 0);
        assertTrue("INCORRECT path from 0 to 0", ok);
        ok = (TaskA.destination[1] == 1);
        assertTrue("INCORRECT path from 0 to 1", ok);
        ok = (TaskA.destination[2] == 3);
        assertTrue("INCORRECT path from 0 to 2", ok);
        ok = (TaskA.destination[3] == 4);
        assertTrue("INCORRECT path from 0 to 3", ok);
        ok = (TaskA.destination[4] == 4);
        assertTrue("INCORRECT path from 0 to 4", ok);
        ok = (TaskA.destination[5] == 6);
        assertTrue("INCORRECT path from 0 to 5", ok);
        ok = (TaskA.destination[6] == 5);
        assertTrue("INCORRECT path from 0 to 6", ok);
        ok = (TaskA.destination[7] == 6);
        assertTrue("INCORRECT path from 0 to 7", ok);
    }

    @Test(timeout = 3000)
    public void testTaskB() {
        TaskB g = new TaskB();
        g.ford(0);
        boolean ok;
        ok = (g.distance[0] == 0);
        assertTrue("INCORRECT path from 0 to 0", ok);
        ok = (g.distance[1] == 4);
        assertTrue("INCORRECT path from 0 to 1", ok);
        ok = (g.distance[2] == -2);
        assertTrue("INCORRECT path from 0 to 2", ok);
        ok = (g.distance[3] == 0);
        assertTrue("INCORRECT path from 0 to 3", ok);
        ok = (g.distance[5] == -1);
        assertTrue("INCORRECT path from 0 to 5", ok);
        ok = (g.distance[6] == 1);
        assertTrue("INCORRECT path from 0 to 6", ok);
        ok = (g.distance[7] == 0);
        assertTrue("INCORRECT path from 0 to 7", ok);
        ok = (g.distance[8] == 0);
        assertTrue("INCORRECT path from 0 to 8", ok);

    }
}
