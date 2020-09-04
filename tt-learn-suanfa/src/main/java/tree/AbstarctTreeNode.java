package tree;

import tree.biniary.BinNode;

public abstract class AbstarctTreeNode<T extends Comparable> {
    public AbstarctTreeNode<T> parent;
    public AbstarctTreeNode<T> leftChild;
    public AbstarctTreeNode<T> rightChild;
    public T data;
    public int height;

    public AbstarctTreeNode(T data) {
        this.data = data;
    }

    public AbstarctTreeNode(AbstarctTreeNode<T> parent, T data) {
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
    //这个节点的right节点吗
    public boolean isRChild(){
        if (parent.hasRChild() && parent.rightChild == this){
            return true;
        }
        return false;
    }
    //这个节点的left节点吗
    public boolean isLChild(){
        if (parent.hasLChild() && parent.leftChild == this){
            return true;
        }
        return false;
    }

    //中继遍历下当前节点的直接后继
    //搜索树中的removeAt需要用到这个
    //tree/succ说明2.png
    public AbstarctTreeNode<T> succ(){
        AbstarctTreeNode<T> s = this;
        if (this.hasRChild()){ //根据中继遍历如果有有孩子，那后继必造右子树
            s = rightChild; //在右子树中
            while (s.hasLChild()){
                s = s.leftChild; //右子树中最靠左的节点
            }
        }else { //图解 tree/succ说明2.png
            while (this.isRChild()) {
                s = s.parent;
            }
            s = s.parent;
        }
        return s; //可能为null
    };


    public T visit(){
        System.out.println(this.toString());
        return this.data;
    }

    //最为左孩子插入行节点O(1)
    public abstract BinNode<T> insertAsLC(T t);

    //作为右孩子插入新节点O(1)
    public abstract BinNode<T> insertAsRC(T t);
}
