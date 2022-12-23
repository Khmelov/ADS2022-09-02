package by.it.group151004.akimenko.lesson12;

import by.it.group151004.akimenko.lesson12.TaskA;
import by.it.group151004.akimenko.lesson12.TaskB;
import by.it.group151004.akimenko.lesson12.TaskC;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class Lesson12Test {

    @Test(timeout = 2000)
    public void taskA() throws Exception {
        by.it.group151004.akimenko.lesson12.TaskA aVar = new TaskA();
        for (int i = 0; i < 8; i++) {
            aVar.adj.add(new ArrayList<TaskA.Pair>());
        }

        aVar.adj.get(0).add(new TaskA.Pair(4, 4));
        aVar.adj.get(0).add(new TaskA.Pair(5, 8));
        aVar.adj.get(0).add(new TaskA.Pair(1, 1));
        aVar.adj.get(1).add(new TaskA.Pair(5, 6));
        aVar.adj.get(1).add(new TaskA.Pair(6, 6));
        aVar.adj.get(1).add(new TaskA.Pair(2, 2));
        aVar.adj.get(2).add(new TaskA.Pair(6, 2));
        aVar.adj.get(2).add(new TaskA.Pair(3, 1));
        aVar.adj.get(3).add(new TaskA.Pair(6, 1));
        aVar.adj.get(3).add(new TaskA.Pair(7, 4));
        aVar.adj.get(6).add(new TaskA.Pair(5, 1));
        aVar.adj.get(6).add(new TaskA.Pair(7, 1));
        aVar.adj.get(4).add(new TaskA.Pair(5, 5));

        aVar.dijkstra(0);

        boolean fl;
        fl = 0 == aVar.dist[0];
        assertTrue("Failed", fl);

        fl = 1 == aVar.dist[1];
        assertTrue("Failed", fl);

        fl = 3 == aVar.dist[2];
        assertTrue("Failed", fl);

        fl = 4 == aVar.dist[3];
        assertTrue("Failed", fl);

        fl = 4 == aVar.dist[4];
        assertTrue("Failed", fl);

        fl = 6 == aVar.dist[5];
        assertTrue("Failed", fl);

        fl = 5 == aVar.dist[6];
        assertTrue("Failed", fl);

        fl = 6 == aVar.dist[7];
        assertTrue("Failed", fl);
    }

    @Test(timeout = 2000)
    public void taskB() throws Exception {
        by.it.group151004.akimenko.lesson12.TaskB bVar = new TaskB();
        bVar.bellmanFord(0);

        boolean fl;
        fl = 0 == bVar.dist[0];
        assertTrue("Failed", fl);
        fl = 4 == bVar.dist[1];
        assertTrue("Failed", fl);
        fl = -2 == bVar.dist[2];
        assertTrue("Failed", fl);
        fl = 0 == bVar.dist[3];
        assertTrue("Failed", fl);
        fl = 999999998 == bVar.dist[4];
        assertTrue("Failed", fl);
        fl = -1 == bVar.dist[5];
        assertTrue("Failed", fl);
        fl = 1 == bVar.dist[6];
        assertTrue("Failed", fl);
        fl = 0 == bVar.dist[7];
        assertTrue("Failed", fl);
        fl = 0 == bVar.dist[8];
        assertTrue("Failed", fl);
        fl = 999999998 == bVar.dist[9];
        assertTrue("Failed", fl);
    }

    @Test(timeout = 2000)
    public void taskC() throws Exception {
        by.it.group151004.akimenko.lesson12.TaskC cVar = new TaskC(2, 3);
        int minLength = cVar.floydWarshell();
        boolean fl = minLength == 2;
        assertTrue("Failed", fl);
    }
}
