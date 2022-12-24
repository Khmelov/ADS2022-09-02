package by.it.group151001.novik.lesson11;

import java.util.*;

public class TaskC {
    public Map<Character, List<Character>> graph;
    public Map<Character, Integer> preValues;
    public Map<Character, Integer> postValues;
    public int counter = 1;
    public List<Character> topologicalOrder;
    public boolean[] visited;
    public int srcs;

    public TaskC(Map<Character, List<Character>> graph) {
        this.graph = graph;
        this.preValues = new HashMap<>();
        this.postValues = new HashMap<>();
        topologicalOrder = new ArrayList<>();
        visited = new boolean[graph.size()];
    }

    public void sort() {
        for (char vertex : graph.keySet()) {
            if (!visited[vertex - 'A']) {
                dfs(vertex);
            }
        }
        Collections.reverse(topologicalOrder);
    }

    public void dfs(Character vertex) {
        preValues.put(vertex, counter++);
        visited[vertex - 'A'] = true;
        for (char neighbor : graph.get(vertex)) {
            if (!visited[neighbor - 'A']) {
                dfs(neighbor);
            }
        }
        topologicalOrder.add(vertex);
        postValues.put(vertex, counter++);
    }

    public Map<Character, Integer> getPreValues() {
        return preValues;
    }

    public Map<Character, Integer> getPostValues() {
        return postValues;
    }

    public List<Character> getTopologicalOrder() {
        return topologicalOrder;
    }

    public void findSourcesAndSinks() {
        List<Character> sources = new ArrayList<>();
        List<Character> sinks = new ArrayList<>();
        for (char vertex : graph.keySet()) {
            if (graph.get(vertex).isEmpty()) {
                sinks.add(vertex);
            }
            boolean isSource = true;
            for (char neighbor : graph.keySet()) {
                if (graph.get(neighbor).contains(vertex)) {
                    isSource = false;
                    break;
                }
            }
            if (isSource) {
                sources.add(vertex);
            }
        }
        this.srcs = sources.size();
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
        int lins = topologicalSort.srcs * 2 * 2; //по 2 развилки в Ц и Ф
        System.out.println("Linearizations: " + lins);
        System.out.println("Pre values: " + topologicalSort.getPreValues());
        System.out.println("Post values: " + topologicalSort.getPostValues());
        System.out.println("Topological order: " + topologicalSort.getTopologicalOrder());
    }
}
