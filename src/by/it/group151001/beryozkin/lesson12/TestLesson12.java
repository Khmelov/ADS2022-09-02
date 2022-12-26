package by.it.group151001.beryozkin.lesson12;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("all")
public class TestLesson12 {

    @Test (timeout = 3000)
    public void testTaskA() throws Exception {
        TaskA g = new TaskA();
        int v = 9;
        for (int i = 0; i < 8; i++) {
            g.a.add(new ArrayList<TaskA.Node>());
        }
        g.a.get(0).add(new TaskA.Node(4, 4));
        g.a.get(0).add(new TaskA.Node(5, 8));
        g.a.get(0).add(new TaskA.Node(1, 1));
        g.a.get(1).add(new TaskA.Node(5, 6));
        g.a.get(1).add(new TaskA.Node(6, 6));
        g.a.get(1).add(new TaskA.Node(2, 2));
        g.a.get(2).add(new TaskA.Node(6, 2));
        g.a.get(2).add(new TaskA.Node(3, 1));
        g.a.get(3).add(new TaskA.Node(6, 1));
        g.a.get(3).add(new TaskA.Node(7, 4));
        g.a.get(6).add(new TaskA.Node(5, 1));
        g.a.get(6).add(new TaskA.Node(7, 1));
        g.a.get(4).add(new TaskA.Node(5, 5));
        g.dejkstraAlg(0);
        boolean ok;
        ok = (g.dest[0] == 0);
        assertTrue("INCORRECT path from 0 to 0", ok);
        ok = (g.dest[1] == 1);
        assertTrue("INCORRECT path from 0 to 1", ok);
        ok = (g.dest[2] == 3);
        assertTrue("INCORRECT path from 0 to 2", ok);
        ok = (g.dest[3] == 4);
        assertTrue("INCORRECT path from 0 to 3", ok);
        ok = (g.dest[4] == 4);
        assertTrue("INCORRECT path from 0 to 4", ok);
        ok = (g.dest[5] == 6);
        assertTrue("INCORRECT path from 0 to 5", ok);
        ok = (g.dest[6] == 5);
        assertTrue("INCORRECT path from 0 to 6", ok);
        ok = (g.dest[7] == 6);
        assertTrue("INCORRECT path from 0 to 7", ok);
    }

    @Test(timeout = 3000)
    public void testTaskB() throws Exception {
        TaskB g = new TaskB();
        g.ford(0);
        boolean ok;
        ok = (g.dest[0] == 0);
        assertTrue("INCORRECT path from 0 to 0", ok);
        ok = (g.dest[1] == 4);
        assertTrue("INCORRECT path from 0 to 1", ok);
        ok = (g.dest[2] == -2);
        assertTrue("INCORRECT path from 0 to 2", ok);
        ok = (g.dest[3] == 0);
        assertTrue("INCORRECT path from 0 to 3", ok);
        ok = (g.dest[5] == -1);
        assertTrue("INCORRECT path from 0 to 5", ok);
        ok = (g.dest[6] == 1);
        assertTrue("INCORRECT path from 0 to 6", ok);
        ok = (g.dest[7] == 0);
        assertTrue("INCORRECT path from 0 to 7", ok);
        ok = (g.dest[8] == 0);
        assertTrue("INCORRECT path from 0 to 8", ok);

    }
    @Test(timeout = 3000)
    public void testTaskC() {
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
        TaskC.getPath(TaskC.verch);
        assertEquals("Failed", expected, TaskC.findTheSmallestCycle());
    }
}