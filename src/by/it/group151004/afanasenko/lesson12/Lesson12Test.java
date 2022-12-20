package by.it.group151004.afanasenko.lesson12;

import by.it.group151004.afanasenko.lesson12.TaskA.Pair;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

@SuppressWarnings("all")
public class Lesson12Test {

	@Test(timeout = 2000)
	public void taskA() throws Exception {
		TaskA sus = new TaskA();
		for (int i = 0; i < 8; i++) {
			sus.adj.add(new ArrayList<Pair>());
		}

		sus.adj.get(0).add(new Pair(4, 4));
		sus.adj.get(0).add(new Pair(5, 8));
		sus.adj.get(0).add(new Pair(1, 1));
		sus.adj.get(1).add(new Pair(5, 6));
		sus.adj.get(1).add(new Pair(6, 6));
		sus.adj.get(1).add(new Pair(2, 2));
		sus.adj.get(2).add(new Pair(6, 2));
		sus.adj.get(2).add(new Pair(3, 1));
		sus.adj.get(3).add(new Pair(6, 1));
		sus.adj.get(3).add(new Pair(7, 4));
		sus.adj.get(6).add(new Pair(5, 1));
		sus.adj.get(6).add(new Pair(7, 1));
		sus.adj.get(4).add(new Pair(5, 5));

		sus.dijkstra(0);

		boolean ok;
		ok = 0 == sus.dist[0];
		assertTrue("Failed", ok);

		ok = 1 == sus.dist[1];
		assertTrue("Failed", ok);

		ok = 3 == sus.dist[2];
		assertTrue("Failed", ok);

		ok = 4 == sus.dist[3];
		assertTrue("Failed", ok);

		ok = 4 == sus.dist[4];
		assertTrue("Failed", ok);

		ok = 6 == sus.dist[5];
		assertTrue("Failed", ok);

		ok = 5 == sus.dist[6];
		assertTrue("Failed", ok);

		ok = 6 == sus.dist[7];
		assertTrue("Failed", ok);
	}

	@Test(timeout = 2000)
	public void taskB() throws Exception {
		TaskB sus = new TaskB();
		sus.bellmanFord(0);

		boolean ok;
		ok = 0 == sus.dist[0];
		assertTrue("Failed", ok);
		ok = 4 == sus.dist[1];
		assertTrue("Failed", ok);
		ok = -2 == sus.dist[2];
		assertTrue("Failed", ok);
		ok = 0 == sus.dist[3];
		assertTrue("Failed", ok);
		ok = 999999998 == sus.dist[4];
		assertTrue("Failed", ok);
		ok = -1 == sus.dist[5];
		assertTrue("Failed", ok);
		ok = 1 == sus.dist[6];
		assertTrue("Failed", ok);
		ok = 0 == sus.dist[7];
		assertTrue("Failed", ok);
		ok = 0 == sus.dist[8];
		assertTrue("Failed", ok);
		ok = 999999998 == sus.dist[9];
		assertTrue("Failed", ok);
	}

	@Test(timeout = 2000)
	public void taskC() throws Exception {
		TaskC sus = new TaskC(2, 3);
		int minLength = sus.floydWarshell();
		boolean ok = minLength == 2;
		assertTrue("Failed", ok);
	}
}