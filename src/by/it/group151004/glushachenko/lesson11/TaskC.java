package by.it.group151004.glushachenko.lesson11;

import java.util.*;

public class TaskC {
    public Map<Character, List<Character>> graph;
    public Map<Character, Integer> pre;
    public Map<Character, Integer> post;
    public int counter = 1;
    public Stack<Character> topologicalOrder;
    public boolean[] visited;
    public int srcs;
    List<Character> sources = new ArrayList<>();
    List<Character> sinks = new ArrayList<>();

    public TaskC(Map<Character, List<Character>> graph) {
        this.graph = graph;
        this.pre = new HashMap<>();
        this.post = new HashMap<>();
        topologicalOrder = new Stack<>();
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
        pre.put(vertex, counter++);
        visited[vertex - 'A'] = true;
        for (char neighbor : graph.get(vertex)) {
            if (!visited[neighbor - 'A']) {
                dfs(neighbor);
            }
        }
        topologicalOrder.push(vertex);
        post.put(vertex, counter++);
    }

    public String getPreValues() {
        return pre.toString();
    }

    public String getPostValues() {
        return post.toString();
    }

    public String getTopologicalOrder() {
        return topologicalOrder.toString();
    }

    public void findSourcesAndSinks() {

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
    }

    public String getSources(){return this.sources.toString();}
    public String getSinks(){return this.sinks.toString();}
    public int getLins(){return this.srcs * 2 * 2;}

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

        TaskC taskC = new TaskC(graph);
        taskC.sort();
        taskC.findSourcesAndSinks();
        System.out.println("Sources: " + taskC.getSources());
        System.out.println("Sinks: " + taskC.getSinks());
        System.out.println("Linearizations: " + taskC.getLins());
        System.out.println("Pre values: " + taskC.getPreValues());
        System.out.println("Post values: " + taskC.getPostValues());
        System.out.println("Topological order: " + taskC.getTopologicalOrder());
    }
}