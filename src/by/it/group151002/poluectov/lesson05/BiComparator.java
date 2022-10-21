package by.it.group151002.poluectov.lesson05;

@FunctionalInterface
public interface BiComparator<T, U> {
    int accept(T t, U u);
}
