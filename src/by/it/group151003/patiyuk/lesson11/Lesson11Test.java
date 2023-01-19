package by.it.group151003.patiyuk.lesson11;

import org.junit.Assert;

import static org.junit.Assert.assertArrayEquals;

public class Lesson11Test {
    @org.junit.Test
    public void testTaskA() throws Exception {
        UndirectedGraph graph = new UndirectedGraph(9);
        graph.loadGraph("graphA.txt");
        TaskA.DFS dfs = new TaskA.DFS(graph);
        int[] pre = dfs.getPre();
        int[] post = dfs.getPost();

        assertArrayEquals(new int[]{0, 1, 2, 12, 4, 3, 13, 14, 6}, pre);
        assertArrayEquals(new int[]{11, 10, 9, 17, 5, 8, 16, 15, 7}, post);

        String expected = "A - B (tree edge) B - A (back edge) B - C (tree edge) C - B (back edge) C - F (tree edge) F - C (back edge) F - E (tree edge) E - A (back edge) E - B (back edge) E - F (back edge) F - I (tree edge) I - F (back edge) B - E (back edge) A - E (back edge) D - G (tree edge) G - D (back edge) G - H (tree edge) H - D (back edge) H - G (back edge) D - H (back edge) ";
        Assert.assertEquals(expected, dfs.getStr());
    }

    @org.junit.Test
    public void testTaskB() throws Exception {
        TaskB.Graph graph = new TaskB.Graph(8);
        graph.addEdge('A', 'C');
        graph.addEdge('B', 'C');
        graph.addEdge('C', 'D');
        graph.addEdge('C', 'E');
        graph.addEdge('D', 'F');
        graph.addEdge('E', 'F');
        graph.addEdge('F', 'G');
        graph.addEdge('F', 'H');
        graph.sort();

        String expected = "[B, A, C, E, D, F, H, G]";
        Assert.assertEquals(expected, graph.getOrder());
    }

    @org.junit.Test
    public void testTaskC() throws Exception {
        TaskC.Graph graph = new TaskC.Graph(8);
        graph.addEdge('A', 'C');
        graph.addEdge('B', 'C');
        graph.addEdge('C', 'D');
        graph.addEdge('C', 'E');
        graph.addEdge('D', 'F');
        graph.addEdge('E', 'F');
        graph.addEdge('F', 'G');
        graph.addEdge('F', 'H');
        graph.sort();
        graph.findSourcesAndSinks();

        String expected = "[B, A, C, E, D, F, H, G]";
        Assert.assertEquals(expected, graph.getOrder());

        expected = "[A, B]";
        Assert.assertEquals(expected, graph.getSources());
        expected = "[G, H]";
        Assert.assertEquals(expected, graph.getSinks());
        Assert.assertEquals(8, graph.getLinearizationsCount());

        expected = "{A=1, B=15, C=2, D=3, E=11, F=4, G=5, H=7}";
        Assert.assertEquals(expected, graph.getPreValues());
        expected = "{A=14, B=16, C=13, D=10, E=12, F=9, G=6, H=8}";
        Assert.assertEquals(expected, graph.getPostValues());
    }
}
