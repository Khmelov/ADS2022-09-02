package lesson09;


import by.it.HomeWork;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@SuppressWarnings("all")

//поставьте курсор на следующую строку и нажмите Ctrl+Shift+F10
public class Test_jd01_11 extends HomeWork {


    @Test(timeout = 1500)
    public void testTaskA__ListA() throws Exception {
        System.out.println("\nA. Диагностика обязательных к реализации методов:");
        Class<?> aclass = findClass("ListA");
        ListA<Integer> a = (ListA<Integer>) aclass.getDeclaredConstructor().newInstance();
        ListA<Integer> e = new ListA<>();
        for (int i = 0; i < 10; i++) {
            e.add(i * 2);
            a.add(i * 2);
            assertEquals("ошибка add или toString() не работает", e.toString(), a.toString());
        }
        assertEquals("метод add(T e) или toString не работает", e.toString(), a.toString());
        System.out.println("add(T e) toString() ok: " + a);
        System.out.flush();

        assertEquals("ошибка remove(int index) не работает", e.remove(0), a.remove(0));
        assertEquals("ошибка remove(int index) или toString() не работает", e.toString(), a.toString());

        assertEquals("ошибка remove(int index) не работает", e.remove(8), a.remove(8));
        assertEquals("ошибка remove(int index) или toString() не работает", e.toString(), a.toString());

        assertEquals("ошибка remove(int index) не работает", e.remove(4), a.remove(4));
        assertEquals("ошибка remove(int index) или toString() не работает", e.toString(), a.toString());
        System.out.println("  remove(int index) ok: " + a);
        System.out.flush();

        assertEquals("ошибка get(int index) не работает", e.get(6), a.get(6));
        assertEquals("ошибка get(int index) не работает", e.get(0), a.get(0));
        System.out.println("     get(int index) ok: " + a);
        System.out.flush();

        assertEquals("ошибка проверки индексов", e.remove(11), -1);
        System.out.println((" проверка прошла успешно для индекса 11"));
        System.out.flush();
    }

    @Test(timeout = 1500)
    public void testTaskB__ListB() throws Exception {
        System.out.println("\nB. Диагностика обязательных к реализации методов:");
        Class<?> aclass = findClass("ListB");
        ListB<Long> a = (ListB<Long>) aclass.getDeclaredConstructor().newInstance();
        ListB<Long> e = new ListB<>();
        for (int i = 0; i < 10; i++) {
            e.add(i * 2L);
            a.add(i * 2L);
            assertEquals("ошибка add или toString() не работает", e.toString(), a.toString());
        }
        assertEquals("метод add(T e) или toString не работает", e.toString(), a.toString());
        System.out.println("add(T e) toString() ok: " + a);
        System.out.flush();

        assertEquals("ошибка remove(int index) не работает", e.remove(0), a.remove(0));
        assertEquals("ошибка remove(int index) или toString() не работает", e.toString(), a.toString());

        assertEquals("ошибка remove(int index) не работает", e.remove(8), a.remove(8));
        assertEquals("ошибка remove(int index) или toString() не работает", e.toString(), a.toString());

        assertEquals("ошибка remove(int index) не работает", e.remove(4), a.remove(4));
        assertEquals("ошибка remove(int index) или toString() не работает", e.toString(), a.toString());
        System.out.println("  remove(int index) ok: " + a);
        System.out.flush();

        assertEquals("ошибка get(int index) не работает", e.get(6), a.get(6));
        assertEquals("ошибка get(int index) не работает", e.get(0), a.get(0));
        System.out.println("     get(int index) ok: " + a);
        System.out.flush();

        assertEquals("ошибка set(int index, T e) не работает", e.set(0, 0L), a.set(0, 0L));
        assertEquals("ошибка set(int index, T e) или toString() не работает", e.toString(), a.toString());

        assertEquals("ошибка set(int index, T e) не работает", e.set(6, null), a.set(6, null));
        assertEquals("ошибка set(int index, T e) или toString() не работает", e.toString(), a.toString());
        System.out.println("     set(int index) ok: " + a);
        System.out.flush();

        e.add(0, null);
        a.add(0, null);
        assertEquals("ошибка add(int index, T e)", e.toString(), a.toString());

        e.add(4, 444L);
        a.add(4, 444L);
        assertEquals("ошибка add(int index, T e)", e.toString(), a.toString());

        e.add(9, 999L);
        a.add(9, 999L);
        assertEquals("ошибка add(int index, T e)", e.toString(), a.toString());
        System.out.println("add(int index, T e) ok: " + a);
        System.out.flush();

        List<Long> added = new ArrayList<>();
        added.add(1000L);
        added.add(1001L);
        added.add(1002L);
        e.addAll(added);
        a.addAll(added);
        assertEquals("ошибка addAll", e.toString(), a.toString());
        System.out.println("  addAll(List<?> c) ok: " + a);
        System.out.flush();

        System.out.println("\nB. Диагностика необязательных к реализации методов:");
        if (e.contains(null) != a.contains(null) ||
                e.contains((long)0) != a.contains((long)0) ||
                e.contains((long)1002) != a.contains((long)1002)
                )
            System.out.println("Диагностика: contains не реализован или работает не так как в ArrayList");
        else
            System.out.println("      contains(T e) ok: " + a);

        if (e.size() != a.size())
            System.out.println("Диагностика: size не реализован или работает не так как в ArrayList");
        else
            System.out.println("             size() ok: " + a);

        if (e.remove(Integer.valueOf(444)) != a.remove(Integer.valueOf(444)))
            System.out.println("Диагностика: remove(Object o) не реализован или работает не так как в ArrayList");
        else
            System.out.println("   remove(Object o) ok: " + a);
    }

    private void assertSet(String message, Set<Short> e, Set<Short> a){
        List<Short> arr=new ArrayList<>(e);
        boolean ok=true;
        if (e.size()!=a.size())
            fail(message+" e.size()!=a.size()");
        for (Short i : arr) {
            if (!a.contains(i)) {
                fail(message+"\n+!a.contains("+i+")\nexpected:"+e.toString()+")\nactual:"+a.toString());
            }
        }
    };

}