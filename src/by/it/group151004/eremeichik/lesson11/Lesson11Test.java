package by.it.group151004.eremeichik.lesson11;

import org.junit.Test;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Lesson11Test {
    @Test
    public void testA(){
        Graph g = new Graph(9);
        g.setVertexNameByIndex(0, 'A');
        g.setVertexNameByIndex(1, 'B');
        g.setVertexNameByIndex(2, 'C');
        g.setVertexNameByIndex(3, 'D');
        g.setVertexNameByIndex(4, 'E');
        g.setVertexNameByIndex(5, 'F');
        g.setVertexNameByIndex(6, 'G');
        g.setVertexNameByIndex(7, 'H');
        g.setVertexNameByIndex(8, 'I');
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

        String expected = "A - B (tree) B - A (back) B - C (tree) C - B (back) C - F (tree) F - C (back) F - E (tree) E - A (back) E - B (back) E - F (back) F - I (tree) I - F (back) \n" +
                "D - G (tree) G - D (back) G - H (tree) H - D (back) H - G (back) \n";
        String actual = TaskA.deepFirstSearch(g).toString();

        assertEquals(expected,actual);
    }

    @Test
    public void testB(){
        Graph g = new Graph(8);
        g.setVertexNameByIndex(0, 'A');
        g.setVertexNameByIndex(1, 'B');
        g.setVertexNameByIndex(2, 'C');
        g.setVertexNameByIndex(3, 'D');
        g.setVertexNameByIndex(4, 'E');
        g.setVertexNameByIndex(5, 'F');
        g.setVertexNameByIndex(6, 'G');
        g.setVertexNameByIndex(7, 'H');
        g.addOrEdge(0, 2);
        g.addOrEdge(1, 2);
        g.addOrEdge(2, 3);
        g.addOrEdge(2, 4);
        g.addOrEdge(3, 5);
        g.addOrEdge(4, 5);
        g.addOrEdge(5, 6);
        g.addOrEdge(5, 7);


        List<Character> expected = List.of('B','A','C','E','D','F','H','G');
        List<Character> actual = TaskB.sort(g);

        assertEquals(expected,actual);
    }

    @Test
    public void testC(){
        Graph g = new Graph(8);
        g.setVertexNameByIndex(0, 'A');
        g.setVertexNameByIndex(1, 'B');
        g.setVertexNameByIndex(2, 'C');
        g.setVertexNameByIndex(3, 'D');
        g.setVertexNameByIndex(4, 'E');
        g.setVertexNameByIndex(5, 'F');
        g.setVertexNameByIndex(6, 'G');
        g.setVertexNameByIndex(7, 'H');
        g.addOrEdge(0, 2);
        g.addOrEdge(1, 2);
        g.addOrEdge(2, 3);
        g.addOrEdge(2, 4);
        g.addOrEdge(3, 5);
        g.addOrEdge(4, 5);
        g.addOrEdge(5, 6);
        g.addOrEdge(5, 7);
        TaskC.sort(g);
        TaskC.sinkAndSources(g);
        TaskC.degree = new int[g.getVertexCount()];
        for (int i = 0; i < g.getVertexCount(); i++) {
            TaskC.visitedVertecies[i] = false;
            for (int j: g.graph.get(i))
                TaskC.degree[j]++;
        }

        int expected  = 8;
        int actual = TaskC.calculateLinNum(g);

        assertEquals(expected,actual);
    }
}
