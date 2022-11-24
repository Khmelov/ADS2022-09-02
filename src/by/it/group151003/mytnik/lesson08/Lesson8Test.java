package by.it.group151003.mytnik.lesson08;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Lesson8Test {
    @Test
    public void A() throws Exception {
        A_Knapsack knapsack = new A_Knapsack();
        assertEquals("A_Knapsack failed", 20, knapsack.getMaxWeight(20, new int[]{6, 7, 12, 18}));
        assertEquals("A_Knapsack failed", 9, knapsack.getMaxWeight(9, new int[]{6, 3, 4, 2}));
        assertEquals("A_Knapsack failed", 0, knapsack.getMaxWeight(1, new int[]{2, 3, 4, 5}));
    }

    @Test
    public void B() throws Exception {
        B_Knapsack knapsack = new B_Knapsack();
        assertEquals("B_Knapsack failed", 19, knapsack.getMaxWeight(20, new int[]{5, 7, 12, 18}));
        assertEquals("B_Knapsack failed", 9, knapsack.getMaxWeight(9, new int[]{6, 3, 4, 2}));
        assertEquals("B_Knapsack failed", 0, knapsack.getMaxWeight(1, new int[]{2, 3, 4, 5}));
    }

    @Test
    public void C() throws Exception {
        C_Stairs stairs = new C_Stairs();
        assertEquals("C_Stairs failed", 3, stairs.getMaxSum(new int[]{1, 2}));
        assertEquals("C_Stairs failed", 1, stairs.getMaxSum(new int[]{2, -1}));
        assertEquals("C_Stairs failed", 3, stairs.getMaxSum(new int[]{-1, 2, 1}));
    }
}