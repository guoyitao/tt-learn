package tree.balance;


import tree.AbstarctTreeNode;
import tree.bst.impl.BSTree;

/**
 * @Description:
 *      高度： tree.AbstractTree里也说了
 *          一个节点 h=0
 *          null h=-1
 *
 *
 *      height(AVL) = O(logn)
 *      平衡因子计算 =  height(leftChild()) - height(rightChild())
 *      平衡 = |平衡因子计算| <= 1
 *      高度为h的AVL树，至少包含S（h） =  fib(h+3) -1个节点
 *
 *      AVL树刚插入一个节点后失衡节点最多为O(logn)
 *      AVL树刚删除一个节点后失衡节点最多为O(1)
 * @Author: guo
 * @CreateDate: 2019/12/20
 * @UpdateUser:
 */
public  class AVLTree<T extends Comparable> extends BSTree<T> {

    public AVLTree(AbstarctTreeNode<T> root) {
        super(root);
    }

    //理想平衡
    public boolean balanced(AbstarctTreeNode<T> x){
        return stature(x.leftChild) == stature(x.rightChild);
    }
    //平衡因子
    public int balFac(AbstarctTreeNode<T> x){
        return stature(x.leftChild) - stature(x.rightChild);
    }
    //avl平衡条件
    public boolean avlBalanced(AbstarctTreeNode<T> x){
        return Math.abs(balFac(x))<2;
    }

    @Override
    public AbstarctTreeNode<T> insert(T e) {
        return super.insert(e);
    }

    @Override
    public boolean remove(T e) {
        return super.remove(e);
    }
}
