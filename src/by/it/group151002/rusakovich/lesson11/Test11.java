package by.it.group151002.rusakovich.lesson11;
import org.junit.Test;
import java.util.Arrays;
import static org.junit.Assert.assertEquals;

public class Test11 {
    @Test
    public void TestA(){
        DirectedGraph<Character> graph = new DirectedGraph<>(9);
        graph.addNode(0, 'A');
        graph.addNode(1, 'B');
        graph.addNode(2, 'C');
        graph.addNode(3, 'D');
        graph.addNode(4, 'E');
        graph.addNode(5, 'F');
        graph.addNode(6, 'G');
        graph.addNode(7, 'H');
        graph.addNode(8, 'I');


        graph.addTwoWayIncident(0, 1, 1, 1);
        graph.addTwoWayIncident(0, 4, 1, 1);
        graph.addTwoWayIncident(1, 2, 1, 1);
        graph.addTwoWayIncident(1, 4, 1, 1);
        graph.addTwoWayIncident(2, 5, 1, 1);
        graph.addTwoWayIncident(4, 5, 1, 1);
        graph.addTwoWayIncident(5, 8, 1, 1);
        graph.addTwoWayIncident(3, 6, 1, 1);
        graph.addTwoWayIncident(6, 7, 1, 1);

        final String expectedDFS = "A B C F E I D G H ";
        final String expectedPre = "[1, 2, 3, 13, 5, 4, 14, 15, 7]";
        final String expectedPost = "[12, 11, 10, 18, 6, 9, 17, 16, 8]";
        assertEquals(expectedDFS, graph.DFS(0));
        assertEquals(expectedPost, Arrays.toString(graph.getPost()));
        assertEquals(expectedPre, Arrays.toString(graph.getPre()));
    }

    @Test
    public void TestB(){
        DirectedGraph<Character> graph = new DirectedGraph<>(8);
        graph.addNode(0, 'A');
        graph.addNode(1, 'B');
        graph.addNode(2, 'C');
        graph.addNode(3, 'D');
        graph.addNode(4, 'E');
        graph.addNode(5, 'F');
        graph.addNode(6, 'G');
        graph.addNode(7, 'H');

        graph.addIncident(0, 2, 1);
        graph.addIncident(1, 2, 1);
        graph.addIncident(2, 3, 1);
        graph.addIncident(2, 4, 1);
        graph.addIncident(3, 5, 1);
        graph.addIncident(4, 5, 1);
        graph.addIncident(5, 6, 1);
        graph.addIncident(5, 7, 1);

        final String expectedOrder = "[B, A, C, E, D, F, H, G]";
        assertEquals(expectedOrder, Arrays.toString(graph.getTopologicalOrder(0)));
    }

    @Test
    public void TestC(){
        DirectedGraph<Character> graph = new DirectedGraph<>(8);

        graph.addNode(0, 'A');
        graph.addNode(1, 'B');
        graph.addNode(2, 'C');
        graph.addNode(3, 'D');
        graph.addNode(4, 'E');
        graph.addNode(5, 'F');
        graph.addNode(6, 'G');
        graph.addNode(7, 'H');

        graph.addIncident(0, 2, 1);
        graph.addIncident(1, 2, 1);
        graph.addIncident(2, 3, 1);
        graph.addIncident(2, 4, 1);
        graph.addIncident(3, 5, 1);
        graph.addIncident(4, 5, 1);
        graph.addIncident(5, 6, 1);
        graph.addIncident(5, 7, 1);


        //Linerisation
        assertEquals("[B, A, C, E, D, F, H, G]", Arrays.toString(graph.getTopologicalOrder(0)));
        //Sources
        assertEquals("[A, B]", Arrays.toString(graph.findSources()));
        //Sinks
        assertEquals("[G, H]", Arrays.toString(graph.findSinks()));
        //Pre
        assertEquals("[1, 15, 2, 3, 11, 4, 5, 7]", Arrays.toString(graph.getPre()));
        //Post
        assertEquals("[14, 16, 13, 10, 12, 9, 6, 8]", Arrays.toString(graph.getPost()));
        assertEquals(4, graph.getNumberOfLinerisation());


    }
}
