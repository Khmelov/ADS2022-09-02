package by.it.group151002.ryabov.lesson11;
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
        ok = (0 == gr.pre[0]);
        assertTrue("Failed", ok);
        ok = (1 == gr.pre[1]);
        assertTrue("Failed", ok);
        ok = (2 == gr.pre[2]);
        assertTrue("Failed", ok);
        ok = (12 == gr.pre[3]);
        assertTrue("Failed", ok);
        ok = (4 == gr.pre[4]);
        assertTrue("Failed", ok);
        ok = (3 == gr.pre[5]);
        assertTrue("Failed", ok);
        ok = (13 == gr.pre[6]);
        assertTrue("Failed", ok);
        ok = (14 == gr.pre[7]);
        assertTrue("Failed", ok);
        ok = (6 == gr.pre[8]);
        assertTrue("Failed", ok);

        ok = (11 == gr.post[0]);
        assertTrue("Failed", ok);
        ok = (10 == gr.post[1]);
        assertTrue("Failed", ok);
        ok = (9 == gr.post[2]);
        assertTrue("Failed", ok);
        ok = (17 == gr.post[3]);
        assertTrue("Failed", ok);
        ok = (5 == gr.post[4]);
        assertTrue("Failed", ok);
        ok = (8 == gr.post[5]);
        assertTrue("Failed", ok);
        ok = (16 == gr.post[6]);
        assertTrue("Failed", ok);
        ok = (15 == gr.post[7]);
        assertTrue("Failed", ok);
        ok = (7 == gr.post[8]);
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
        ok = (0 == gr.pre[0]);
        assertTrue("Failed", ok);
        ok = (14 == gr.pre[1]);
        assertTrue("Failed", ok);
        ok = (1 == gr.pre[2]);
        assertTrue("Failed", ok);
        ok = (2 == gr.pre[3]);
        assertTrue("Failed", ok);
        ok = (10 == gr.pre[4]);
        assertTrue("Failed", ok);
        ok = (3 == gr.pre[5]);
        assertTrue("Failed", ok);
        ok = (4 == gr.pre[6]);
        assertTrue("Failed", ok);
        ok = (6 == gr.pre[7]);
        assertTrue("Failed", ok);

        ok = (13 == gr.post[0]);
        assertTrue("Failed", ok);
        ok = (15 == gr.post[1]);
        assertTrue("Failed", ok);
        ok = (12 == gr.post[2]);
        assertTrue("Failed", ok);
        ok = (9 == gr.post[3]);
        assertTrue("Failed", ok);
        ok = (11 == gr.post[4]);
        assertTrue("Failed", ok);
        ok = (8 == gr.post[5]);
        assertTrue("Failed", ok);
        ok = (5 == gr.post[6]);
        assertTrue("Failed", ok);
        ok = (7 == gr.post[7]);
        assertTrue("Failed", ok);

        gr.sinkAndSources(g);
        ok = (gr.sources.toString().equals("[A, B]"));
        assertTrue("Failed", ok);
        ok = (gr.sinks.toString().equals("[G, H]"));
        assertTrue("Failed", ok);

        gr.indegree = new int[g.getVertexCount()];
        for (int i = 0; i < g.getVertexCount(); i++) {
            gr.visited[i] = false;
            for (int j: g.graph.get(i))
                gr.indegree[j]++;
        }
        gr.countLinearizationsNum(g);
        ok = (gr.linearizationsNum == 8);
        assertTrue("Failed", ok);
    }

}
