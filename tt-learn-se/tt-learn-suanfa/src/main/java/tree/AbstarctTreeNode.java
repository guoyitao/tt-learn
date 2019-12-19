package tree;

import tree.biniary.BinNode;

public class AbstarctTreeNode<T> {
    public BinNode parent;
    public BinNode leftChild;
    public BinNode rightChild;
    public T data;
    public int height;

    public AbstarctTreeNode(BinNode parent, T data) {
        this.parent = parent;
        this.data = data;
    }

    //O(n)
    public int size() {
        int s = 1;
        if (leftChild!= null){
            s += leftChild.size();
        }

        if (rightChild!= null){
            s += rightChild.size();
        }
        return s;
    }

    public boolean hasRChild(){
        if (rightChild == null){
            return false;
        }
        return true;
    }

    public boolean hasLChild(){
        if (leftChild == null){
            return false;
        }
        return true;
    }

    public T visit(){
        System.out.println(this.toString());
        return this.data;
    }
}
