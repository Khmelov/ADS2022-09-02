package by.it.group151001.maliauka.lesson05;

@FunctionalInterface
public interface BiComparator<T, U> {
    int accept(T t, U u);
}
