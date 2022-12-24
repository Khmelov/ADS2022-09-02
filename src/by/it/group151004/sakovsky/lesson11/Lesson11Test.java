package by.it.group151004.sakovsky.lesson11;

import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Test;

import by.it.group151004.sakovsky.lesson11.TaskA.DFS;

@SuppressWarnings("all")
public class Lesson11Test {

    @Test(timeout = 2000)
    public void taskA() throws Exception {
        TaskA proc = new TaskA(9);
        proc.setVertexName(0, "A");
        proc.setVertexName(1, "B");
        proc.setVertexName(2, "C");
        proc.setVertexName(3, "D");
        proc.setVertexName(4, "E");
        proc.setVertexName(5, "F");
        proc.setVertexName(6, "G");
        proc.setVertexName(7, "H");
        proc.setVertexName(8, "I");
        proc.addEdge(3, 7);
        proc.addEdge(3, 6);
        proc.addEdge(6, 7);
        proc.addEdge(0, 1);
        proc.addEdge(0, 4);
        proc.addEdge(1, 4);
        proc.addEdge(1, 2);
        proc.addEdge(2, 5);
        proc.addEdge(5, 8);

        DFS dfs = new DFS(proc);

        for (int v : proc.getVerticesInAlphabeticalOrder()) {
            String s1 = proc.getVertexName(v) + ": pre = " + dfs.getPre(v) + ", post = " + dfs.getPost(v);
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

        Map<Character, List<Character>> graphB = new HashMap<>();
        graphB.put('A', List.of('C'));
        graphB.put('B', List.of('C'));
        graphB.put('C', List.of('D', 'E'));
        graphB.put('D', List.of('F'));
        graphB.put('E', List.of('F'));
        graphB.put('F', List.of('G', 'H'));
        graphB.put('G', List.of());
        graphB.put('H', List.of());

        TaskB topologicalSort = new TaskB(graphB);
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

        Map<Character, List<Character>> graphC = new HashMap<>();
        graphC.put('A', List.of('C'));
        graphC.put('B', List.of('C'));
        graphC.put('C', List.of('D', 'E'));
        graphC.put('D', List.of('F'));
        graphC.put('E', List.of('F'));
        graphC.put('F', List.of('G', 'H'));
        graphC.put('G', List.of());
        graphC.put('H', List.of());

        TaskC topologicalSort = new TaskC(graphC);
        topologicalSort.sort();
        boolean ok;

        ok = res.equals(topologicalSort.topologicalOrder);
        assertTrue("Failed", ok);
    }
}