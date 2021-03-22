package tree.balance;


import tree.AbstarctTreeNode;
import tree.biniary.BinNode;
import tree.bst.impl.BSTree;

/**
 * @Description: 高度： tree.AbstractTree里也说了
 * 一个节点 h=0
 * null h=-1
 * <p>
 * <p>
 * height(AVL) = O(logn)
 * 平衡因子计算 =  height(leftChild()) - height(rightChild())
 * 平衡 = |平衡因子计算| <= 1
 * 高度为h的AVL树，至少包含S（h） =  fib(h+3) -1个节点
 * <p>
 * AVL树刚插入一个节点后失衡节点最多为O(logn)  肯能引起整体分支失衡
 * AVL树刚删除一个节点后失衡节点最多为O(1)
 * 因为-被删除的节点数据被删除节点父节点最短的分支，高度是由最长分支决定的
 * @Author: guo
 * @CreateDate: 2019/12/20
 * @UpdateUser:
 */
public class AVLTree<T extends Comparable<T>> extends BSTree<T> {

    public AVLTree(AbstarctTreeNode<T> root) {
        super(root);
    }

    //理想平衡
    public boolean balanced(AbstarctTreeNode<T> x) {
        return stature(x.leftChild) == stature(x.rightChild);
    }

    //平衡因子
    public int balFac(AbstarctTreeNode<T> x) {
        return stature(x.leftChild) - stature(x.rightChild);
    }

    //avl平衡条件
    public boolean avlBalanced(AbstarctTreeNode<T> x) {
        return Math.abs(balFac(x)) < 2;
    }

    @Override
    public AbstarctTreeNode<T> insert(T e) {
        AbstarctTreeNode<T> x = search(e);
        if (x != null) { //如果存在元素就返回添加失败
            return null;
        }
        //基本的插入元素
        x = new BinNode<>(hot(), e);
        //记录从hot下一步的走向 true为left  false为right
        boolean b = x.data.compareTo(hot().data) < 0;
        if (b){
            hot().leftChild = x;
        }else {
            hot().rightChild = x;
        }
        size++;

        AbstarctTreeNode<T> xx = x;

        for (AbstarctTreeNode<T> g = x.parent; g != null; g = g.parent) {
            if (!avlBalanced(g)){ //如果g失去平衡，则通过调整恢复 g必然是所有失衡元素的最低者
//TODO 旋转
                break;
            }else {
                updateHeight(g);
            }
        }

        return xx;
    }

    @Override
    public boolean remove(T e) {
        return super.remove(e);
    }
}
