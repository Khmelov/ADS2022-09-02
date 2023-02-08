package by.it.group151002.bybikov.lesson10;

public class Binary_Tree<T extends Comparable<T>> {
    private Binary_Tree_Node<T> tree_Root;
    private int length;
    private StringBuilder to_String_Result;
    Binary_Tree() {
        this.length = 0;
        this.tree_Root = null;
    }

    private boolean private_Contains_Method(Binary_Tree_Node<T> current_Node, T value) {
        if (current_Node == null){
            return false;
        }
        if (value.compareTo(current_Node.getTop_Value()) < 0){
            return private_Contains_Method(current_Node.getLeft(), value);
        }
        if(value.compareTo(current_Node.getTop_Value()) > 0){
            return private_Contains_Method(current_Node.getRight(), value);
        }
        return current_Node.getTop_Value().equals(value);
    }

    boolean contains(Object value) {
        if (value == null){
            throw new NullPointerException("TreeSet doesn't apply null objects as parameters");
        }
        return private_Contains_Method(this.tree_Root, (T)value);
    }

    boolean is_Empty() {
        return this.length == 0;
    }

    int size(){
        return this.length;
    }

    private void private_Insert_Method(Binary_Tree_Node<T> parent_Node, Binary_Tree_Node<T> insert_Node) {
        if (insert_Node.getTop_Value().compareTo(parent_Node.getTop_Value()) < 0){
            if (parent_Node.getLeft() == null){
                parent_Node.setLeft(insert_Node);
                insert_Node.setParent(parent_Node);
            }
            else {
                private_Insert_Method(parent_Node.getLeft(), insert_Node);
            }
        }
        else if(insert_Node.getTop_Value().compareTo(parent_Node.getTop_Value()) > 0){
            if (parent_Node.getRight() == null){
                parent_Node.setRight(insert_Node);
                insert_Node.setParent(parent_Node);
            }
            else {
                private_Insert_Method(parent_Node.getRight(), insert_Node);
            }
        }
    }

    boolean insert_Value(T value) {
        if (value == null){
            throw new NullPointerException("TreeSet doesn't apply null objects as parameters");
        }
        if (contains(value)){
            return false;
        }
        Binary_Tree_Node<T> insert_Node = new Binary_Tree_Node<>(value);
        if (is_Empty()){
            this.tree_Root = insert_Node;
        }
        else {
            private_Insert_Method(this.tree_Root, insert_Node);
        }
        this.length++;
        return true;
    }

    private Binary_Tree_Node<T> get_Node_By_Top_Value(Binary_Tree_Node<T> current_Node, T value) {
        if (value.compareTo(current_Node.getTop_Value()) < 0){
            return get_Node_By_Top_Value(current_Node.getLeft(), value);
        }
        if(value.compareTo(current_Node.getTop_Value()) > 0){
            return get_Node_By_Top_Value(current_Node.getRight(), value);
        }
        if (value.equals(current_Node.getTop_Value())){
            return current_Node;
        }
        return null;
    }

    private void private_Remove_Method(T value) {
        Binary_Tree_Node<T> remove_Node = get_Node_By_Top_Value(this.tree_Root, value);
        Binary_Tree_Node<T> parent_Remove_Node = remove_Node.getParent();
        Binary_Tree_Node<T> exchange_Node = remove_Node.getRight();
        if (exchange_Node == null){
            exchange_Node = remove_Node.getLeft();
            if (parent_Remove_Node == null){
                this.tree_Root = exchange_Node;
            }
            else {
                if (remove_Node.getTop_Value().compareTo(parent_Remove_Node.getTop_Value()) < 0) {
                    parent_Remove_Node.setLeft(exchange_Node);
                    exchange_Node.setParent(parent_Remove_Node);
                }
                if (remove_Node.getTop_Value().compareTo(parent_Remove_Node.getTop_Value()) > 0) {
                    parent_Remove_Node.setRight(exchange_Node);
                    exchange_Node.setParent(parent_Remove_Node);
                }
            }
            remove_Node = null;
        }
        else {
            while (exchange_Node.getLeft() != null){
                exchange_Node = exchange_Node.getLeft();
            }
            Binary_Tree_Node<T> parent_Exchange_Node = exchange_Node.getParent();
            if (exchange_Node.getTop_Value().compareTo(parent_Exchange_Node.getTop_Value()) > 0) {
                parent_Exchange_Node.setRight(exchange_Node.getRight());
            }
            if (exchange_Node.getTop_Value().compareTo(parent_Exchange_Node.getTop_Value()) < 0) {
                parent_Exchange_Node.setLeft(exchange_Node.getRight());
            }
            if (exchange_Node.getRight() != null) {
                exchange_Node.getRight().setParent(parent_Exchange_Node);
            }
            remove_Node.setTop_Value(exchange_Node.getTop_Value());
            exchange_Node = null;

        }
    }

    boolean remove_Value (Object value) {
        if (value == null){
            throw new NullPointerException("TreeSet doesn't apply null objects as parameters");
        }
        if (!contains(value)){
            return false;
        }
        if (this.size() == 1){
            this.tree_Root = null;
        }
        else {
            private_Remove_Method((T) value);
        }
        this.length--;
        return true;
    }

    private void private_To_String_Method(Binary_Tree_Node<T> current_Node) {
        if (current_Node == null){
            return;
        }
        if (current_Node.getLeft() != null){
            private_To_String_Method(current_Node.getLeft());
        }
        to_String_Result.append(current_Node.getTop_Value());
        to_String_Result.append(", ");
        if (current_Node.getRight() != null){
            private_To_String_Method(current_Node.getRight());
        }
    }

    @Override
    public String toString() {
        to_String_Result = new StringBuilder();
        to_String_Result.append('[');
        if (!this.is_Empty()) {
            private_To_String_Method(tree_Root);
            to_String_Result.delete(to_String_Result.length() - 2, to_String_Result.length());
        }
        to_String_Result.append(']');
        return to_String_Result.toString();
    }
}
