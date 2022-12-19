package by.it.group151002.bybikov.lesson10;

public class Binary_Tree<T extends Comparable<T>> {
    private Binary_Tree_Node<T> tree_Root;
    private int tree_Size = 0;

    Binary_Tree() {
        this.tree_Size = 0;
        this.tree_Root = null;
    }

    private boolean insert_Node(Binary_Tree_Node<T> parent_Node, Binary_Tree_Node<T> insert_Node) {
        boolean insertion_Result = true;
        if (parent_Node.getTop_Value().compareTo(insert_Node.getTop_Value()) == 0){
            insertion_Result = false;
        }
        else if (parent_Node.getTop_Value().compareTo(insert_Node.getTop_Value()) > 0){
            if (parent_Node.getLeft() == null){
                parent_Node.setLeft(insert_Node);
                insert_Node.setParent(parent_Node);
            }
            else {
                insert_Node(parent_Node.getLeft(), insert_Node);
            }
        }
        else if(parent_Node.getTop_Value().compareTo(insert_Node.getTop_Value()) < 0){
            if (parent_Node.getRight() == null) {
                parent_Node.setRight(insert_Node);
                insert_Node.setParent(parent_Node);
            }
            else {
                insert_Node(parent_Node.getRight(), insert_Node);
            }
        }
        return insertion_Result;
    }

    boolean insert_Value (T insert_Value) {
        Binary_Tree_Node<T> insert_Node = new Binary_Tree_Node<>(insert_Value);
        if (this.tree_Root == null) {
            this.tree_Root = insert_Node;
            this.tree_Size++;
            return true;
        }
        else{
            if (insert_Node(this.tree_Root, insert_Node)){
                this.tree_Size++;
                return true;
            }
            return false;
        }
    }

    private boolean remove_Method() {
        return false;
    }

    boolean remove_Value (T value) {

        return false;
    }

}
