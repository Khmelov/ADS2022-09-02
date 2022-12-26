package by.it.group151004.karvatskaya.lesson11;

import java.util.ArrayList;
import java.util.Arrays;

public class Start {
        public ArrayList<ArrayList<Integer>> graph;
        public int depth;
        public char[] nodusName;

        public Start(int counter) {
            depth = counter;
            graph = new ArrayList<>();
            for (int i = 0; i < depth; i++) {
                graph.add(new ArrayList<>());
            }
            nodusName = new char[depth];
        }

         void addEdge(int a, int b) {
        graph.get(a).add(b);
        graph.get(b).add(a);
         }

         void addOrientEdge(int a, int b) {
        graph.get(a).add(b);
         }
        char getName(int ind) {
            return nodusName[ind];
        }

        int getVertCount() {
            return depth;
        }

        int[] getNear(int vertex) {
            int[] near = new int[graph.get(vertex).size()];
            int i = 0;
            for (int num : graph.get(vertex))
                near[i++] = num;
            Arrays.sort(near);
            return near;
        }

        void setName(int ind, char name) {
            nodusName[ind] = name;
        }
    }

