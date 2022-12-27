package by.it.group151004.glushachenko.lesson11;

import java.util.Arrays;
import java.util.LinkedList;

public class TaskA {
    public LinkedList<Integer>[] adjacencyList;
    public String[] vertexNames;

    public TaskA(int numVertices) {
        adjacencyList = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
        vertexNames = new String[numVertices];
    }

    public void add(int v1, int v2) {
        adjacencyList[v1].add(v2);
        adjacencyList[v2].add(v1);
    }

    public void setName(int v, String name) {
        vertexNames[v] = name;
    }

    public String getName(int v) {
        return vertexNames[v];
    }

    public int getNum() {
        return adjacencyList.length;
    }

    public int[] getVerticesByAlphabet() {
        int[] vertices = new int[getNum()];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = i;
        }
        Arrays.sort(vertices);
        return vertices;
    }

    public int[] getNeighborsByAlphabet(int v) {
        int[] neighbors = new int[adjacencyList[v].size()];
        int i = 0;
        for (int neighbor : adjacencyList[v]) {
            neighbors[i] = neighbor;
            i++;
        }
        Arrays.sort(neighbors);
        return neighbors;
    }

    public static class DFS {
        private final boolean[] visited;
        private final int[] pre;
        private final int[] post;
        private int counter;
        public StringBuilder str = new StringBuilder();

        public DFS(TaskA g) {
            visited = new boolean[g.getNum()];
            pre = new int[g.getNum()];
            post = new int[g.getNum()];
            counter = 0;

            for (int v : g.getVerticesByAlphabet()) {
                if (!visited[v]) {
                    Explore(g, v);
                }
            }
        }

        public void Explore(TaskA g, int v) {
            visited[v] = true;
            pre[v] = counter++;

            for (int neighbor : g.getNeighborsByAlphabet(v)) {
                if (!visited[neighbor]) {
                    str.append(g.getName(v)).append(" - ").append(g.getName(neighbor)).append(" (tree edge) ");
                    Explore(g, neighbor);
                } else {
                    str.append(g.getName(v)).append(" - ").append(g.getName(neighbor)).append(" (back edge) ");
                }
            }

            post[v] = counter++;
        }

        public int getPre(int v) {
            return pre[v];
        }

        public int getPost(int v) {
            return post[v];
        }

        public String getString() {
            return str.toString();
        }
    }

    public static void main(String[] args) {
        TaskA taskA = new TaskA(9);
        taskA.setName(0, "A");
        taskA.setName(1, "B");
        taskA.setName(2, "C");
        taskA.setName(3, "D");
        taskA.setName(4, "E");
        taskA.setName(5, "F");
        taskA.setName(6, "G");
        taskA.setName(7, "H");
        taskA.setName(8, "I");
        taskA.add(3, 7);
        taskA.add(3, 6);
        taskA.add(6, 7);
        taskA.add(0, 1);
        taskA.add(0, 4);
        taskA.add(1, 4);
        taskA.add(1, 2);
        taskA.add(2, 5);
        taskA.add(5, 8);
        taskA.add(4, 5);

        DFS dfs = new DFS(taskA);

        System.out.println(dfs.getString());
        for (int v : taskA.getVerticesByAlphabet()) {
            System.out.println(taskA.getName(v) + ": pre = " + dfs.getPre(v) + ", post = " + dfs.getPost(v));
        }
    }
}
