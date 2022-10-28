package by.it.group151004.burbouski.lesson07;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Lesson7Test {
	@Test
	public void A() throws Exception {
		A_EditDist instance = new A_EditDist();
		assertEquals("A1 failed", instance.getDistanceEdinting("ab", "ab"), 0);
		assertEquals("A2 failed", instance.getDistanceEdinting("short", "ports"), 3);
		assertEquals("A3 failed", instance.getDistanceEdinting("distance", "editing"), 5);
	}

	@Test
	public void B() throws Exception {
		B_EditDist instance = new B_EditDist();
		assertEquals("B1 failed", instance.getDistanceEdinting("ab", "ab"), 0);
		assertEquals("B2 failed", instance.getDistanceEdinting("short", "ports"), 3);
		assertEquals("B3 failed", instance.getDistanceEdinting("distance", "editing"), 5);
	}

	@Test
	public void C() throws Exception {
		C_EditDist instance = new C_EditDist();
		assertEquals("C1 failed", instance.getDistanceEdinting("ab", "ab"), "#,#,");
		assertEquals("C2 failed", instance.getDistanceEdinting("short", "ports").split("#").length, 4);
		assertEquals("C3 failed", instance.getDistanceEdinting("distance", "editing").split("#").length, 5);
	}
}