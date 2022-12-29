package by.it.group151003.baranouski.lesson11;

import java.util.*;

public class TaskC {
    public Map<Character, List<Character>> m_graph;
    public Map<Character, Integer> m_preValues;
    public Map<Character, Integer> m_postValues;
    public int m_counter = 1;
    public List<Character> m_topologicalOrder;
    public boolean[] m_visited;
    public int m_srcs;

    public TaskC(Map<Character, List<Character>> graph) {
        this.m_graph = graph;
        this.m_preValues = new HashMap<>();
        this.m_postValues = new HashMap<>();
        m_topologicalOrder = new ArrayList<>();
        m_visited = new boolean[graph.size()];
    }

    public void sort() {
        for (char vertex : m_graph.keySet()) {
            if (!m_visited[vertex - 'A']) {
                dfs(vertex);
            }
        }
        Collections.reverse(m_topologicalOrder);
    }

    public void dfs(Character vertex) {
        m_preValues.put(vertex, m_counter++);
        m_visited[vertex - 'A'] = true;
        for (char neighbor : m_graph.get(vertex)) {
            if (!m_visited[neighbor - 'A']) {
                dfs(neighbor);
            }
        }
        m_topologicalOrder.add(vertex);
        m_postValues.put(vertex, m_counter++);
    }

    public Map<Character, Integer> getM_preValues() {
        return m_preValues;
    }

    public Map<Character, Integer> getM_postValues() {
        return m_postValues;
    }

    public List<Character> getM_topologicalOrder() {
        return m_topologicalOrder;
    }

    public void findSourcesAndSinks() {
        List<Character> sources = new ArrayList<>();
        List<Character> sinks = new ArrayList<>();
        for (char vertex : m_graph.keySet()) {
            if (m_graph.get(vertex).isEmpty()) {
                sinks.add(vertex);
            }
            boolean isSource = true;
            for (char neighbor : m_graph.keySet()) {
                if (m_graph.get(neighbor).contains(vertex)) {
                    isSource = false;
                    break;
                }
            }
            if (isSource) {
                sources.add(vertex);
            }
        }
        this.m_srcs = sources.size();
        System.out.println("Sources: " + sources);
        System.out.println("Sinks: " + sinks);
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

        TaskC topologicalSort = new TaskC(graph);
        topologicalSort.sort();
        topologicalSort.findSourcesAndSinks();
        int lins = topologicalSort.m_srcs * 2 * 2; //по 2 развилки в Ц и Ф
        System.out.println("Linearizations: " + lins);
        System.out.println("Pre values: " + topologicalSort.getM_preValues());
        System.out.println("Post values: " + topologicalSort.getM_postValues());
        System.out.println("Topological order: " + topologicalSort.getM_topologicalOrder());
    }
}
