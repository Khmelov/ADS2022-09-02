package by.it.group151002.bobrik.lesson12;


import by.it.HomeWork;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

@SuppressWarnings("all")

//поставьте курсор на следующую строку и нажмите Ctrl+Shift+F10
public class Test_jd01_12 extends HomeWork {

    @Test (timeout = 3000)
    public void testTaskA() throws Exception {
        TaskA g = new TaskA();
        int v = 9;
        for (int i = 0; i < 8; i++) {
            g.adj.add(new ArrayList<TaskA.Node>());
        }

        g.adj.get(0).add(new TaskA.Node(4, 4));
        g.adj.get(0).add(new TaskA.Node(5, 8));
        g.adj.get(0).add(new TaskA.Node(1, 1));
        g.adj.get(1).add(new TaskA.Node(5, 6));
        g.adj.get(1).add(new TaskA.Node(6, 6));
        g.adj.get(1).add(new TaskA.Node(2, 2));
        g.adj.get(2).add(new TaskA.Node(6, 2));
        g.adj.get(2).add(new TaskA.Node(3, 1));
        g.adj.get(3).add(new TaskA.Node(6, 1));
        g.adj.get(3).add(new TaskA.Node(7, 4));
        g.adj.get(6).add(new TaskA.Node(5, 1));
        g.adj.get(6).add(new TaskA.Node(7, 1));
        g.adj.get(4).add(new TaskA.Node(5, 5));

        g.dijkstra(0);
        boolean ok;
        ok = (g.dist[0] == 0);
        assertTrue("INCORRECT path from 0 to 0", ok);
        ok = (g.dist[1] == 1);
        assertTrue("INCORRECT path from 0 to 1", ok);
        ok = (g.dist[2] == 3);
        assertTrue("INCORRECT path from 0 to 2", ok);
        ok = (g.dist[3] == 4);
        assertTrue("INCORRECT path from 0 to 3", ok);
        ok = (g.dist[4] == 4);
        assertTrue("INCORRECT path from 0 to 4", ok);
        ok = (g.dist[5] == 6);
        assertTrue("INCORRECT path from 0 to 5", ok);
        ok = (g.dist[6] == 5);
        assertTrue("INCORRECT path from 0 to 6", ok);
        ok = (g.dist[7] == 6);
        assertTrue("INCORRECT path from 0 to 7", ok);

    }

    @Test (timeout = 3000)
    public void testTaskB() throws Exception {
        TaskB g = new TaskB();
        g.ford(0);
        boolean ok;
        ok = (g.dist[0] == 0);
        assertTrue("INCORRECT path from 0 to 0", ok);
        ok = (g.dist[1] == 4);
        assertTrue("INCORRECT path from 0 to 1", ok);
        ok = (g.dist[2] == -2);
        assertTrue("INCORRECT path from 0 to 2", ok);
        ok = (g.dist[3] == 0);
        assertTrue("INCORRECT path from 0 to 3", ok);
        ok = (g.dist[5] == -1);
        assertTrue("INCORRECT path from 0 to 5", ok);
        ok = (g.dist[6] == 1);
        assertTrue("INCORRECT path from 0 to 6", ok);
        ok = (g.dist[7] == 0);
        assertTrue("INCORRECT path from 0 to 7", ok);
        ok = (g.dist[8] == 0);
        assertTrue("INCORRECT path from 0 to 8", ok);

    }





}