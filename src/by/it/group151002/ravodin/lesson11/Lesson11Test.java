package by.it.group151002.ravodin.lesson11;

import static org.junit.Assert.assertTrue;
import java.util.*;
import org.junit.Test;
@SuppressWarnings("all")
public class Lesson11Test {

    @Test(timeout = 2000)
    public void TaskA() throws Exception {
        Graph g = new Graph(9);
        g.setName(0, 'A');
        g.setName(1, 'B');
        g.setName(2, 'C');
        g.setName(3, 'D');
        g.setName(4, 'E');
        g.setName(5, 'F');
        g.setName(6, 'G');
        g.setName(7, 'H');
        g.setName(8, 'I');
        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 4);
        g.addEdge(1, 2);
        g.addEdge(2, 5);
        g.addEdge(4, 5);
        g.addEdge(5, 8);
        g.addEdge(3, 6);
        g.addEdge(7, 6);
        g.addEdge(3, 7);
        GraphA gr = new GraphA();
        gr.dfs(g, 0);
        boolean ok;
        ok = (0 == gr.before[0]);
        assertTrue("Failed", ok);
        ok = (1 == gr.before[1]);
        assertTrue("Failed", ok);
        ok = (2 == gr.before[2]);
        assertTrue("Failed", ok);
        ok = (12 == gr.before[3]);
        assertTrue("Failed", ok);
        ok = (4 == gr.before[4]);
        assertTrue("Failed", ok);
        ok = (3 == gr.before[5]);
        assertTrue("Failed", ok);
        ok = (13 == gr.before[6]);
        assertTrue("Failed", ok);
        ok = (14 == gr.before[7]);
        assertTrue("Failed", ok);
        ok = (6 == gr.before[8]);
        assertTrue("Failed", ok);

        ok = (11 == gr.after[0]);
        assertTrue("Failed", ok);
        ok = (10 == gr.after[1]);
        assertTrue("Failed", ok);
        ok = (9 == gr.after[2]);
        assertTrue("Failed", ok);
        ok = (17 == gr.after[3]);
        assertTrue("Failed", ok);
        ok = (5 == gr.after[4]);
        assertTrue("Failed", ok);
        ok = (8 == gr.after[5]);
        assertTrue("Failed", ok);
        ok = (16 == gr.after[6]);
        assertTrue("Failed", ok);
        ok = (15 == gr.after[7]);
        assertTrue("Failed", ok);
        ok = (7 == gr.after[8]);
        assertTrue("Failed", ok);
    }

    @Test(timeout = 2000)
    public void TaskB() throws Exception {
        Graph g = new Graph(8);
        g.setName(0, 'A');
        g.setName(1, 'B');
        g.setName(2, 'C');
        g.setName(3, 'D');
        g.setName(4, 'E');
        g.setName(5, 'F');
        g.setName(6, 'G');
        g.setName(7, 'H');
        g.addOrEdge(0, 2);
        g.addOrEdge(1, 2);
        g.addOrEdge(2, 3);
        g.addOrEdge(2, 4);
        g.addOrEdge(3, 5);
        g.addOrEdge(4, 5);
        g.addOrEdge(5, 6);
        g.addOrEdge(5, 7);
        GraphB gr = new GraphB();
        gr.topological = new ArrayList<>();
        gr.sort(g);
        boolean ok;
        ok = (gr.topological.toString().equals("[B, A, C, E, D, F, H, G]"));
        assertTrue("Failed", ok);
    }

    @Test(timeout = 2000)
    public void TaskC() throws Exception {
        Graph g = new Graph(8);
        g.setName(0, 'A');
        g.setName(1, 'B');
        g.setName(2, 'C');
        g.setName(3, 'D');
        g.setName(4, 'E');
        g.setName(5, 'F');
        g.setName(6, 'G');
        g.setName(7, 'H');
        g.addOrEdge(0, 2);
        g.addOrEdge(1, 2);
        g.addOrEdge(2, 3);
        g.addOrEdge(2, 4);
        g.addOrEdge(3, 5);
        g.addOrEdge(4, 5);
        g.addOrEdge(5, 6);
        g.addOrEdge(5, 7);
        GraphC gr = new GraphC();
        gr.topological = new ArrayList<>();
        gr.sort(g);
        boolean ok;
        ok = (gr.topological.toString().equals("[B, A, C, E, D, F, H, G]"));
        assertTrue("Failed", ok);
        ok = (0 == gr.before[0]);
        assertTrue("Failed", ok);
        ok = (14 == gr.before[1]);
        assertTrue("Failed", ok);
        ok = (1 == gr.before[2]);
        assertTrue("Failed", ok);
        ok = (2 == gr.before[3]);
        assertTrue("Failed", ok);
        ok = (10 == gr.before[4]);
        assertTrue("Failed", ok);
        ok = (3 == gr.before[5]);
        assertTrue("Failed", ok);
        ok = (4 == gr.before[6]);
        assertTrue("Failed", ok);
        ok = (6 == gr.before[7]);
        assertTrue("Failed", ok);

        ok = (13 == gr.after[0]);
        assertTrue("Failed", ok);
        ok = (15 == gr.after[1]);
        assertTrue("Failed", ok);
        ok = (12 == gr.after[2]);
        assertTrue("Failed", ok);
        ok = (9 == gr.after[3]);
        assertTrue("Failed", ok);
        ok = (11 == gr.after[4]);
        assertTrue("Failed", ok);
        ok = (8 == gr.after[5]);
        assertTrue("Failed", ok);
        ok = (5 == gr.after[6]);
        assertTrue("Failed", ok);
        ok = (7 == gr.after[7]);
        assertTrue("Failed", ok);

        gr.sinkAndSources(g);
        ok = (gr.starts.toString().equals("[A, B]"));
        assertTrue("Failed", ok);
        ok = (gr.sinks.toString().equals("[G, H]"));
        assertTrue("Failed", ok);

        gr.pow = new int[g.getVertexNumber()];
        for (int i = 0; i < g.getVertexNumber(); i++) {
            gr.isVisited[i] = false;
            for (int j: g.graph.get(i))
                gr.pow[j]++;
        }
        gr.countLinearizationsNum(g);
        ok = (gr.linearizationsNum == 8);
        assertTrue("Failed", ok);
    }

}