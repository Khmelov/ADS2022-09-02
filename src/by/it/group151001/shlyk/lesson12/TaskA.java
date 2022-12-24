package by.it.group151001.shlyk.lesson12;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TaskA {

    final static int VERY_LONG_PATH = 1_000_000;

    private class NodeInfo implements Comparable {
        char key;
        Node pOrigin;
        int weight;

        int path;
        NodeInfo(char key){
            this.key = key;
        }
        NodeInfo(Node origin, int weight){
            pOrigin = origin;
            key = origin.info.key;
            this.weight = weight;
            path = VERY_LONG_PATH;
        }
        void setPath(int path){
            this.path = path;
            if(pOrigin != null)
                pOrigin.info.setPath(path);
        }
        void updatePath(int path){
            if(path < this.path){
                this.path = path;
                if(pOrigin != null)
                    pOrigin.info.updatePath(path);
            }
        }
        int getPath(){
            if(pOrigin != null){
                if(pOrigin.info.path != this.path)
                    this.path = pOrigin.info.path;
            }
            return this.path;
        }
        @Override
        public int compareTo(@NotNull Object o) {
            NodeInfo other = (NodeInfo)o;
            return this.weight - other.weight;
        }
    }
    private class Node{
        Node next;
        Node prev;
        NodeInfo info;
        Node (char key){
            info = new NodeInfo(key);
        }
        Node (Node origin, int weight){
            this.info = new NodeInfo(origin, weight);
        }

        void addPrev(Node father, int weight){
            if(prev == null) {
                prev = new Node(father, weight);
            }
            else {
                Node node = prev;
                while(node.prev != null)
                    node = node.prev;
                node.prev = new Node(father, weight);
            }
        }
        void addChild(Node origin, int weight){
            if(next == null) {
                next = new Node(origin, weight);
            }
            else {
                Node node = next;
                while(node.next != null)
                    node = node.next;
                node.next = new Node(origin, weight);

            }
            origin.addPrev(this, weight); //it's impossible to achieve father)))
        }
        //return parent according to him path value
        @Nullable
        Node getParent(){
            Node parent = this.prev;
            while(parent != null){
                int prevPath = parent.info.getPath();
                if(prevPath == this.info.path - parent.info.weight)
                    return parent;
                parent = parent.prev;
            }
            return null;
        }
        void setPath(int path){
            info.setPath(path);
        }
    }
    Node[] arrNodes;
    int[][] matrix;

    //format: "A->B,C,D;..."
    void addNodes(String strSource){

    }
    TaskA(){
        arrNodes = new Node[8];
        for(int i = 0; i < arrNodes.length; i++)
            arrNodes[i] = new Node((char) i);

        arrNodes[0].addChild(arrNodes[1], 1);
        arrNodes[0].addChild(arrNodes[4], 4);
        arrNodes[0].addChild(arrNodes[5], 8);

        arrNodes[1].addChild(arrNodes[2], 2);
        arrNodes[1].addChild(arrNodes[5], 6);
        arrNodes[1].addChild(arrNodes[6], 6);

        arrNodes[2].addChild(arrNodes[3], 1);
        arrNodes[2].addChild(arrNodes[6], 2) ;

        arrNodes[3].addChild(arrNodes[6], 1);
        arrNodes[3].addChild(arrNodes[7], 4);

        arrNodes[4].addChild(arrNodes[5], 5);

        arrNodes[6].addChild(arrNodes[5], 1);
        arrNodes[6].addChild(arrNodes[7], 1);
        printShortest('A', 'G');

    }
    //start from A...
    ArrayList<NodeInfo> restorePath(Node finished){
            int nRest = finished.info.path;
            Node node = finished;
            Node parent;
            ArrayList<NodeInfo> arrItems = new ArrayList<>();
            while(nRest != 0){
                parent = node.getParent();
                if(parent == null)
                    throw new IllegalStateException("Impossible to find existing pathway");
                node = parent;
                arrItems.add(node.info);
                nRest -= node.info.weight;
            }
            arrItems.add(finished.info);
            arrItems.sort(Comparator.comparingInt(o -> o.path));
            return arrItems;
    }
    void printPath(ArrayList<NodeInfo> arrItem){
        for(NodeInfo item : arrItem){
            System.out.print( (char) (item.key + 'A') + " -> ");
        }
        System.out.println("END");
    }
    void initPathSearch(char iStart){
        for(int i = 0; i < arrNodes.length; i++){
            if(i != iStart)
                arrNodes[i].setPath(VERY_LONG_PATH);
            else
                arrNodes[i].setPath(0);
        }
    }
    void printShortest(char lblStart, char lblEnd){
        char iStart = (char) (lblStart - 'A');
        char iEnd = (char) (lblEnd - 'A');
        if(iStart == iEnd) {
            System.out.println("Node is finish yet)");
            return;
        }
        initPathSearch(iStart);
        boolean isFinish = false;
        NodeInfo lastInfo = null;
        PriorityQueue<NodeInfo> queue = new PriorityQueue<>();
        ArrayList<NodeInfo> container = new ArrayList<>();
        queue.add(arrNodes[iStart].info);
        while(!isFinish && !queue.isEmpty()){
            NodeInfo info = queue.poll();
            Node root = arrNodes[info.key];
            Node link = root.next;
            int nLinks = 0;
            while(link != null){
                queue.add(link.info);
                nLinks++;
                link = link.next;
            }
            for(int i = 0; i < nLinks; i++){
                NodeInfo linkInfo = queue.poll();
                lastInfo = linkInfo; //save last
                linkInfo.updatePath(linkInfo.weight + info.path);
                isFinish = linkInfo.key == iEnd;
                if(isFinish)
                    break;
                container.add(linkInfo);
            }
            queue.addAll(container);
            container.clear();
        }
        if(isFinish) {
            container = restorePath(arrNodes[lastInfo.key]);
            printPath(container);
        }
        else {
            System.out.println("No such path");
            return;
        }

    }

}
