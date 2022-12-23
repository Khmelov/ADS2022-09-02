package by.it.group151004.eremeichik.lesson12;

import java.util.Comparator;

class Node implements Comparator<Node> {
        public int value;
        public int cost;

        public Node(){}

        public Node(int value, int cost)
        {
            this.value = value;
            this.cost = cost;
        }

        @Override
        public int compare(Node node1, Node node2)
        {
            return Integer.compare(node1.cost,node2.cost);
        }
}
