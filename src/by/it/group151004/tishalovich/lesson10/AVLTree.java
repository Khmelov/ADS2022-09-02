package by.it.group151004.tishalovich.lesson10;

import java.util.ArrayList;
import java.util.Iterator;

import static java.lang.Math.max;

class AVLTree<E> {
    private class Node<E> {
        Node<E> left, right, parent;
        E value;
        int height;
        Node(E value){
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.height = 0;
        }

        private int getLeftHeight(){
            int leftHeight = (this.left == null) ? (0) : this.left.height + 1;
            return leftHeight;
        }
        private int getRightHeight(){
            int rightHeight = (this.right == null) ? (0) : this.right.height + 1;
            return rightHeight;
        }

        private void setHeight(){
            this.height = this.getHeight();
        }
        private int getHeight(){
            return max(getLeftHeight(), getRightHeight());
        }
        private int getBalance(){
            return getLeftHeight() - getRightHeight();
        }

        int compareTo(Node<E> node){
            return ((Comparable<? super E>)this.value).compareTo(node.value);
        }
        boolean isLeaf(){
            return this.left == null && this.right == null;
        }

        boolean hasOneChild(){
            return (this.left == null && this.right != null) ||
                    (this.left != null && this.right == null);
        }

        boolean isLeftChild(){
            return this.parent.left != null && this.parent.left == this;
        }

        boolean isRoot(){
            return this.parent == null;
        }
    }

    private int size;

    private void leftRotation(Node<E> x){
        Node<E> y = x.right;
        Node<E> xParent = x.parent;
        if(x.isLeftChild()){
            x.parent.left = y;
        } else{
            x.parent.right = y;
        }
        x.right = y.left;
        y.left = x;
        x.parent = y;
        y.parent = xParent;
        x.setHeight();
        y.setHeight();
    }

    private void rightRotation(Node<E> y){
        Node<E> x = y.left;
        Node<E> yParent = y.parent;
        if(y.isLeftChild()){
            y.parent.left = x;
        }else{
            y.parent.right = x;
        }
        y.left = x.right;
        x.right = y;
        x.parent = yParent;
        y.parent = x;
        y.setHeight();
        x.setHeight();
    }

    private void leftRightRotation(Node<E> node){
        leftRotation(node.left);
        rightRotation(node);
    }

    private void rightLeftRotation(Node<E> node){
        rightRotation(node.right);
        leftRotation(node);
    }

    //the left child of preRoot is the root
    private final Node<E> preRoot;

    AVLTree() {
        preRoot = new Node<>(null);
        preRoot.parent = null;
        preRoot.left = null;
    }

    private void rebalance(Node<E> node){
    //node is currently inserted node(height == 0)
        Node<E> parent;
        while(node.parent != null) {
            node.setHeight();
           parent = node.parent;
            if (node.getBalance() > 1) {
                if (node.left.getBalance() > 0) {
                    rightRotation(node);
                } else {
                    leftRightRotation(node);
                }
            } else if (node.getBalance() < -1) {
                if (node.right.getBalance() > 0) {
                    rightLeftRotation(node);
                } else {
                    leftRotation(node);
                }
            }
            node = parent;
        }
    }

    Node<E> add(E value){
        Node<E> newNode = new Node<>(value);
        if(preRoot.left == null) {
            preRoot.left = newNode;
            preRoot.left.parent = preRoot;
        }else{
            Node<E> temp = preRoot.left;
            Node<E> prev;
            do {
                prev = temp;
                if(temp.compareTo(newNode) > 0){
                    temp = temp.left;
                } else if(temp.compareTo(newNode) < 0){
                    temp = temp.right;
                } else{
                    return null;
                }
            }while(temp != null);
            newNode.parent = prev;
            if(prev.compareTo(newNode)>0){
                prev.left = newNode;
            }else{
                prev.right = newNode;
            }
            //rebalance(newNode);
        }
        return newNode;
    }

    boolean contains(Object o){
        return null != findNode((E) o);
    }

    void clear(){
        preRoot.left = null;
    }

    boolean isEmpty(){
        return preRoot.left == null;
    }

    int size(){
        size = 0;
        countSize(preRoot.left);
        return size;
    }

    void countSize(Node<E> node){
        if(node != null){
            size++;
            countSize(node.left);
            countSize(node.right);
        }
    }

    E first(){
        if(isEmpty()) return null;
        Node<E> tmp = preRoot.left;
        while(tmp.left != null){
            tmp = tmp.left;
        }
        return tmp.value;
    }

    E last(){
        if(isEmpty()) return null;
        Node<E> tmp = preRoot.left;
        while(tmp.right != null){
            tmp = tmp.right;
        }
        return tmp.value;
    }

    E pollFirst(){
        E first = first();
        delete(first);
        return first;
    }

    E pollLast(){
        E last = last();
        delete(last);
        return last;
    }

    E lower(E e){
        E result = null;
        Node<E> cmp = new Node<>(e);
        Node<E> node = preRoot.left;
        while(node != null) {
            if (node.compareTo(cmp) >= 0) {
                node = node.left;
            } else {
                result = node.value;
                node = node.right;
            }
        }
        return result;
    }

    E floor(E e){
        E result = null;
        Node<E> cmp = new Node<>(e);
        Node<E> node = preRoot.left;
        while(node != null) {
            if (node.compareTo(cmp) > 0) {
                node = node.left;
            } else {
                result = node.value;
                node = node.right;
            }
        }
        return result;
    }

    E ceiling(E e){
        E result = null;
        Node<E> cmp = new Node<>(e);
        Node<E> node = preRoot.left;
        while(node != null) {
            if (node.compareTo(cmp) >= 0) {
                result = node.value;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return result;
    }

    E higher(E e){
        E result = null;
        Node<E> cmp = new Node<>(e);
        Node<E> node = preRoot.left;
        while(node != null) {
            if (node.compareTo(cmp) > 0) {
                result = node.value;
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return result;
    }

    private void checkLower(Node<E> result, Node<E> cmp, Node<E> node){

    }

    Iterator<E> iterator(){
        ArrayList<E> list = new ArrayList<>();
        toArrayList(preRoot.left, list);
        return list.iterator();
    }

    void toArrayList(Node<E> node, ArrayList<E> list){
        if(node != null){
            toArrayList(node.left, list);
            list.add(node.value);
            toArrayList(node.right, list);
        }
    }

    private Node<E> findNode(E value){
        Node<E> temp = preRoot.left;
        while(temp != null){
            if(((Comparable<? super E>)temp.value).compareTo(value) > 0){
                temp = temp.left;
            } else if(((Comparable<? super E>)temp.value).compareTo(value) < 0){
                temp = temp.right;
            } else{
                return temp;
            }
        }
        return null;
    }

    private void deleteLeaf(Node<E> forDeleting){
        Node<E> parent = forDeleting.parent;

        if(forDeleting.isLeftChild()){
            parent.left = null;
        } else{
            parent.right = null;
        }
    }

    private void deleteHasOneChild(Node<E> forDeleting){
        Node<E> parent = forDeleting.parent;
        if(forDeleting.isLeftChild()){
            if(forDeleting.left == null){
                parent.left = forDeleting.right;
            } else{
                parent.left = forDeleting.left;
            }
            parent.left.parent = parent;
        }else{
            if(forDeleting.left == null){
                parent.right = forDeleting.right;
            } else{
                parent.right = forDeleting.left;
            }
            parent.right.parent = parent;
        }
    }

    Node<E> delete(E value){

        Node<E> forDeleting = findNode(value);

        if(forDeleting != null){
            Node<E> parent = forDeleting.parent;
            Node<E> forRebalance = null;
            //!!!!!!!!!!!WARNING ROOT DELETING!!!!!!!
            if(forDeleting.isLeaf()){
                deleteLeaf(forDeleting);
                forRebalance = parent;
            //!!!!!!!!!!!WARNING ROOT DELETING!!!!!!!
            }else if(forDeleting.hasOneChild()){
                deleteHasOneChild(forDeleting);
                forRebalance = parent;
            }else{
                Node<E> toSwap = forDeleting.right;
                while(toSwap.left != null){
                    toSwap = toSwap.left;
                }
                E swapValue = toSwap.value;
                forRebalance = toSwap.parent;
                if(toSwap.isLeaf()){
                    deleteLeaf(toSwap);
                }
                else{
                    deleteHasOneChild(toSwap);
                }
                forDeleting.value = swapValue;
            }
            //rebalance(forRebalance);
        }
        return forDeleting;
    }
    void printTree(){
        toStringOrder(preRoot.left, 0);
    }
    private void toStringOrder(Node<E> node, int nestingLevel){
        if(node != null) {
            toStringOrder(node.right, nestingLevel + 1);
            printMargin(nestingLevel);
            System.out.println(node.value);
            toStringOrder(node.left, nestingLevel + 1);
        }
    }
    private void printMargin(int nSpaces){
        for (int i = 0; i < 4*nSpaces; i++) {
            System.out.print(" ");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        toStringTrav(sb, preRoot.left);
        if(preRoot.left != null){
            sb.deleteCharAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append(']');
        return sb.toString();
    }

    private void toStringTrav(StringBuilder sb, Node<E> node){
        if(node != null){
            toStringTrav(sb, node.left);
            sb.append(node.value);
            sb.append(", ");
            toStringTrav(sb, node.right);
        }
    }
}
