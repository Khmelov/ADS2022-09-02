package by.it.group151002.kragel.lesson11;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Lesson11Test {
    private void assertEqualsAndPrint(String message, Object expected, Object actual){
        System.out.println(actual);
        assertEquals(message, expected, actual);
    }
    @Test
    public void A() throws Exception {
        GraphA gA = new GraphA();
        gA.addEdge("A", "B");
        gA.addEdge("A", "E");
        gA.addEdge("B", "E");
        gA.addEdge("B", "C");
        gA.addEdge("E", "F");
        gA.addEdge("C", "F");
        gA.addEdge("F", "I");
        gA.addEdge("D", "G");
        gA.addEdge("D", "H");
        gA.addEdge("G","H");
        assertEqualsAndPrint("dfs failed", "[A, B, C, F, E, I, D, G, H]", gA.dfsToString());
        assertEqualsAndPrint("Pre failed", "[0, 1, 2, 12, 4, 3, 13, 14, 6]", gA.getPres().values().toString());
        assertEqualsAndPrint("Post failed", "[11, 10, 9, 17, 5, 8, 16, 15, 7]", gA.getPosts().values().toString());
        assertEqualsAndPrint("Trees failed", "[A-B, B-C, C-F, F-E, F-I, D-G, G-H]", gA.getTrees().toString());
        assertEqualsAndPrint("Backs failed", "[E-A, E-B, H-D]", gA.getBacks().toString());
    }
    @Test
    public void B() throws Exception {
        GraphBC gB = new GraphBC();
        gB.addOrientEdge("A", "C");
        gB.addOrientEdge("B", "C");
        gB.addOrientEdge("C", "D");
        gB.addOrientEdge("C", "E");
        gB.addOrientEdge("D", "F");
        gB.addOrientEdge("E", "F");
        gB.addOrientEdge("F", "G");
        gB.addOrientEdge("F", "H");
        assertEqualsAndPrint("Topological failed", "[B, A, C, E, D, F, H, G]", gB.getTopological().toString());
    }
    @Test
    public void C() throws Exception {
        GraphBC gC = new GraphBC();
        gC.addOrientEdge("A", "C");
        gC.addOrientEdge("B", "C");
        gC.addOrientEdge("C", "D");
        gC.addOrientEdge("C", "E");
        gC.addOrientEdge("D", "F");
        gC.addOrientEdge("E", "F");
        gC.addOrientEdge("F", "G");
        gC.addOrientEdge("F", "H");
        assertEqualsAndPrint("Pre failed", "[0, 14, 1, 2, 10, 3, 4, 6]", gC.getPres().values().toString());
        assertEqualsAndPrint("Post failed", "[13, 15, 12, 9, 11, 8, 5, 7]", gC.getPosts().values().toString());
        assertEqualsAndPrint("All starts failed", "[A, B]", gC.findStarts().toString());
        assertEqualsAndPrint("All ends failed", "[G, H]", gC.findEnds().toString());
        assertEqualsAndPrint("Topological failed", "[B, A, C, E, D, F, H, G]", gC.getTopological().toString());
        assertEqualsAndPrint("Count linear failed", 8, gC.getLinear());
    }
}
