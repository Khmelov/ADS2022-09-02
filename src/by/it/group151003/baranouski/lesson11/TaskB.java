package by.it.group151003.baranouski.lesson11;

import java.util.*;

public class TaskB {
    public Map<Character, List<Character>> m_graph;
    public List<Character> m_topologicalOrder;
    public boolean[] m_visited;

    public TaskB(Map<Character, List<Character>> graph) {
        this.m_graph = graph;
        m_topologicalOrder = new ArrayList<>();
        m_visited = new boolean[graph.size()];
    }

    public void sort() {
        for (Character vertex : m_graph.keySet()) {
            if (!m_visited[vertex - 'A']) {
                dfs(vertex);
            }
        }
        Collections.reverse(m_topologicalOrder);
    }

    public void dfs(Character vertex) {
        m_visited[vertex - 'A'] = true;
        for (Character neighbor : m_graph.get(vertex)) {
            if (!m_visited[neighbor - 'A']) {
                dfs(neighbor);
            }
        }
        m_topologicalOrder.add(vertex);
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
        System.out.println(topologicalSort.m_topologicalOrder);
    }
}