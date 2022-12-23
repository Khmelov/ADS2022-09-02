package by.it.group151004.pyshny.lesson11;

import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Test;

import by.it.group151004.pyshny.lesson11.TaskA.DFS;

@SuppressWarnings("all")
public class Lesson11Test {

    @Test(timeout = 2000)
    public void taskA() throws Exception {
        TaskA testA = new TaskA(9);
        testA.setName(0, "A");
        testA.setName(1, "B");
        testA.setName(2, "C");
        testA.setName(3, "D");
        testA.setName(4, "E");
        testA.setName(5, "F");
        testA.setName(6, "G");
        testA.setName(7, "H");
        testA.setName(8, "I");
        testA.addEdge(3, 7);
        testA.addEdge(3, 6);
        testA.addEdge(6, 7);
        testA.addEdge(0, 1);
        testA.addEdge(0, 4);
        testA.addEdge(1, 4);
        testA.addEdge(1, 2);
        testA.addEdge(2, 5);
        testA.addEdge(5, 8);
        testA.addEdge(4,5);
        DFS dfs = new DFS(testA);

        boolean ok;
        ok = 0 == dfs.getPre(0);
        assertTrue("Failed", ok);

        ok = 1 == dfs.getPre(1);
        assertTrue("Failed", ok);

        ok = 2 == dfs.getPre(2);
        assertTrue("Failed", ok);

        ok = 6 == dfs.getPre(3);
        assertTrue("Failed", ok);

        ok = 4 == dfs.getPre(4);
        assertTrue("Failed", ok);

        ok = 3 == dfs.getPre(5);
        assertTrue("Failed", ok);

        ok = 7 == dfs.getPre(6);
        assertTrue("Failed", ok);

        ok = 8 == dfs.getPre(7);
        assertTrue("Failed", ok);

        ok = 5 == dfs.getPre(8);
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
