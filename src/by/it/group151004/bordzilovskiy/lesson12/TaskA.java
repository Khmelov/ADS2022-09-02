package by.it.group151004.bordzilovskiy.lesson12;

import java.util.*;

public class TaskA {
    public int maxlen = 1000000;
    public List<List<Info>> list = new ArrayList<>();
    public int[] len;
    public boolean[] visited;

    public void GetWay(int start) {
        len = new int[list.size()];
        Arrays.fill(len, maxlen);
        visited = new boolean[list.size()];

        PriorityQueue<Info> pq = new PriorityQueue<>(new Comparator<Info>() {
            @Override
            public int compare(Info p1, Info p2) {
                return p1.cost - p2.cost;
            }
        });

        pq.offer(new Info(start, 0));
        len[start] = 0;

        while (!pq.isEmpty()) {
            Info p = pq.poll();
            int here = p.here;
            if (visited[here])
                continue;

            visited[here] = true;
            for (int i = 0; i < list.get(here).size(); i++) {
                int there = list.get(here).get(i).here;
                int cost = list.get(here).get(i).cost;

                if (len[there] > len[here] + cost) {
                    len[there] = len[here] + cost;
                    pq.offer(new Info(there, len[there]));
                }
            }
        }
    }

    public static class Info {
        int here;
        int cost;

        Info(int here, int cost) {
            this.here = here;
            this.cost = cost;
        }
    }

    public static void main(String[] args) {
        TaskA graphA = new TaskA();
        for (int i = 0; i < 8; i++) {
            graphA.list.add(new ArrayList<Info>());
        }
        graphA.list.get(0).add(new Info(4, 4));
        graphA.list.get(0).add(new Info(5, 8));
        graphA.list.get(0).add(new Info(1, 1));
        graphA.list.get(1).add(new Info(5, 6));
        graphA.list.get(1).add(new Info(6, 6));
        graphA.list.get(1).add(new Info(2, 2));
        graphA.list.get(2).add(new Info(6, 2));
        graphA.list.get(2).add(new Info(3, 1));
        graphA.list.get(3).add(new Info(6, 1));
        graphA.list.get(3).add(new Info(7, 4));
        graphA.list.get(6).add(new Info(5, 1));
        graphA.list.get(6).add(new Info(7, 1));
        graphA.list.get(4).add(new Info(5, 5));
        graphA.GetWay(0);
        for (int i = 0; i < graphA.len.length; i++) {
            System.out.println("Shortest path from A to " + (char) ('A' + i) + ": " + graphA.len[i]);
        }
    }
}
