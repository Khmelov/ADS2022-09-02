package by.it.group151001.kokarev.lesson07;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Lesson7Test {
    @Test
    public void A() throws Exception {
        A_EditDist instance = new A_EditDist();
        assertEquals("A1 failed", instance.getDistanceEdinting("ab","ab"),0);
        assertEquals("A2 failed", instance.getDistanceEdinting("short","ports"),3);
        assertEquals("A3 failed", instance.getDistanceEdinting("distance","editing"),5);
    }
}
