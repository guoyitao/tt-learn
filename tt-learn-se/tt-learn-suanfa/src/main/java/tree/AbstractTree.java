package tree;

import tree.biniary.BinNode;
import tree.biniary.BinTree;

public abstract class AbstractTree<T> {
    public int size; //规模
    public BinNode<T> root; //根节点

    public AbstractTree(BinNode<T> root) {
        size = 1;
        this.root = root;
    }


    /*
    * 更新节点x的高度
    * */
    protected int updateHeight(BinNode<T> binNode){
        binNode.height = 1 + Math.max(stature(binNode.leftChild), stature(binNode.rightChild));
        return binNode.height;
    }

    /*
    * 更新x及祖先的高度 O(n = binNode的深度)
    * */
    protected void updateHeightAbove(BinNode<T> binNode){
        while (true){
            updateHeight(binNode);
            if (binNode.parent != null){
                binNode = binNode.parent;
            }else {
                break;
            }
        }
    }

    public abstract BinNode<T> insertAsRC(BinNode<T> x, T t);

    public abstract BinNode<T> insertAsLC(BinNode<T> x, T t);

    public int getSize() {
        return size;
    }

    public boolean empty(){
        return root==null;
    }

    public BinNode<T> getRoot() {
        return root;
    }

    //高度 特殊情况
    private int stature(BinNode binNode){
        if (binNode == null){
            return -1;
        }
        if (binNode.parent != null && binNode.rightChild ==null && binNode.leftChild == null){
            return 0;
        }else if (binNode.parent == null && binNode.rightChild ==null && binNode.leftChild == null){
            return -1;
        }
        return binNode.height;

    }
}
