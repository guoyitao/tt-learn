package tree.bst;

import tree.AbstarctTreeNode;
import tree.AbstractTree;
import tree.biniary.BinNode;

/**
 * 二叉搜索树
 * 描述：{
 * 顺序性：任意1节点的  左后代都比这个节点小，右后代都比这个节点大
 * 单调性：BST的中序遍历序列，必然单调非降(单调递增!!!)!!
 * 禁止重复词条
 * <p>
 * }
 *
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/12/20
 * @UpdateUser:
 */

public abstract class AbstractBSTree<T extends Comparable> extends AbstractTree<T> {

    /*
    * hot 代表的意义:
    * 在查找成功时：搜索命中节点的上一个节点
    * 在查找失败时：最后查找的节点
    * */
    public AbstarctTreeNode<T> hot;

    public AbstractBSTree(AbstarctTreeNode<T> root) {
        super(root);
    }

    public abstract AbstarctTreeNode<T>  search(T t);

    public abstract AbstarctTreeNode<T>  insert(T t);

    public abstract AbstarctTreeNode<T>  hot();

    public abstract boolean remove(T t);


    protected void swap(AbstarctTreeNode<T> node,AbstarctTreeNode<T> node2){
        T tmp = node.data;
        node.data = node2.data;
        node2.data = tmp;
    }

//    protected abstract Entry<T>  rotateAt(Entry<T> x);

}
