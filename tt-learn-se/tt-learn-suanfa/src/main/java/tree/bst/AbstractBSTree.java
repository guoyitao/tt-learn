package tree.bst;

import tree.AbstarctTreeNode;
import tree.AbstractTree;
import tree.biniary.BinNode;

/**
 * 二叉搜索树
 * 描述：{
 * 顺序性：任意1节点的  左后代都比这个节点小，右后代都比这个节点大
 * 单调性：BST的中序遍历序列，必然单调非降(单调递增)
 * 禁止重复词条
 * <p>
 * }
 *
 * @Description:
 * @Author: guo
 * @CreateDate: 2019/12/20
 * @UpdateUser:
 */

public abstract class AbstractBSTree<T> extends AbstractTree<T> {


    public AbstractBSTree(AbstarctTreeNode<T> root) {
        super(root);
    }

    public abstract Entry<T>  search(T t);

    public abstract Entry<T>  insert(T t);

    public abstract Entry<T>  hot();

    protected abstract Entry<T>  rotateAt(Entry<T> x);

}
