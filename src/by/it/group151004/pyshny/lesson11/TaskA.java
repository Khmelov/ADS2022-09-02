package by.it.group151004.pyshny.lesson11;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;


public class TaskA {
    public LinkedList<Integer>[] neighbour;
    public String[] name;

    public TaskA(int numVertices) {
        neighbour = new LinkedList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            neighbour[i] = new LinkedList<>();
        }
        name = new String[numVertices];
    }

    public void addEdge(int v1, int v2) {
        neighbour[v1].add(v2);
        neighbour[v2].add(v1);
    }

    public void setName(int v, String str) {
        name[v] = str;
    }

    public String getName(int v) {
        return name[v];
    }

    public int getNum() {
        return neighbour.length;
    }

    public LinkedList<Integer> getNeighbour(int v) {
        return neighbour[v];
    }

    public int[] sort() {
        int[] arr = new int[getNum()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        Arrays.sort(arr);
        return arr;
    }

    public int[] sortNeighbours(int v) {
        int[] neighbors = new int[neighbour[v].size()];
        int i = 0;
        for (int neighbor : neighbour[v]) {
            neighbors[i] = neighbor;
            i++;
        }
        Arrays.sort(neighbors);
        return neighbors;
    }

    public static class DFS {
        public boolean[] visited;
        public int[] pre;
        public int[] post;
        public int preCounter;
        public int postCounter;

        public DFS(TaskA g) {
            visited = new boolean[g.getNum()];
            pre = new int[g.getNum()];
            post = new int[g.getNum()];
            preCounter = 0;
            postCounter = 0;

            for (int v : g.sort()) {
                if (!visited[v]) {
                    dfs(g, v);
                }
            }
        }

        public void dfs(TaskA g, int v) {
            visited[v] = true;
            pre[v] = preCounter++;

            for (int neighbor : g.sortNeighbours(v)) {
                if (!visited[neighbor]) {
                    System.out.println(g.getName(v) + " - " + g.getName(neighbor) + " (tree edge)");
                    dfs(g, neighbor);
                } else {
                    System.out.println(g.getName(v) + " - " + g.getName(neighbor) + " (back edge)");
                }
            }

            post[v] = postCounter++;
        }

        public int getPre(int v) {
            return pre[v];
        }

        public int getPost(int v) {
            return post[v];
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "\\src\\";
        InputStream stream = new FileInputStream(root + "by\\it\\pyshny\\lesson11\\dataA.txt");
        TaskA graphA = new TaskA(9);
        for (int i=0;i<9;i++){
            graphA.setName(i,"A"+i);
        }
        Scanner scanner = new Scanner(stream);
        int n=scanner.nextInt();
        for(int i=0;i<n;i++){
            graphA.addEdge(scanner.nextInt(), scanner.nextInt());
        }
        DFS dfs = new DFS(graphA);
        for (int v : graphA.sort()) {
            System.out.println(graphA.getName(v) + ": pre = " + dfs.getPre(v) + ", post = " + dfs.getPost(v));
        }
    }
}