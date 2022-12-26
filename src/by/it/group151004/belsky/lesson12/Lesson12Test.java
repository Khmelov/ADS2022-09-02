package by.it.group151004.belsky.lesson12;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class Lesson12Test {
    @Test
    public void A() throws Exception {
        HashMap<Vertex, Float> map = TaskA.getRoutes("by/it/group151004/belsky/lesson12/graphA.txt", "A");
        HashMap<String, Float> nameMap = new HashMap<>();
        for (Vertex v : map.keySet()) {
            nameMap.put(v.getName(), map.get(v));
        }

        assertEquals("A failed", map.size(), 8);
        assertEquals("A failed", nameMap.get("A").toString(), "0.0");
        assertEquals("A failed", nameMap.get("B").toString(), "1.0");
        assertEquals("A failed", nameMap.get("D").toString(), "4.0");
    }

    @Test
    public void B() throws Exception {
        HashMap<Vertex, Float> map = TaskB.getRoutes("by/it/group151004/belsky/lesson12/graphB.txt", "A");

        HashMap<String, Float> nameMap = new HashMap<>();
        for (Vertex v : map.keySet()) {
            nameMap.put(v.getName(), map.get(v));
        }
        assertEquals("B failed", map.size(), 10);
        assertEquals("B failed", nameMap.get("A").toString(), "0.0");
        assertEquals("B failed", nameMap.get("D").toString(), "0.0");
        assertEquals("B failed", nameMap.get("S").toString(), "Infinity");
    }

    @Test
    public void C() throws Exception {
        float len = TaskC.getLen("by/it/group151004/belsky/lesson12/graphC.txt");
        assertEquals("C failed", (int) len, 16);
    }

}
