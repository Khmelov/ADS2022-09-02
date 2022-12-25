package by.it.group151002.ravodin.lesson12;

import java.util.Comparator;

class Node implements Comparator<Node> {
    public int node;
    public int value;

    public Node(){}

    public Node(int node, int data)
    {
        this.node = node;
        this.value = data;
    }

    @Override
    public int compare(Node node1, Node node2)
    {
        if (node1.value < node2.value)
            return -1;
        if (node1.value > node2.value)
            return 1;
        return 0;
    }
}