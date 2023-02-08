package by.it.group151004.akimenko.lesson11;

import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Test;

import by.it.group151004.akimenko.lesson11.TaskA.DFS;

@SuppressWarnings("all")
public class Lesson11Tests {

    @Test(timeout = 2000)
    public void taskA() throws Exception {
        TaskA aVar = new TaskA(9);
        aVar.setVertexName(0, "A");
        aVar.setVertexName(1, "B");
        aVar.setVertexName(2, "C");
        aVar.setVertexName(3, "D");
        aVar.setVertexName(4, "E");
        aVar.setVertexName(5, "F");
        aVar.setVertexName(6, "G");
        aVar.setVertexName(7, "H");
        aVar.setVertexName(8, "I");
        aVar.addEdge(3, 7);
        aVar.addEdge(3, 6);
        aVar.addEdge(6, 7);
        aVar.addEdge(0, 1);
        aVar.addEdge(0, 4);
        aVar.addEdge(1, 4);
        aVar.addEdge(1, 2);
        aVar.addEdge(2, 5);
        aVar.addEdge(5, 8);

        DFS dfs = new DFS(aVar);

        for (int v : aVar.getVerticesInAlphabeticalOrder()) {
            String s1 = aVar.getVertexName(v) + ": pre = " + dfs.getPre(v) + ", post = " + dfs.getPost(v);
        }

        boolean fl;
        fl = 0 == dfs.getPre(0);
        assertTrue("Failed", fl);

        fl = 1 == dfs.getPre(1);
        assertTrue("Failed", fl);

        fl = 2 == dfs.getPre(2);
        assertTrue("Failed", fl);

        fl = 6 == dfs.getPre(3);
        assertTrue("Failed", fl);

        fl = 5 == dfs.getPre(4);
        assertTrue("Failed", fl);

        fl = 3 == dfs.getPre(5);
        assertTrue("Failed", fl);

        fl = 7 == dfs.getPre(6);
        assertTrue("Failed", fl);

        fl = 8 == dfs.getPre(7);
        assertTrue("Failed", fl);

        fl = 4 == dfs.getPre(8);
        assertTrue("Failed", fl);
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

        boolean fl = res.equals(topologicalSort.topologicalOrder);
        assertTrue("Failed", fl);
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
        boolean fl;

        fl = res.equals(topologicalSort.topologicalOrder);
        assertTrue("Failed", fl);
    }
}
