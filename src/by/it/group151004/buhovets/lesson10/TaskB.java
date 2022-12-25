package by.it.group151004.buhovets.lesson10;

import by.it.group151004.buhovets.lesson04.C_GetInversions;
import com.sun.source.tree.Tree;

import java.util.*;


//AVLTree implementation
public class TaskB<E>  implements NavigableSet<E> {

    //Создайте БЕЗ использования других классов (включая абстрактные)
    //аналог дерева TreeSet
    //Assume, E is comparable
    private enum SonType {Right, Left, Random};
    static final private int RightKey = 0b01;
    static final private int LeftKey = 0b10;

    static final private int LeftLeftPath = (LeftKey << 2) | LeftKey;
    static final private int LeftRightPath = (RightKey << 2) | LeftKey;
    static final private int RightRightPath = (RightKey << 2) | RightKey;
    static final private int RightLeftPath = (LeftKey << 2) | RightKey;
    interface DummyNode<E> {
        void clear();
        E getValue();
    }
    private  class TreeNode implements  DummyNode<E>{
        E value;
        int height;
        TreeNode rSon, lSon;
        TreeNode father;
        TreeNode(E value){
            this.value = value;
        }
        TreeNode(E value, TreeNode father){
            this.value = value;
            this.father = father;
        }


        public void clear(){
            lSon = null;
            rSon = null;
            father = null;
            value = null;
        }
        public E getValue(){
            return value;
        }
        //true -> father should be changed
        //false -> this stable
        public void updateHeight(){
            int lHeight = (lSon == null) ? -1 : lSon.height;
            int rHeight = (rSon == null) ? -1 : rSon.height;
            this.height = Math.max(lHeight, rHeight) + 1;
        }
        boolean isBalanced() {
            updateHeight();
            int lHeight, rHeight;
            lHeight = (lSon == null) ? -1 : lSon.height;
            rHeight = (rSon == null) ? -1 : rSon.height;

            return Math.abs(lHeight - rHeight) <= 1;
        }
        public int compareTo(E value){
            if(comparator == null){
                Objects.requireNonNull(value);
                return ((Comparable<? super E>) this.value).compareTo(value);
            }
            return comparator.compare(this.value, value);
        }
        //child should be exists
        public int getLastKey(TreeNode child){
            return (this.lSon == child) ? LeftKey : RightKey;
        }
        public void add(TreeNode child, SonType order){
            switch(order){
                case Right -> rSon = child;
                case Left -> lSon = child;
                default -> throw new IllegalStateException("Unknown son type");
            }
            updateHeight();
        }
        public TreeNode getSon(SonType type){
            switch(type){
                case Right -> {
                    return this.rSon;
                }
                case Left -> {
                    return this.lSon;
                }
                case Random -> {
                    return (this.lSon == null) ? rSon : lSon;
                }
                default -> throw new IllegalStateException("Unknown son type");
            }
        }
        //successor is some child of this node
        public void swapFather(TreeNode successor){

            if(this.father.lSon == this)
                this.father.lSon = successor;
            else
                this.father.rSon = successor;
            if(successor != null)
                successor.father = this.father;
            this.father = successor;
        }
        public void alignChildren(){
            if(rSon != null)
                rSon.updateHeight();
            if(lSon != null)
                lSon.updateHeight();
            this.updateHeight();
        }
        public SonType getHesitatedSon(){
            if(isBalanced())
                return SonType.Random;
            int lHeight = (lSon == null) ? -1 : lSon.height;
            int rHeight = (rSon == null) ? -1 : rSon.height;
            return (lHeight > rHeight) ? SonType.Left : SonType.Right;
        }
        public boolean isLeaf(){
            return (this.lSon == null) && (this.rSon == null);
        }
        public boolean isStable() {
            int lHeight = (lSon == null) ? -1 : lSon.height;
            int rHeight = (rSon == null) ? -1 : rSon.height;
            return lHeight == rHeight;
        }
        public void replaceValue (TreeNode origin){
            this.value = origin.value;
        }
    }

    int pathKey;

    int nNodes;
    TreeNode root;
    TreeNode lastChild;
    TreeNode lastSuccessor;
    Comparator<E> comparator;

    //Обязательные к реализации методы и конструкторы
    public TaskB(Comparator<E> comparator) {
        this.comparator = comparator;
    }
    public TaskB(){}

    private void resetPathKey(){
        pathKey = 0;
    }
    private void updatePathKey(int lastKey) {
        pathKey = ((pathKey & 0b11) << 2) | lastKey;
    }
    private void rotateRightRight(TreeNode hesitated){
        TreeNode  successor = hesitated.rSon;
        TreeNode lChild = successor.lSon;
        successor.lSon = hesitated;
        hesitated.swapFather(successor);
        if(lChild != null)
            lChild.father = hesitated;
        hesitated.rSon = lChild;
        lastSuccessor = successor;
    }
    private void rotateLeftLeft(TreeNode hesitated){
        TreeNode successor = hesitated.lSon;
        TreeNode rChild = successor.rSon;
        successor.rSon = hesitated;
        hesitated.swapFather(successor);
        if(rChild != null)
            rChild.father = hesitated;
        hesitated.lSon = rChild;
        lastSuccessor = successor;
    }
    private void rotateRightLeft(TreeNode hesitated){
        TreeNode rChild = hesitated.rSon;
        TreeNode successor = rChild.lSon;

        successor.swapFather(successor.rSon);
        hesitated.rSon = successor;
        hesitated.swapFather(successor);

        hesitated.rSon = successor.lSon;
        if(hesitated.rSon != null)
            hesitated.rSon.father = hesitated;
        rChild.father = successor;

        successor.rSon = rChild;
        successor.lSon = hesitated;
        lastSuccessor = successor;
    }
    private void rotateLeftRight(TreeNode hesitated){
        TreeNode lChild = hesitated.lSon;
        TreeNode successor = lChild.rSon;

        successor.swapFather(successor.lSon);

        hesitated.swapFather(successor);

        hesitated.lSon = successor.rSon;
        if(hesitated.lSon != null)
            hesitated.lSon.father = hesitated;
        lChild.father = successor;

        successor.lSon = lChild;
        successor.rSon = hesitated;
        lastSuccessor = successor;
    }
    private void rotate(TreeNode hesitated){
        TreeNode dummyFather = null;
        int rotateType = 0;
        if(hesitated == root){
            dummyFather = new TreeNode(root.value);
            root.father = dummyFather;
            rotateType = 1; //root rotation
        }
        switch(pathKey){
            case LeftLeftPath -> {
                rotateLeftLeft(hesitated);
            }
            case LeftRightPath -> {
                rotateLeftRight(hesitated);
            }
            case RightLeftPath -> {
                rotateRightLeft(hesitated);
            }
            case RightRightPath -> {
                rotateRightRight(hesitated);
            }
            default -> System.out.println("Unknown keypath");
        }
        if(rotateType == 1){
            root = dummyFather.getSon(SonType.Random);
            root.father = null; // free memory for dummyFather
        }
    }
    private boolean insertChild(TreeNode currNode, E value){
        if((value == null) || (currNode.compareTo(value) == 0))
            return false;
        TreeNode child;
        boolean isAddable;
        SonType order = (currNode.compareTo(value) < 0) ? SonType.Right : SonType.Left;
        if(order == SonType.Left)
            isAddable = currNode.lSon == null;
        else
            isAddable = currNode.rSon == null;
        if(!isAddable){
            isAddable = insertChild(currNode.getSon(order), value);
        }
        else {
            child = new TreeNode(value, currNode);
            currNode.add(child, order);
            lastChild = child;
        }
        return isAddable;
    }
    private SonType convertType(int lastKey){
        return (lastKey == LeftKey) ? SonType.Left : SonType.Right;
    }
    private SonType reflectSon (SonType type){
        return (type == SonType.Left) ? SonType.Right : SonType.Left;
    }

    //used when hesitated is not balanced
    private boolean isCorrectPathKey(int lastKey, TreeNode hesitated){
        SonType hSon = hesitated.getHesitatedSon();
        return convertType(lastKey) == hSon;
    }
    private void restoreBalance(TreeNode child){
        TreeNode currNode = child;
        resetPathKey();
        int lastKey = 0;
        while(currNode.isBalanced() && (currNode.father != null)){
            currNode = child.father;
            lastKey = currNode.getLastKey(child);
            updatePathKey(lastKey);
            child = currNode;
        }
        if(!currNode.isBalanced()) {
            if(isFullPathKey()){
                if(isCorrectPathKey(lastKey, currNode)){
                    rotate(currNode);
                    lastSuccessor.alignChildren();
                    restoreBalance(lastSuccessor);
                }
                else {
                    SonType son = convertType(lastKey);
                    SonType otherSon = reflectSon(son);
                    restoreBalance(getLeaf(currNode, otherSon));
                }
            }
            else {
                SonType sonType = convertType(lastKey);
                if(isCorrectPathKey(lastKey, currNode))
                    restoreBalance(getLeaf(currNode, sonType));
                else
                    restoreBalance(getLeaf(currNode, reflectSon(sonType)));
            }

        }
    }
    private boolean addChild(TreeNode root, E value){
        boolean isExists = !(insertChild(root, value));
        if(isExists)
            return false;
        restoreBalance(lastChild);
        return true;
    }


    @Override
    public boolean add(E e) {
        boolean isAdded;
        if(root != null){
            isAdded = addChild(root, e);
        }
        else {
            root = new TreeNode(e);
            isAdded = true;
        }
        if(isAdded)
            nNodes += 1;
        return isAdded;
    }
    public TreeNode findNode(TreeNode node, E value){
        if(node == null || node.compareTo(value) == 0)
            return node;
        if(node.compareTo(value) < 0)
            return findNode(node.rSon, value);
        else
            return findNode(node.lSon, value);
    }

    public TreeNode getMin(TreeNode node){
        while(node.lSon != null)
            node = node.lSon;
        return node;
    }
    public TreeNode getLeaf(TreeNode node, SonType type){
        if(node.isLeaf())
            return node;
        if(type == SonType.Left && node.lSon == null)
            return node;
        if(type == SonType.Right && node.rSon == null)
            return node;
        node = node.getSon(type);
        while(!node.isLeaf()){
            type = node.getHesitatedSon();
            node = node.getSon(type);
        }
        return node;
    }
    public TreeNode getLeaf(TreeNode root){
        return getLeaf(root, SonType.Random);
    }
    public void removeTree(TreeNode root){
        TreeNode successor = getMin(root.rSon);
        TreeNode saved;
        if(root.rSon != successor){
            saved = successor.father;
            saved.lSon = successor.rSon;
            if(successor.rSon != null)
                successor.rSon.father = saved;
        }
        else {
            saved = successor.rSon;
            root.rSon = saved;
            if(saved != null){
                saved.father = root;
            }
            else {
                saved = root;
            }

        }
        root.replaceValue(successor);
        restoreBalance(getLeaf(saved));
    }

    private boolean isFullPathKey(){
        return (pathKey == LeftLeftPath) || (pathKey == RightRightPath) || (pathKey == RightLeftPath) || (pathKey == LeftRightPath);
    }
    public void removeLeaf(TreeNode leaf){
        TreeNode father = leaf.father;
        if(father.lSon == leaf)
            father.lSon = null;
        else
            father.rSon = null;
        TreeNode child = (father.isLeaf()) ? father : getLeaf(father);
        restoreBalance(child);
    }

    //type of son in subtree; not in the father
    public void removeSubtree(TreeNode node, SonType type){
        TreeNode father = node.father;
        TreeNode successor;
        if(type == SonType.Left)
            successor = node.lSon;
        else
            successor = node.rSon;

        if(father.lSon == node)
            father.lSon = successor;
        else
            father.rSon = successor;
        successor.father = node.father;
        restoreBalance(successor);
    }
    //return subtree

    //root should be set on dummyHeader temporaraly
    public void removeChild(TreeNode node){

        int deleteMode = 0; //leaf deleting
        SonType type = SonType.Left;
        if(node.lSon != null){
            deleteMode += 1;
        }
        if(node.rSon != null){
            deleteMode += 1;
            type = SonType.Right;
        }
        switch(deleteMode){
            case 0 -> removeLeaf(node);
            case 1 -> removeSubtree(node, type);
            case 2 -> removeTree(node);
        }

    }
    @Override
    public boolean remove(Object o) {
        if(o == null)
            return false;
        TreeNode result = findNode(root, (E)o);
        if(result == null)
            return false;
        if(result != root){
            removeChild(result);
        }
        else{
            TreeNode dummyHead;
            if(root.lSon == null || root.rSon == null){
                dummyHead = new TreeNode(root.value);
                root.father = dummyHead;
                removeChild(root);
                root = dummyHead.getSon(SonType.Random);
            }
            else {
                removeChild(root);
            }
            if(root != null)
                root.father = null;
        }
        nNodes -= 1;
        return true;
    }

    private StringBuilder strOutput;
    @Override
    public String toString() {
        if(root == null)
            return "[]";
        strOutput = new StringBuilder();
        strOutput.append("[");
        overInside(root, (node -> {
            strOutput.append(node.getValue().toString()).append(", ");
        }));
        strOutput.setLength(strOutput.length() - 2);
        strOutput.append("]");
        return strOutput.toString();
    }
    @Override
    public boolean contains(Object o) {
        if(o == null)
            return false; //null element is not allow
        return o.equals(findNode(root, (E) o).getValue());
    }

    private class TreeIterator implements Iterator<E> {

        TreeNode currNode;
        private TreeNode callBack(TreeNode node){
            if(node == null || node.father == null)
                return null;
            if(node.father.lSon == node)
                return node.father;

            while(node.father != null && node.father.rSon == node)
                node = node.father;
            return node.father;
        }

        @Override
        public boolean hasNext() {
            return (currNode != null);
        }

        @Override
        public E next() {
            if(currNode == null)
                return null;
            E result = currNode.value;
            if(currNode.rSon == null){
                currNode = callBack(currNode);
            }
            else {
                currNode = getMin(currNode.rSon);
            }
            return result;
        }
        TreeIterator(){
            if(root == null) {
                currNode = null;
            }
            else {
                currNode = getMin(root);
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new TreeIterator();
    }

    @FunctionalInterface
    public interface TreeAction {
         void action(DummyNode node);
    }
    private void overReverse(TreeNode root, TreeAction handle){
        if(root.lSon != null)
            overReverse(root.lSon, handle);
        if(root.rSon != null)
            overReverse(root.rSon, handle);
        handle.action(root);
    }
    private void overInside(TreeNode root, TreeAction handle){
        if(root.lSon != null)
            overInside(root.lSon, handle);
        handle.action(root);
        if(root.rSon != null)
            overInside(root.rSon, handle);
    }
    @Override
    public void clear() {
        if(root != null) {
            overReverse(root, DummyNode::clear);
            nNodes = 0;
            root = null;
        }

    }

    @Override
    public boolean isEmpty() {
        return nNodes == 0;
    }

    @Override
    public int size() {
        return nNodes;
    }

    @Override
    public E lower(E e) {
        return null;
    }

    @Override
    public E floor(E e) {
        return null;
    }

    @Override
    public E ceiling(E e) {
        return null;
    }

    @Override
    public E higher(E e) {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public NavigableSet<E> descendingSet() {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return null;
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        return null;
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        return null;
    }

    @Override
    public Comparator<? super E> comparator() {
        return null;
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return null;
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return null;
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return null;
    }

    @Override
    public E first() {
        return new TreeIterator().next();
    }

    @Override
    public E last() {
        if(root == null)
            return null;
        TreeNode node = root;
        while(node.rSon != null)
            node = node.rSon;
        return node.value;
    }
}
