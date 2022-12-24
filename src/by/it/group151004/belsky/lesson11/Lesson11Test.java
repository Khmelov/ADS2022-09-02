package by.it.group151004.belsky.lesson11;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Lesson11Test {
    @Test
    public void A() throws Exception {
        TaskA.calculate();
        ArrayList<String[]> reverseEdges = TaskA.getReverseResult();
        assertEquals("A failed", 3, reverseEdges.size());
        TreeMap<String, Integer> pre = TaskA.getPreResult();
        TreeMap<String, Integer> post = TaskA.getPostResult();

        assertEquals("A failed", 9, pre.size());
        assertEquals("A failed", 9, post.size());

        assertEquals("A failed", 1, pre.get("A").intValue());
        assertEquals("A failed", 12, post.get("A").intValue());
    }

    @Test
    public void B() throws Exception {
        LinkedList<Vertex> orderedVertices = TaskB.calculate();
        assertEquals("B failed", 8, orderedVertices.size());
        assertEquals("B failed", "B", orderedVertices.get(0).getName());
        assertEquals("B failed", "A", orderedVertices.get(1).getName());

        assertEquals("B failed", "H", orderedVertices.get(6).getName());
        assertEquals("B failed", "G", orderedVertices.get(7).getName());
    }

    @Test
    public void C() throws Exception {
        TaskC.calculate();

        TreeMap<String, Integer> pre = TaskC.getPreResult(), post = TaskC.getPostResult();
        assertEquals("C failed", 8, pre.size());
        assertEquals("C failed", 8, post.size());
        assertEquals("C failed", 1, pre.get("A").intValue());
        assertEquals("C failed", 16, post.get("A").intValue());



        ArrayList<Vertex> sources = TaskC.getSourcesResult(), sinks = TaskC.getSinksResult();
        ArrayList<String> sourcesNames = new ArrayList<>(), sinksNames = new ArrayList<>();
        for (Vertex v : sources) {
            sourcesNames.add(v.getName());
        }
        for (Vertex v : sinks) {
            sinksNames.add(v.getName());
        }

        assertEquals("C failed", 1, sourcesNames.size());
        assertEquals("C failed", 2, sinksNames.size());

        assertTrue("C failed", sourcesNames.contains("A"));

        assertTrue("C failed", sinksNames.contains("H"));
        assertTrue("C failed", sinksNames.contains("F"));

        ArrayList<Vertex> linearList = TaskC.getLinearList();
        ArrayList<String> linearVertexNames = new ArrayList<>();
        for (Vertex v : linearList) {
            linearVertexNames.add(v.getName());
        }
        int linCount = TaskC.getLinCount();

        assertEquals("C failed", 8, linearVertexNames.size());
        assertEquals("C failed", 11, linCount);
    }

}
