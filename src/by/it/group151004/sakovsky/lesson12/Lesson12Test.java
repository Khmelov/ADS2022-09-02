package by.it.group151004.sakovsky.lesson12;

import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Test;

import by.it.group151004.sakovsky.lesson12.TaskA.Pair;

@SuppressWarnings("all")
public class Lesson12Test {

    @Test(timeout = 2000)
    public void taskA() throws Exception {
        TaskA proc = new TaskA();
        for (int i = 0; i < 8; i++) {
            proc.adj.add(new ArrayList<Pair>());
        }

        proc.adj.get(0).add(new Pair(4, 4));
        proc.adj.get(0).add(new Pair(5, 8));
        proc.adj.get(0).add(new Pair(1, 1));
        proc.adj.get(1).add(new Pair(5, 6));
        proc.adj.get(1).add(new Pair(6, 6));
        proc.adj.get(1).add(new Pair(2, 2));
        proc.adj.get(2).add(new Pair(6, 2));
        proc.adj.get(2).add(new Pair(3, 1));
        proc.adj.get(3).add(new Pair(6, 1));
        proc.adj.get(3).add(new Pair(7, 4));
        proc.adj.get(6).add(new Pair(5, 1));
        proc.adj.get(6).add(new Pair(7, 1));
        proc.adj.get(4).add(new Pair(5, 5));

        proc.dijkstra(0);

        boolean ok;
        ok = 0 == proc.dist[0];
        assertTrue("Failed", ok);

        ok = 1 == proc.dist[1];
        assertTrue("Failed", ok);

        ok = 3 == proc.dist[2];
        assertTrue("Failed", ok);

        ok = 4 == proc.dist[3];
        assertTrue("Failed", ok);

        ok = 4 == proc.dist[4];
        assertTrue("Failed", ok);

        ok = 6 == proc.dist[5];
        assertTrue("Failed", ok);

        ok = 5 == proc.dist[6];
        assertTrue("Failed", ok);

        ok = 6 == proc.dist[7];
        assertTrue("Failed", ok);
    }

    @Test(timeout = 2000)
    public void taskB() throws Exception {
        TaskB proc = new TaskB();
        proc.bellmanFord(0);

        boolean ok;
        ok = 0 == proc.dist[0];
        assertTrue("Failed", ok);
        ok = 4 == proc.dist[1];
        assertTrue("Failed", ok);
        ok = -2 == proc.dist[2];
        assertTrue("Failed", ok);
        ok = 0 == proc.dist[3];
        assertTrue("Failed", ok);
        ok = 999999998 == proc.dist[4];
        assertTrue("Failed", ok);
        ok = -1 == proc.dist[5];
        assertTrue("Failed", ok);
        ok = 1 == proc.dist[6];
        assertTrue("Failed", ok);
        ok = 0 == proc.dist[7];
        assertTrue("Failed", ok);
        ok = 0 == proc.dist[8];
        assertTrue("Failed", ok);
        ok = 999999998 == proc.dist[9];
        assertTrue("Failed", ok);
    }

    @Test(timeout = 2000)
    public void taskC() throws Exception {
        TaskC proc = new TaskC(2, 3);
        int minLength = proc.floydWarshell();
        boolean ok = minLength == 2;
        assertTrue("Failed", ok);
    }
}