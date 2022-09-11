package by.it.group151004.burbouski.lesson02;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.Test;

public class Lesson2Test {

	@Test(timeout = 1000)
	public void A_VideoRegistrator() {
		A_VideoRegistrator instance = new A_VideoRegistrator();
		double[] events = { 1, 1.1, 1.6, 2.2, 2.4, 2.7, 3.9, 8.1, 9.1, 5.5, 3.7 };
		List<Double> starts = instance.calcStartTimes(events, 1);
		boolean ok = "[1.0, 2.2, 3.7, 5.5, 8.1]".equals(starts.toString());
		assertTrue("slowA failed 1", ok);

		events = new double[] { 0.2 };
		starts = instance.calcStartTimes(events, 1);
		ok = "[0.2]".equals(starts.toString());
		assertTrue("slowA failed 2", ok);

		events = new double[] { 2.5, 5.1, 4.6, 6.4, 5.0, 6.3, 7.2, 2.1, 2.3, 7.8, 10.4, 1.9, 5.6, 5.4, 6.5 };
		starts = instance.calcStartTimes(events, 1);
		ok = "[1.9, 4.6, 6.3, 7.8, 10.4]".equals(starts.toString());
		assertTrue("slowA failed 3", ok);

		events = new double[] { 2.5, 5.1, 4.6, 6.4, 5.0, 6.3, 7.2, 2.1, 2.3, 7.8, 10.4, 1.9, 5.6, 5.4, 6.5 };
		starts = instance.calcStartTimes(events, 5);
		ok = "[1.9, 7.2]".equals(starts.toString());
		assertTrue("slowA failed 4", ok);
	}

	@Test(timeout = 250)
	public void B_Sheduler() {
		B_Sheduler instance = new B_Sheduler();
		B_Sheduler.Event[] events = { new B_Sheduler.Event(0, 3), new B_Sheduler.Event(0, 1), new B_Sheduler.Event(1, 2), new B_Sheduler.Event(3, 5), new B_Sheduler.Event(1, 3), new B_Sheduler.Event(1, 3), new B_Sheduler.Event(1, 3), new B_Sheduler.Event(3, 6), new B_Sheduler.Event(2, 7), new B_Sheduler.Event(2, 3), new B_Sheduler.Event(2, 7), new B_Sheduler.Event(7, 9), new B_Sheduler.Event(3, 5), new B_Sheduler.Event(2, 4), new B_Sheduler.Event(2, 3), new B_Sheduler.Event(3, 7), new B_Sheduler.Event(4, 5), new B_Sheduler.Event(6, 7), new B_Sheduler.Event(6, 9), new B_Sheduler.Event(7, 9), new B_Sheduler.Event(8, 9), new B_Sheduler.Event(4, 6), new B_Sheduler.Event(8, 10), new B_Sheduler.Event(7, 10) };

		List<B_Sheduler.Event> starts = instance.calcStartTimes(events, 0, 10);
		boolean ok = "[(0:1), (1:2), (2:3), (3:5), (6:7), (7:9)]".equals(starts.toString());
		assertTrue("B_Sheduler failed 1", ok);

		starts = instance.calcStartTimes(events, 1, 3);
		ok = "[(1:2), (2:3)]".equals(starts.toString());
		assertTrue("B_Sheduler failed 2", ok);

		events = new B_Sheduler.Event[] { new B_Sheduler.Event(0, 5) };
		starts = instance.calcStartTimes(events, 1, 3);
		ok = "[]".equals(starts.toString());
		assertTrue("B_Sheduler failed 3", ok);

		starts = instance.calcStartTimes(events, 0, 4);
		ok = "[]".equals(starts.toString());
		assertTrue("B_Sheduler failed 4", ok);
	}

    @Test
    public void C_GreedyKnapsack() throws Exception {
        String root=System.getProperty("user.dir")+"/src/";
        File f=new File(root+"by/it/a_khmelev/lesson02/greedyKnapsack.txt");
        double costFinal=new C_GreedyKnapsack().calc(f);
        boolean ok=costFinal==200;
        assertTrue("B_Sheduler failed", ok);
    }
}