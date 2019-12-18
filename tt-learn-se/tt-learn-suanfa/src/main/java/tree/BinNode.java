package tree;

import org.junit.Test;

import javax.swing.tree.TreeNode;
import java.util.TreeSet;

public class BinNode<T> {
    BinNode parent;
    BinNode leftChild;
    BinNode rightChild;

    T data;

    int height;



    public BinNode(BinNode parent, T data) {
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

    //最为左孩子插入行节点O(1)
    public BinNode<T> insertAsLC(T t){
         leftChild = new BinNode<T>(this,t);
         return leftChild;
    }
    //作为右孩子插入新节点O(1)
    public BinNode<T> insertAsRC(T t){
        rightChild = new BinNode<T>(this,t);
        return rightChild;
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
