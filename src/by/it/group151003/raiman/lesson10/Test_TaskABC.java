package by.it.group151003.mytnik.lesson10;


import by.it.HomeWork;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("all")
public class Test_TaskABC extends HomeWork {

    @Test(timeout = 5000)
    public void testTaskA() throws Exception {
        TreeSet<String> methodNames = new TreeSet<>(Arrays.asList(
                "toString", "add", "remove"
        ));
        randomCheck(methodNames, "TaskABC");
    }

    @Test(timeout = 5000)
    public void testTaskB() throws Exception {
        TreeSet<String> methodNames = new TreeSet<>(Arrays.asList(
                "toString", "add", "remove",
                "contains", "clear", "isEmpty", "size", "first", "last"
        ));
        randomCheck(methodNames, "TaskABC");
    }

    @Test(timeout = 5000)
    public void testTaskC() throws Exception {
        TreeSet<String> methodNames = new TreeSet<>(Arrays.asList(
                "toString", "add", "remove",
                "contains", "clear", "isEmpty", "size", "first", "last",
                "lower", "floor", "ceiling", "higher", "pollFirst", "pollLast"
        ));
        randomCheck(methodNames, "TaskABC");
    }

    private void randomCheck(TreeSet<String> methodNames, String className) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class<?> aclass = findClass(className);
        assertEquals(Object.class, aclass.getSuperclass());
        NavigableSet<Integer> e = (NavigableSet<Integer>) TreeSet.class.getDeclaredConstructor().newInstance();
        NavigableSet<Integer> a = (NavigableSet<Integer>) aclass.getDeclaredConstructor().newInstance();
        int seed = 1234;
        Random rnd = new Random(seed);
        for (int testNumber = 0; testNumber < seed; testNumber++) {
            Integer value = rnd.nextInt(10);
            for (int i = 0; i <= value % 10; i++) {
                a.add(value + i * value);
                e.add(value + i * value);
            }
        }
        System.out.println("OK" + methodNames);
        System.out.println(e);
        System.out.println(a);
    }

    private static List<Method> fill(Method[] e, TreeSet<String> methodNames) {
        return Arrays.stream(e)
                .filter(m -> methodNames.contains(m.getName()))
                .sorted((m1, m2) -> m1.getName().compareTo(m2.getName()))
                .toList();
    }
}