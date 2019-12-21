package tree.biniary;

import tree.AbstarctTreeNode;

public class BinNode<T extends Comparable> extends tree.AbstarctTreeNode<T> {


    public BinNode(AbstarctTreeNode parent, T data) {
        super(parent, data);
    }



    //最为左孩子插入行节点O(1)
    @Override
    public BinNode<T> insertAsLC(T t){
        this.leftChild = new BinNode<T>(this,t);
        return (BinNode<T>) leftChild;
    }
    //作为右孩子插入新节点O(1)
    @Override
    public BinNode<T> insertAsRC(T t){
        this.rightChild = new BinNode<T>(this,t);
        return (BinNode<T>) rightChild;
    }

    @Override
    public String toString() {

        String parentResult ="parent = null";
        if (parent!= null){
            parentResult = "parent=" + parent.data;
        }

        String leftChildResult = "leftChild = null";
        if (leftChild!= null){
            leftChildResult = "leftChild=" + leftChild.data;
        }

        String rightChildResult = "rightChild = null";
        if (rightChild!= null){
            rightChildResult = "rightChild=" + rightChild.data;
        }

        return "BinNode{" + parentResult + ", " + leftChildResult + ", " + rightChildResult + ", data=" + data + ", height=" + height + '}';
    }


    public static void main(String[] args) {
        BinNode<Integer> root = new BinNode<>(null, 1);

        BinNode<Integer> lc1 = root.insertAsLC(2);
        root.insertAsRC(3);

        lc1.insertAsLC(4);
        lc1.insertAsRC(5);

        System.out.println( "size：" +root.size());

    }

}
