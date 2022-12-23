package by.it.group151004.terebey.lesson11;

import java.util.*;

public class TaskB {
    public Map<Character, List<Character>> graph;
    public List<Character> topologicalOrder;
    public boolean[] visited;

    public TaskB(Map<Character, List<Character>> graph) {
        this.graph = graph;
        topologicalOrder = new ArrayList<>();
        visited = new boolean[graph.size()];
    }

    public void sort() {
        for (Character vertex : graph.keySet()) {
            if (!visited[vertex - 'A']) {
                dfs(vertex);
            }
        }
        Collections.reverse(topologicalOrder);
    }

    public void dfs(Character vertex) {
        visited[vertex - 'A'] = true;
        for (Character neighbor : graph.get(vertex)) {
            if (!visited[neighbor - 'A']) {
                dfs(neighbor);
            }
        }
        topologicalOrder.add(vertex);
    }

    public static void main(String[] args) {
        Map<Character, List<Character>> graph = new HashMap<>();
        graph.put('A', List.of('C'));
        graph.put('B', List.of('C'));
        graph.put('C', List.of('D', 'E'));
        graph.put('D', List.of('F'));
        graph.put('E', List.of('F'));
        graph.put('F', List.of('G', 'H'));
        graph.put('G', List.of());
        graph.put('H', List.of());

        TaskB topologicalSort = new TaskB(graph);
        topologicalSort.sort();
        System.out.println(topologicalSort.topologicalOrder);
    }
}
