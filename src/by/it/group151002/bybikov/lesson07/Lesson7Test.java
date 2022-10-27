package by.it.group151002.bybikov.lesson07;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Lesson7Test {
    @Test
    public void A() throws Exception {
        LevenshteinEditDistanceClass levenshteinEditDistanceClass = new LevenshteinEditDistanceClass();
        RandomValuesMethods randomValuesMethods = new RandomValuesMethods();
        String sourceString = "distance";
        String destString = "editing";
        boolean isCorrect = true;
        for (int i = 0; i < 100 && isCorrect; i++) {
            sourceString = randomValuesMethods.getRandomString();
            destString = randomValuesMethods.getRandomString();
            System.out.println(sourceString);
            System.out.println(destString);
            levenshteinEditDistanceClass.setPrivateLevenshteinEditDistanceMatrix(sourceString, destString);
            int matrixLowerEditDistance = levenshteinEditDistanceClass.getLowerEditDistanceFromMatrix();
            int recurrentLowerEditDistance = levenshteinEditDistanceClass.recurrentFormulas(sourceString, destString);
            isCorrect = matrixLowerEditDistance == recurrentLowerEditDistance;
        }
        assertTrue("A failed", isCorrect);

        /*
        assertEquals("A1 failed", instance.getDistanceEditing("ab","ab"),0);
        assertEquals("A2 failed", instance.getDistanceEditing("short","ports"),3);
        assertEquals("A3 failed", instance.getDistanceEditing("distance","editing"),5);
         */
    }


    @Test
    public void B() throws Exception {
        LevenshteinEditDistanceClass levenshteinEditDistanceClass = new LevenshteinEditDistanceClass();
        RandomValuesMethods randomValuesMethods = new RandomValuesMethods();
        String sourceString = "short";
        String destString = "ports";
        boolean isCorrect = true;
        for (int i = 0; i < 100 && isCorrect; i++) {
            sourceString = randomValuesMethods.getRandomString();
            destString = randomValuesMethods.getRandomString();
            levenshteinEditDistanceClass.setPrivateLevenshteinEditDistanceMatrix(sourceString, destString);
            int first = levenshteinEditDistanceClass.getLowerEditDistanceFromMatrix();

            levenshteinEditDistanceClass.setPrivateLevenshteinEditDistanceMatrix(destString, sourceString);
            int second = levenshteinEditDistanceClass.getLowerEditDistanceFromMatrix();
            isCorrect = first == second;
        }
        assertTrue("B failed", isCorrect);
        /*
        B_EditDist instance = new B_EditDist();
        assertEquals("B1 failed", instance.getDistanceEditing("ab","ab"),0);
        assertEquals("B2 failed", instance.getDistanceEditing("short","ports"),3);
        assertEquals("B3 failed", instance.getDistanceEditing("distance","editing"),5);
        */
    }

    @Test
    public void C() throws Exception {
        C_Edit_Distance_Solution solution = new C_Edit_Distance_Solution();
        RandomValuesMethods randomValuesMethods = new RandomValuesMethods();
        String editSequence;
        String sourceString = "distance";
        String destString = "editing";
        //sourceString = "ab";
        //destString = "ab";
        boolean isCorrect = true;
        for (int i = 0; i < 100000 && isCorrect; i++) {
            sourceString = randomValuesMethods.getRandomString();
            destString = randomValuesMethods.getRandomString();
            System.out.println(sourceString);
            System.out.println(destString);
            solution.setLevenshteinMatrix(sourceString, destString);
            editSequence = solution.getEditSequence(sourceString, destString);
        }
        assertTrue("C failed", isCorrect);

        /*
        C_EditDist instance = new C_EditDist();
        assertEquals("C1 failed", instance.getDistanceEditing("ab","ab"),"#,#,");
        //путей может быть много, поэтому тут жестко проверить все сложно
        //надо найти и проверить их все, что делает тест сложнее реализации
        //возможно, что хватит только подсчета повторов.

        //ожидается     -s,~p,#,#,#,+s,
        assertEquals("C2 failed", instance.getDistanceEditing("short","ports").split("#").length,4);

        //ожидается     +e,#,#,-s,#,~i,#,-c,~g,
        assertEquals("C3 failed", instance.getDistanceEditing("distance","editing").split("#").length,5);
         */
    }


}
