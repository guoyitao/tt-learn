package tree.bst;

import tree.AbstarctTreeNode;
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
 * @CreateDate: 2019/5/5
 * @UpdateUser:
 */



public class Entry<K extends Comparable,T extends Comparable> extends AbstarctTreeNode<T> {
    public K key; //关键码


    public Entry( K key,T data) {
        super(data);
        this.key = key;
    }

    public Entry(Entry<K,T> entry){
        super(entry.data);
        this.key = entry.key;

    }

    public Entry(AbstarctTreeNode parent, T data) {
        super(parent, data);
    }


    public boolean isBiggerThan(Entry<K,T> e) {
        return this.key.compareTo( e.key)>0;
    }

    public boolean isSmallerThan(Entry<K,T> e) {
        return this.key.compareTo( e.key)<0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Entry) {
            Entry<K,T> entry = (Entry<K, T>) obj;

            boolean compare = this.key.compareTo(entry.key) == 0;
            if (compare){
                return compare;
            }

            boolean equals = this.key.equals(entry.key);
            if (equals){
                return equals;
            }
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public BinNode<T> insertAsLC(T t) {
        return null;
    }

    @Override
    public BinNode<T> insertAsRC(T t) {
        return null;
    }
}



