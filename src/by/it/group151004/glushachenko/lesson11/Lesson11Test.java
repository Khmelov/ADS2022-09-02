package by.it.group151004.glushachenko.lesson11;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


@SuppressWarnings("all")
public class Lesson11Test {

    @Test(timeout = 2000)
    public void taskA() throws Exception {
        TaskA taskA = new TaskA(9);
        taskA.setName(0, "A");
        taskA.setName(1, "B");
        taskA.setName(2, "C");
        taskA.setName(3, "D");
        taskA.setName(4, "E");
        taskA.setName(5, "F");
        taskA.setName(6, "G");
        taskA.setName(7, "H");
        taskA.setName(8, "I");
        taskA.add(3, 7);
        taskA.add(3, 6);
        taskA.add(6, 7);
        taskA.add(0, 1);
        taskA.add(0, 4);
        taskA.add(1, 4);
        taskA.add(1, 2);
        taskA.add(2, 5);
        taskA.add(5, 8);
        taskA.add(4, 5);

        TaskA.DFS dfs = new TaskA.DFS(taskA);

        int[] preExpected = {0,1,2,12,4,3,13,14,6};
        int[] postExpected = {11,10,9,17,5,8,16,15,7};
        for (int v : taskA.getVerticesByAlphabet()) {
            System.out.println(taskA.getName(v) + ": pre = " + dfs.getPre(v) + ", post = " + dfs.getPost(v));
            assertEquals("Failed",preExpected[v],dfs.getPre(v));
            assertEquals("Failed",postExpected[v],dfs.getPost(v));
        }
        String expected = "A - B (tree edge) B - A (back edge) B - C (tree edge) C - B (back edge) C - F (tree edge) F - C (back edge) F - E (tree edge) E - A (back edge) E - B (back edge) E - F (back edge) F - I (tree edge) I - F (back edge) B - E (back edge) A - E (back edge) D - G (tree edge) G - D (back edge) G - H (tree edge) H - D (back edge) H - G (back edge) D - H (back edge) ";

        assertEquals("Failed", expected, dfs.getString());
    }

    @Test(timeout = 2000)
    public void taskB() {
        Map<Character, List<Character>> graph = new HashMap<>();
        graph.put('A', List.of('C'));
        graph.put('B', List.of('C'));
        graph.put('C', List.of('D', 'E'));
        graph.put('D', List.of('F'));
        graph.put('E', List.of('F'));
        graph.put('F', List.of('G', 'H'));
        graph.put('G', List.of());
        graph.put('H', List.of());

        TaskB taskB = new TaskB(graph);
        taskB.sort();
        String expected = "[B, A, C, E, D, F, H, G]";
        System.out.println(taskB.getOrder());
        assertEquals("Failed", expected, taskB.getOrder());
    }
    @Test(timeout = 2000)
    public void taskC() {
        Map<Character, List<Character>> graph = new HashMap<>();
        graph.put('A', List.of('C'));
        graph.put('B', List.of('C'));
        graph.put('C', List.of('D', 'E'));
        graph.put('D', List.of('F'));
        graph.put('E', List.of('F'));
        graph.put('F', List.of('G', 'H'));
        graph.put('G', List.of());
        graph.put('H', List.of());

        TaskC taskC = new TaskC(graph);
        taskC.sort();
        taskC.findSourcesAndSinks();
        System.out.println(taskC.getSources());
        System.out.println(taskC.getSinks());
        System.out.println("Linearizations: " + taskC.getLins());
        System.out.println("Pre values: " + taskC.getPreValues());
        System.out.println("Post values: " + taskC.getPostValues());
        System.out.println("Topological order: " + taskC.getTopologicalOrder());

        String expectedSources = new String("[A, B]");
        String expectedSinks = new String("[G, H]");
        String expectedPre = "{A=1, B=15, C=2, D=3, E=11, F=4, G=5, H=7}";
        String expectedPost = "{A=14, B=16, C=13, D=10, E=12, F=9, G=6, H=8}";
        String expectedTop = "[B, A, C, E, D, F, H, G]";
        int expectedLin = 8;
        assertEquals("Failed sources", expectedSources, taskC.getSources());
        System.out.println("Success sourses");
        assertEquals("Failed sinks", expectedSinks, taskC.getSinks());
        System.out.println("Success sinks");
        assertEquals("Failed Linearizations", expectedLin, taskC.getLins());
        System.out.println("Success Linearizations");
        assertEquals("Failed Pre values", expectedPre, taskC.getPreValues());
        System.out.println("Success Pre values");
        assertEquals("Failed Post values", expectedPost, taskC.getPostValues());
        System.out.println("Success Post values");
        assertEquals("Failed Topological order", expectedTop, taskC.getTopologicalOrder());
        System.out.println("Success Topological order");

    }
}
