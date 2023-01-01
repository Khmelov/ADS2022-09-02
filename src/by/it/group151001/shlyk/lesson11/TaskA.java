package by.it.group151001.shlyk.lesson11;

import by.it.group151001.kononchuk.lesson09.Node;

import java.awt.*;
import java.util.Arrays;
import java.util.Stack;

public class TaskA {

    private class TimePoint {
        int start;
        int end;
        TimePoint(int start, int end){
            this.start = start;
            this.end = end;
        }
    }
    public enum EdgeType {Start, Regular, Reversed};
    //bases of lists
    private class NodeInfo{
       NodeInfo next;
       int key;
       EdgeType type;
       NodeInfo(EdgeType type, int key){
           this.type = type;
           this.key = key;
       }

    }
    int GL_COUNTER;
    int matrix[][];
    int arrPrev[];
    int arrPost[];
    NodeInfo[] arrNodes;

    EdgeType convertToType(TimePoint father, TimePoint son){
        if(father.start < son.start && father.end > son.end){
            return EdgeType.Regular;
        }
        return EdgeType.Reversed;
    }
    //Assume: arrNodes is not null
    void fillNodes(){
        NodeInfo[] arrHead = arrNodes;
        NodeInfo[] arrTail = new NodeInfo[matrix.length];
        arrVisited= new boolean[matrix.length];
        Arrays.fill(arrVisited, false);
        for(int iX = 0; iX < matrix.length; iX++){
            for(int iY = iX + 1; iY < matrix.length; iY++){
                if(matrix[iY][iX] != 0) { //exists
                    EdgeType type;
                    if(arrVisited[iY])
                        type = EdgeType.Reversed;
                    else
                        type = EdgeType.Regular;
                    NodeInfo son = new NodeInfo(type, iY);
                    if(arrTail[iX] == null){
                        arrHead[iX] = son;
                    }
                    else{
                        arrTail[iX].next = son;
                    }
                    arrTail[iX] = son;

                }
            }
        }
    }
    NodeInfo[] arrHeader;
    NodeInfo[] arrTail;
    boolean[] arrVisited;
    int nRest;
    void insideTravel(int iX){
        arrVisited[iX] = true;
        nRest -= 1;
        arrPrev[iX] = GL_COUNTER++;
        for(int iY = (iX + 1); iY < matrix.length; iY++){
            if(matrix[iY][iX] != 0){
                EdgeType type;
                if(!arrVisited[iY])
                    type = EdgeType.Regular;
                else
                    type = EdgeType.Reversed;
                NodeInfo son = new NodeInfo(type, iY);
                arrTail[iX].next = son;
                arrTail[iX] = son;
                if(!arrVisited[iY]){
                    insideTravel(iY);
                }
            }

        }
        arrPost[iX] = GL_COUNTER++;

    }
    void startTravel() {
        GL_COUNTER = 0;
        arrVisited = new boolean[matrix.length];
        Arrays.fill(arrVisited, false);
        arrHeader = arrNodes;
        for(int i = 0; i < matrix.length; i++){
            arrHeader[i] = new NodeInfo(EdgeType.Start, i);
        }
        arrTail = new NodeInfo[matrix.length];
        System.arraycopy(arrHeader, 0, arrTail, 0, matrix.length);
        while(nRest != 0){
            for(int i = 0; i < arrVisited.length; i++){
                if(!arrVisited[i])
                    insideTravel(i);
            }
        }


    }
    void printNodes(){
        for(NodeInfo item : arrNodes){
            NodeInfo node = item.next;
            int stage = 0;
            if(item.type == EdgeType.Start)
                System.out.println("Node " + (char)(item.key + 'A'));
            if(node == null)
                continue;
            if(node.type == EdgeType.Regular)
                System.out.println("\tRegular edges:");
            while(node != null){
                if(stage == 0)
                    stage = node.type == EdgeType.Regular ? 0 : 1;
                if(stage == 1) {
                    System.out.println("\tReversed edges:");
                    stage += 1;
                }
                System.out.println((char) (node.key + 'A') + "( " + arrPrev[node.key] + ':' + arrPost[node.key] + "), ");
                node = node.next;
            }
        }
    }
    TaskA(){
        arrPost = new int[9];
        arrPrev = new int[9];
        arrNodes = new NodeInfo[9];
        this.matrix = new int[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 0, 1, 0, 1, 0, 0, 0, 0},
                    {0, 1, 0, 0, 0, 1, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 1, 1, 0},
                    {1, 1, 0, 0, 0, 1, 0, 0, 0},
                    {0, 1, 1, 0, 1, 0, 0, 0, 1},
                    {0, 0, 0, 1, 0, 0, 0, 1, 0},
                    {0, 0, 0, 1, 0, 0, 1, 0, 0},
                    {0, 0, 0, 0, 0, 1, 0, 0, 0}

            };
        nRest = matrix.length;
        startTravel();
        printNodes();
        }
        TaskA(int[][] matrix){
            this.matrix = matrix;
            arrPrev = new int[matrix.length];
            arrPost = new int[matrix.length];
            arrNodes = new NodeInfo[matrix.length];
        }

}
