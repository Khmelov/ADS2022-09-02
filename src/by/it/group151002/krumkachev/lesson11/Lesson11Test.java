package by.it.group151002.krumkachev.lesson11;


import static org.junit.Assert.assertTrue;
import java.util.*;
import org.junit.Test;
@SuppressWarnings("all")
public class Lesson11Test {

    @Test(timeout = 2000)
    public void TaskA() throws Exception {
        Graph g = new Graph(9);
        g.setChar(0, 'A');
        g.setChar(1, 'B');
        g.setChar(2, 'C');
        g.setChar(3, 'D');
        g.setChar(4, 'E');
        g.setChar(5, 'F');
        g.setChar(6, 'G');
        g.setChar(7, 'H');
        g.setChar(8, 'I');
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
        assertTrue("Failed", (0 == gr.pre[0]));
        assertTrue("Failed", (1 == gr.pre[1]));
        assertTrue("Failed", (2 == gr.pre[2]));
        assertTrue("Failed", (12 == gr.pre[3]));
        assertTrue("Failed", (4 == gr.pre[4]));
        assertTrue("Failed", (3 == gr.pre[5]));
        assertTrue("Failed", (13 == gr.pre[6]));
        assertTrue("Failed", (14 == gr.pre[7]));
        assertTrue("Failed", (6 == gr.pre[8]));

        assertTrue("Failed", (11 == gr.post[0]));
        assertTrue("Failed", (10 == gr.post[1]));
        assertTrue("Failed", (9 == gr.post[2]));
        assertTrue("Failed", (17 == gr.post[3]));
        assertTrue("Failed", (5 == gr.post[4]));
        assertTrue("Failed", (8 == gr.post[5]));
        assertTrue("Failed", (16 == gr.post[6]));
        assertTrue("Failed", (15 == gr.post[7]));
        assertTrue("Failed", (7 == gr.post[8]));
    }

    @Test(timeout = 2000)
    public void TaskB() throws Exception {
        Graph g = new Graph(8);
        g.setChar(0, 'A');
        g.setChar(1, 'B');
        g.setChar(2, 'C');
        g.setChar(3, 'D');
        g.setChar(4, 'E');
        g.setChar(5, 'F');
        g.setChar(6, 'G');
        g.setChar(7, 'H');
        g.addOrientedEdge(0, 2);
        g.addOrientedEdge(1, 2);
        g.addOrientedEdge(2, 3);
        g.addOrientedEdge(2, 4);
        g.addOrientedEdge(3, 5);
        g.addOrientedEdge(4, 5);
        g.addOrientedEdge(5, 6);
        g.addOrientedEdge(5, 7);
        GraphB gr = new GraphB();
        gr.topological = new ArrayList<>();
        gr.sort(g);
        assertTrue("Failed", gr.topological.toString().equals("[B, A, C, E, D, F, H, G]"));
    }

    @Test(timeout = 2000)
    public void TaskC() throws Exception {
        Graph g = new Graph(8);
        g.setChar(0, 'A');
        g.setChar(1, 'B');
        g.setChar(2, 'C');
        g.setChar(3, 'D');
        g.setChar(4, 'E');
        g.setChar(5, 'F');
        g.setChar(6, 'G');
        g.setChar(7, 'H');
        g.addOrientedEdge(0, 2);
        g.addOrientedEdge(1, 2);
        g.addOrientedEdge(2, 3);
        g.addOrientedEdge(2, 4);
        g.addOrientedEdge(3, 5);
        g.addOrientedEdge(4, 5);
        g.addOrientedEdge(5, 6);
        g.addOrientedEdge(5, 7);
        GraphC gr = new GraphC();
        gr.topological = new ArrayList<>();
        gr.sort(g);
        assertTrue("Failed", gr.topological.toString().equals("[B, A, C, E, D, F, H, G]"));
        assertTrue("Failed", (0 == gr.pre[0]));
        assertTrue("Failed", (14 == gr.pre[1]));
        assertTrue("Failed", (1 == gr.pre[2]));
        assertTrue("Failed", (2 == gr.pre[3]));
        assertTrue("Failed", (10 == gr.pre[4]));
        assertTrue("Failed", (3 == gr.pre[5]));
        assertTrue("Failed", (4 == gr.pre[6]));
        assertTrue("Failed", (6 == gr.pre[7]));

        assertTrue("Failed", (13 == gr.post[0]));
        assertTrue("Failed", (15 == gr.post[1]));
        assertTrue("Failed", (12 == gr.post[2]));
        assertTrue("Failed", (9 == gr.post[3]));
        assertTrue("Failed", (11 == gr.post[4]));
        assertTrue("Failed", (8 == gr.post[5]));
        assertTrue("Failed", (5 == gr.post[6]));
        assertTrue("Failed", (7 == gr.post[7]));

        gr.sinkAndSources(g);
        assertTrue("Failed", gr.sources.toString().equals("[A, B]"));
        assertTrue("Failed", gr.sinks.toString().equals("[G, H]"));

        gr.indegree = new int[g.getNumberOfVertex()];
        for (int i = 0; i < g.getNumberOfVertex(); i++) {
            gr.visited[i] = false;
            for (int j: g.graph.get(i))
                gr.indegree[j]++;
        }
        gr.countNumOfLinearizations(g);
        assertTrue("Failed", (gr.numOfLinearizations == 8));
    }

}