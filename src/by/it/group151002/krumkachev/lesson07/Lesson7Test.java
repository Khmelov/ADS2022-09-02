package by.it.group151002.krumkachev.lesson07;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Lesson7Test {
    @Test
    public void A() throws Exception {
        A_EditDist instance = new A_EditDist();
        assertEquals("A1 failed", 0, instance.getDistanceEditing("ab","ab"));
        assertEquals("A2 failed", 3, instance.getDistanceEditing("short","ports"));
        assertEquals("A3 failed", 5, instance.getDistanceEditing("distance","editing"));
        assertEquals("A4 failed", 0, instance.getDistanceEditing("",""));
        assertEquals("A5 failed", 5, instance.getDistanceEditing("abcde",""));
        assertEquals("A6 failed", 3, instance.getDistanceEditing("saturday","sunday"));
        assertEquals("A7 failed", 6, instance.getDistanceEditing("polynomial","exponential"));
        assertEquals("A8 failed", 3, instance.getDistanceEditing("kitten","sitting"));
    }


    @Test
    public void B() throws Exception {
        B_EditDist instance = new B_EditDist();
        assertEquals("B1 failed", 0, instance.getDistanceEditing("ab","ab"));
        assertEquals("B2 failed", 3, instance.getDistanceEditing("short","ports"));
        assertEquals("B3 failed", 5, instance.getDistanceEditing("distance","editing"));
        assertEquals("B4 failed", 0, instance.getDistanceEditing("",""));
        assertEquals("B5 failed", 5, instance.getDistanceEditing("abcde",""));
        assertEquals("B6 failed", 3, instance.getDistanceEditing("saturday","sunday"));
        assertEquals("B7 failed", 6, instance.getDistanceEditing("polynomial","exponential"));
        assertEquals("B8 failed", 3, instance.getDistanceEditing("kitten","sitting"));
    }

    @Test
    public void C() throws Exception {
        C_EditDist instance = new C_EditDist();
        assertEquals("C1 failed", instance.getDistanceEditing("ab","ab"),"#,#,");
        //путей может быть много, поэтому тут жестко проверить все сложно
        //надо найти и проверить их все, что делает тест сложнее реализации
        //возможно, что хватит только подсчета повторов.

        //ожидается     -s,~p,#,#,#,+s,
        assertEquals("C2 failed", instance.getDistanceEditing("short","ports").split("#").length,4);
        //ожидается     +e,#,#,-s,#,~i,#,-c,~g,
        assertEquals("C3 failed", instance.getDistanceEditing("distance","editing").split("#").length,5);
        assertEquals("C4 failed", "", instance.getDistanceEditing("",""));
        assertEquals("C5 failed", "-a,-b,-c,-d,-e,", instance.getDistanceEditing("abcde",""));
    }
}
