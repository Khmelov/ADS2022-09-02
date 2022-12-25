package by.it.group151002.rusakovich.lesson10;


import by.it.HomeWork;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.spec.ECField;
import java.util.*;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("all")

//поставьте курсор на следующую строку и нажмите Ctrl+Shift+F10
public class Test_jd01_10 extends HomeWork {


    @Test
    public void myTest() throws Exception{
        TaskA a = new TaskA<Integer>();
        a.add(12);
        a.add(15);
        a.add(18);
        a.add(4);
        a.add(3);
        a.add(15);
        final String res = "[3, 4, 12, 15, 18]";
        assertEquals(res, a.toString());
        boolean flag = a.remove(3);
        assertEquals(true, flag);
        flag = a.remove(1);
        assertEquals(false, flag);
        a.add(null);
        assertEquals(true, a.contains(null));
        a.remove(15);
        assertEquals("[null, 4, 12, 18]", a.toString());
        assertEquals(a.size(), 4);
    }

    @Test
    public void mistakeTest() throws Exception{
        TaskA a = new TaskA<Integer>();
        a.add(8);
        a.add(16);
        a.add(24);
        a.add(32);
        a.add(40);
        a.add(48);
        a.add(56);
        a.add(64);
        a.add(72);
        System.out.println(a.toString());
        a.add(3);
        System.out.println(a.toString());
        a.add(6);
        System.out.println(a.toString());
        a.add(9);
        System.out.println(a.toString());
        a.add(12);
        System.out.println(a.toString());
    }
    @Test
    public void testIterator() throws Exception{
        Integer[] expectedArr = {0,1,2, 3, 4};
        TaskB a = new TaskB<Integer>();
        a.add(0);
        a.add(4);
        a.add(3);
        a.add(2);
        a.add(1);
        int i = 0;
        for(var El:a){
            assertEquals(expectedArr[i], El);
            i++;
        }
    }

    @Test(timeout = 5000)
    public void testTaskA() throws Exception {
        TreeSet<String> methodNames = new TreeSet<>(Arrays.asList(
                "toString", "add", "remove"
        ));
        randomCheck(methodNames, "TaskA");
    }

    @Test(timeout = 5000)
    public void testTaskB() throws Exception {
        TreeSet<String> methodNames = new TreeSet<>(Arrays.asList(
                "toString", "add", "remove",
                "contains", "clear", "isEmpty", "size", "first", "last"
        ));
        randomCheck(methodNames, "TaskB");
    }

    @Test(timeout = 5000)
    public void testTaskC() throws Exception {
        TreeSet<String> methodNames = new TreeSet<>(Arrays.asList(
                "toString", "add", "remove",
                "contains", "clear", "isEmpty", "size", "first", "last",
                "lower", "floor", "ceiling", "higher", "pollFirst", "pollLast"
        ));
        randomCheck(methodNames, "TaskC");
    }

    private void randomCheck(TreeSet<String> methodNames, String className) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        Class<?> aclass = findClass(className);
        assertEquals("Неверное наследование", Object.class, aclass.getSuperclass());
        System.out.println("\nA. Диагностика обязательных к реализации методов:");
        NavigableSet<Integer> e = (NavigableSet<Integer>) TreeSet.class.getDeclaredConstructor().newInstance();
        NavigableSet<Integer> a = (NavigableSet<Integer>) aclass.getDeclaredConstructor().newInstance();
        Field[] fields = a.getClass().getDeclaredFields();
        List<Method> methodsE = fill(e.getClass(), methodNames);
        List<Method> methodsA = fill(aclass, methodNames);
        int seed = 1234;
        Random rnd = new Random(seed);
        for (int testNumber = 0; testNumber < seed; testNumber++) {
            Integer value = rnd.nextInt(10);
            for (int i = 0; i <= value % 10; i++) {
                a.add(value + i * value);
                e.add(value + i * value);
            }
            int mIndex = rnd.nextInt(methodsA.size());
            Method methodE = methodsE.get(mIndex);
            Method methodA = methodsA.get(mIndex);
            int params = methodE.getParameterCount();
            if (params < 2) {
                Object expected = params == 0 ? methodE.invoke(e) : methodE.invoke(e, value);
                Object actual = params == 0 ? methodA.invoke(a) : methodA.invoke(a, value);
                String eString = e.toString();
                String aString = a.toString();
                assertEquals("ошибка сравнения результатов  \n" + methodE + "\n" + methodA, expected, actual);
                assertEquals("ошибка состояния после \n" + methodE + "\n" + methodA, eString, aString);
            }
        }
        System.out.println("OK" + methodNames);
        System.out.println(e);
        System.out.println(a);
    }

    private List<Method> fill(Class<?> c, TreeSet<String> methodNames) {
        return Stream.of(c.getMethods(), c.getDeclaredMethods())
                .flatMap(Arrays::stream)
                .distinct()
                .filter(m -> methodNames.contains(m.getName()))
                .filter(this::notComparable)
                .sorted((m1, m2) -> m1.getName().compareTo(m2.getName()))
                .toList();
    }

    private boolean notComparable(Method m) {
        return m.getReturnType() != Comparable.class &&
                0 == Arrays.stream(m.getParameterTypes())
                        .filter(p -> p == Comparable.class)
                        .count();
    }
}