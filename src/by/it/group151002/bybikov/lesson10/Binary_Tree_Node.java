package by.it.group151002.bybikov.lesson10;

public class Binary_Tree_Node<T extends Comparable<T>> {
    private T top_Value;
    private Binary_Tree_Node<T> right;
    private Binary_Tree_Node<T> left;
    private Binary_Tree_Node<T> parent;
    Binary_Tree_Node(){
        this.top_Value = null;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
    Binary_Tree_Node(T top_Value){
        this.top_Value = top_Value;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    void setTop_Value(T top_Value) {
        this.top_Value = top_Value;
    }

    void setLeft(Binary_Tree_Node<T> left) {
        this.left = left;
    }

    void setRight(Binary_Tree_Node<T> right) {
        this.right = right;
    }

    void setParent(Binary_Tree_Node<T> parent){
        this.parent = parent;
    }

    T getTop_Value() {
        return this.top_Value;
    }

    Binary_Tree_Node<T> getRight(){
        return this.right;
    }

    Binary_Tree_Node<T> getLeft(){
        return this.left;
    }

    Binary_Tree_Node<T> getParent() {
        return this.parent;
    }

}
