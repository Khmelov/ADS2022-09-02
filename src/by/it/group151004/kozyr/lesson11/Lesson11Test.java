package by.it.group151004.kozyr.lesson11;

import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Test;

import by.it.group151004.kozyr.lesson11.TaskA.DFS;

@SuppressWarnings("all")
public class Lesson11Test {

    @Test(timeout = 2000)
    public void taskA() throws Exception {
        TaskA g = new TaskA(9);
        g.setVertexName(0, "A");
        g.setVertexName(1, "B");
        g.setVertexName(2, "C");
        g.setVertexName(3, "D");
        g.setVertexName(4, "E");
        g.setVertexName(5, "F");
        g.setVertexName(6, "G");
        g.setVertexName(7, "H");
        g.setVertexName(8, "I");
        g.addEdge(3, 7);
        g.addEdge(3, 6);
        g.addEdge(6, 7);
        g.addEdge(0, 1);
        g.addEdge(0, 4);
        g.addEdge(1, 4);
        g.addEdge(1, 2);
        g.addEdge(2, 5);
        g.addEdge(5, 8);

        DFS dfs = new DFS(g);

        for (int v : g.getVerticesInAlphabeticalOrder()) {
            String s1 = g.getVertexName(v) + ": pre = " + dfs.getPre(v) + ", post = " + dfs.getPost(v);
        }

        boolean ok;
        ok = 0 == dfs.getPre(0);
        assertTrue("Failed", ok);

        ok = 1 == dfs.getPre(1);
        assertTrue("Failed", ok);

        ok = 2 == dfs.getPre(2);
        assertTrue("Failed", ok);

        ok = 6 == dfs.getPre(3);
        assertTrue("Failed", ok);

        ok = 5 == dfs.getPre(4);
        assertTrue("Failed", ok);

        ok = 3 == dfs.getPre(5);
        assertTrue("Failed", ok);

        ok = 7 == dfs.getPre(6);
        assertTrue("Failed", ok);

        ok = 8 == dfs.getPre(7);
        assertTrue("Failed", ok);

        ok = 4 == dfs.getPre(8);
        assertTrue("Failed", ok);
    }

    @Test(timeout = 2000)
    public void taskB() throws Exception {
        List<Character> res = new ArrayList();
        res.add('B');
        res.add('A');
        res.add('C');
        res.add('E');
        res.add('D');
        res.add('F');
        res.add('H');
        res.add('G');

        Map<Character, List<Character>> graph = new HashMap<>();
        graph.put('A', List.of('C'));
        graph.put('B', List.of('C'));
        graph.put('C', List.of('D', 'E'));
        graph.put('D', List.of('F'));
        graph.put('E', List.of('F'));
        graph.put('F', List.of('G', 'H'));
        graph.put('G', List.of());
        graph.put('H', List.of());

        TaskB topologicalSort = new TaskB(graph);
        topologicalSort.sort();

        boolean ok = res.equals(topologicalSort.topologicalOrder);
        assertTrue("Failed", ok);
    }

    @Test(timeout = 2000)
    public void taskC() throws Exception {
        List<Character> res = new ArrayList();
        res.add('B');
        res.add('A');
        res.add('C');
        res.add('E');
        res.add('D');
        res.add('F');
        res.add('H');
        res.add('G');

        Map<Character, List<Character>> graph = new HashMap<>();
        graph.put('A', List.of('C'));
        graph.put('B', List.of('C'));
        graph.put('C', List.of('D', 'E'));
        graph.put('D', List.of('F'));
        graph.put('E', List.of('F'));
        graph.put('F', List.of('G', 'H'));
        graph.put('G', List.of());
        graph.put('H', List.of());

        TaskC topologicalSort = new TaskC(graph);
        topologicalSort.sort();
        boolean ok;

        ok = res.equals(topologicalSort.topologicalOrder);
        assertTrue("Failed", ok);
    }
}
