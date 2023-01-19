package by.it.group151001.milkovskaya.lesson11;

import java.util.Arrays;

public class TaskA {
        public int size;
        public boolean[][] matrix;
        public String res = "";
        public int[] pre;
        public int[] post;
        public boolean[] visited;
        public int start = 0;
        int clock = 0;
        public void addEdge(int x, int y){
            matrix[x][y] = true;
            matrix[y][x] = true;
        }
        public void initDFS()
        {
            visited = new boolean[size];
            pre = new int[size];
            post = new int[size];
            for (int i = 0; i < size; i++)
            {
                if (!visited[i])
                {
                    explore(i);
                }
                System.out.println("Pre " + i + " = "+ Arrays.toString(pre));

                System.out.println("Post " + i + " = "+ Arrays.toString(post));
            }

        }
        public void explore(int v)
        {
            visited[v] = true;
            pre[v] = clock++;
            res = res + v + " ";
            for (int i = 0; i < size; i++)
            {
                if (matrix[v][i] && !visited[i])
                {
                    explore(i);
                }
            }
            post[v] = clock++;
        }
        public static void main(String[] args) {
            TaskA graph = new TaskA();
            graph.size = 9;
            graph.matrix = new boolean[graph.size][graph.size];
            graph.addEdge(0,1);
            graph.addEdge(0,4);
            graph.addEdge(1,2);
            graph.addEdge(1,4);
            graph.addEdge(2,5);
            graph.addEdge(4,5);
            graph.addEdge(6,7);
            graph.addEdge(3,7);
            graph.addEdge(3,6);
            graph.addEdge(5,8);
            graph.initDFS();
            System.out.println(graph.res);
        }
}

