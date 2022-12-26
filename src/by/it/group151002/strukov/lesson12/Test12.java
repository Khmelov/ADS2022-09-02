package by.it.group151002.strukov.lesson12;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("all")
public class Test12 {

    @Test (timeout = 3000)
    public void testA() throws Exception {
        TaskA g = new TaskA();
        int v = 9;
        for (int i = 0; i < 8; i++) {
            g.matrix.add(new ArrayList<TaskA.Node>());
        }
        g.matrix.get(0).add(new TaskA.Node(4, 4));
        g.matrix.get(0).add(new TaskA.Node(5, 8));
        g.matrix.get(0).add(new TaskA.Node(1, 1));
        g.matrix.get(1).add(new TaskA.Node(5, 6));
        g.matrix.get(1).add(new TaskA.Node(6, 6));
        g.matrix.get(1).add(new TaskA.Node(2, 2));
        g.matrix.get(2).add(new TaskA.Node(6, 2));
        g.matrix.get(2).add(new TaskA.Node(3, 1));
        g.matrix.get(3).add(new TaskA.Node(6, 1));
        g.matrix.get(3).add(new TaskA.Node(7, 4));
        g.matrix.get(6).add(new TaskA.Node(5, 1));
        g.matrix.get(6).add(new TaskA.Node(7, 1));
        g.matrix.get(4).add(new TaskA.Node(5, 5));
        g.pathDeikstra(0);
        boolean ok;
        ok = (g.destination[0] == 0);
        assertTrue("INCORRECT path from 0 to 0", ok);
        ok = (g.destination[1] == 1);
        assertTrue("INCORRECT path from 0 to 1", ok);
        ok = (g.destination[2] == 3);
        assertTrue("INCORRECT path from 0 to 2", ok);
        ok = (g.destination[3] == 4);
        assertTrue("INCORRECT path from 0 to 3", ok);
        ok = (g.destination[4] == 4);
        assertTrue("INCORRECT path from 0 to 4", ok);
        ok = (g.destination[5] == 6);
        assertTrue("INCORRECT path from 0 to 5", ok);
        ok = (g.destination[6] == 5);
        assertTrue("INCORRECT path from 0 to 6", ok);
        ok = (g.destination[7] == 6);
        assertTrue("INCORRECT path from 0 to 7", ok);
    }

    @Test(timeout = 3000)
    public void testB() throws Exception {
        TaskB g = new TaskB();
        g.BelFord(0);
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
    @Test(timeout = 3000)
    public void testC() {
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
        TaskC.getPath(TaskC.node);
        assertEquals("Failed", expected, TaskC.findSmallestCycle());
    }
}
