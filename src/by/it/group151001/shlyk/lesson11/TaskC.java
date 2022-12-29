package by.it.group151001.shlyk.lesson11;

import java.util.Arrays;
import java.util.Stack;

public class TaskC {
    int[][] matrix;
    int[] arrStart;
    int[] arrEnd;
    int counter;
    boolean[] arrVisited;
    int[] arrNest;
    int nRest;

    private class Node {
        Node next;
        boolean hasPrev;
        int key;
        Node(int key){
            this.key = key;
            hasPrev = false;
        }
        void printInfo(int[] arrStart, int[] arrEnd){
            System.out.println( (char) (this.key + 'A') +  " (" + arrStart[this.key] + '-' + arrEnd[this.key] + ")");
        }
    }

    Node[] arrNodes;
    void addNode(int iFather, int key){
        Node node = arrNodes[iFather];
        while(node.next != null)
            node = node.next;
        node.next = new Node(key);
    }
    void markPrev(int iFather){
        arrNodes[iFather].hasPrev = true;
    }

    void insideTravel(int iX){
        arrStart[iX]  = counter++;
        nRest -= 1;
        for(int iY = iX + 1; iY < matrix.length; iY++){
            if(matrix[iY][iX] != 0){
                arrNest[iY]++;
                addNode(iX, iY);
                markPrev(iY);
                if(!arrVisited[iY])
                    insideTravel(iY);

            }
        }

        arrEnd[iX] = counter++;
        arrVisited[iX] = true;
    }

    void initNodes(){
        arrNodes = new Node[matrix.length];
        for(int i = 0; i < matrix.length; i++){
            arrNodes[i] = new Node(i);
        }
    }
    void initBuilding(){
        initNodes();
        while(nRest != 0){
            for(int i = 0; i < arrVisited.length; i++){
                if(!arrVisited[i]){
                    insideTravel(i);
                    if(nRest == 0)
                        break;
                }

            }
        }
        initSearch();
        printNodes();
        printRivers();
    }
    void printRivers(){
        System.out.println("The all source");
        for(Node root : arrNodes){
            if(!root.hasPrev){
                System.out.print("\t");
                root.printInfo(arrStart, arrEnd);
            }
        }
        System.out.println("The all drains");
        for(Node root : arrNodes){
            if(root.next == null) {
                System.out.print("\t");
                root.printInfo(arrStart, arrEnd);
            }
        }
    }
    void printNodes(){
        for(Node root : arrNodes){
            System.out.print("Node ");
            root.printInfo(arrStart, arrEnd);
            Node node = root.next;
            while(node != null) {
                System.out.print("\t*child node: ");
                node.printInfo(arrStart, arrEnd);
                node = node.next;
            }
        }
    }
    void initSearch(){
        nChild = 0;
        varCounter = 0;
        Stack<Integer> stack = new Stack<>();
        boolean[] arrVisited = new boolean[arrNest.length];
        for(int i = 0; i < arrVisited.length; i++){
            if(arrNest[i] == 0){
                stack.push(i);
                arrVisited[i] = true;
                nChild++;
            }
        }
        strOutput = new StringBuilder();
        shuffleStack(stack, arrVisited);
        System.out.println("The total num of variants: " + varCounter);
    }

    int nChild = 0;
    void swap(int[] arrEntity, int iBig, int iSmall){
        int temp = arrEntity[iBig];
        arrEntity[iBig] = arrEntity[iSmall];
        arrEntity[iSmall] = temp;
    }
    boolean shuffleArray(int[] arrStack){
        if(arrStack.length == 0)
            return false;

        int i = arrStack.length - 2;
        while(i >= 0 && arrStack[i] > arrStack[i + 1])
            i -= 1;
        if(i == -1)
            return false;
        for(int j = i + 1, k = arrStack.length - 1; j < k; j++, k--)
            swap(arrStack, j, k);
        int j = i + 1;
        while(arrStack[j] < arrStack[i])
            j++;
        swap(arrStack, i, j);
        return true;
    }
    boolean isVisited(boolean[] arrVisited){
        for(boolean loc : arrVisited)
            if(!loc)
                return false;
        return true;
    }

    StringBuilder strOutput;
    int varCounter;

    void shuffleStack(Stack<Integer> stack, boolean[] arrOrigin){
        int[] arrChild = new int[nChild];
        for(int i = (arrChild.length - 1); i >= 0; i--)
            arrChild[i] = stack.pop();

        boolean[] arrVisited = new boolean[arrOrigin.length];

        int[] arrSavedNest = new int[arrNest.length];
        System.arraycopy(arrNest, 0, arrSavedNest, 0, arrNest.length);
        StringBuilder strCurrOutput = new StringBuilder(strOutput);

        boolean isShuffled = true;
        while(isShuffled){
            for(int child : arrChild)
                stack.push(child);
            System.arraycopy(arrOrigin, 0, arrVisited, 0, arrOrigin.length);
            System.arraycopy(arrSavedNest, 0, arrNest, 0, arrNest.length);
            strOutput = new StringBuilder(strCurrOutput);
            topologySort(stack, arrVisited);
            isShuffled = shuffleArray(arrChild);
        }


        //nChildes > 1 then
    }
    void topologySort(Stack<Integer> stack, boolean[] arrVisited){
        if(stack.size() == 0)
            return;

        Integer iX;
        while(!stack.isEmpty()){
            iX = stack.pop();
            strOutput.append((char) (iX + 'A')).append(" -> ");
            nChild = 0;
            for(int iY = 0; iY < matrix.length; iY++){
                if(!arrVisited[iY] && matrix[iY][iX] != 0){
                    arrNest[iY] -= 1;
                    if(arrNest[iY] == 0){
                        arrVisited[iY] = true;
                        nChild += 1;
                        stack.push(iY);
                    }
                }
            }
            if(nChild > 1){
                shuffleStack(stack, arrVisited);
                return;
            }
        }
        strOutput.append("EoL");
        System.out.println(strOutput);
        varCounter += 1;
    }
    void shuffleSort(){

    }
    TaskC(){

        matrix = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 1, 0, 0},
        };
        nRest = matrix.length;
        arrStart = new int[matrix.length];
        arrEnd = new int[matrix.length];
        arrVisited = new boolean[matrix.length];
        Arrays.fill(arrVisited, false);
        arrNest = new int[matrix.length];
        initBuilding();
        shuffleSort();

    }
}
