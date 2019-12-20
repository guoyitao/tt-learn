package tree;

import tree.biniary.BinNode;
import tree.biniary.BinTree;

public abstract class AbstractTree<T extends Comparable> {
    public int size; //规模
    public AbstarctTreeNode<T> root; //根节点

    public AbstractTree(AbstarctTreeNode<T> root) {
        size = 1;
        this.root = root;
    }



    /*
    * 更新节点x的高度
    * */
    protected int updateHeight(AbstarctTreeNode<T> node){
        node.height = 1 + Math.max(stature(node.leftChild), stature(node.rightChild));
        return node.height;
    }

    /*
    * 更新x及祖先的高度 O(n = binNode的深度)
    * */
    protected void updateHeightAbove(AbstarctTreeNode<T> node){
        while (true){
            updateHeight(node);
            if (node.parent != null){
                node = node.parent;
            }else {
                break;
            }
        }
    }

    public int getSize() {
        return size;
    }

    public boolean empty(){
        return root==null;
    }

    public AbstarctTreeNode<T> getRoot() {
        return root;
    }

    //高度 特殊情况
    private int stature(AbstarctTreeNode node){
        if (node == null){
            return -1;
        }
        if (node.parent != null && node.rightChild ==null && node.leftChild == null){
            return 0;
        }else if (node.parent == null && node.rightChild ==null && node.leftChild == null){
            return -1;
        }
        return node.height;

    }
}
