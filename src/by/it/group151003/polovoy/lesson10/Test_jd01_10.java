package by.it.group151003.polovoy.lesson10;


import by.it.HomeWork;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("all")

//поставьте курсор на следующую строку и нажмите Ctrl+Shift+F10
public class Test_jd01_10 extends HomeWork {

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
        List<Method> methodsE = fill(e.getClass().getMethods(), methodNames);
        List<Method> methodsA = fill(aclass.getMethods(), methodNames);
        int seed = 1234;
        Random rnd = new Random(seed);
        for (int testNumber = 0; testNumber < seed; testNumber++) {
            Integer value = rnd.nextInt(10);
            for (int i = 0; i <= value % 10; i++) {
                a.add(value + i*value);
                e.add(value + i*value);
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

    private static List<Method> fill(Method[] e, TreeSet<String> methodNames) {
        return Arrays.stream(e)
                .filter(m -> methodNames.contains(m.getName()))
                .sorted((m1, m2) -> m1.getName().compareTo(m2.getName()))
                .toList();
    }
}