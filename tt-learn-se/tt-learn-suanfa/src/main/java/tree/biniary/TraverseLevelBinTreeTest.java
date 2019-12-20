package tree.biniary;


import tree.AbstarctTreeNode;

import java.util.LinkedList;

/**
 * @Description: 层次遍历
 * @Author: guo
 * @CreateDate: 2019/12/19
 * @UpdateUser:
 */
public class TraverseLevelBinTreeTest<T extends Comparable> {

    /*

     *
     * 层次遍历非递归实现   O（n）
     * */
    public void travPre_Itr(AbstarctTreeNode<T> x){
        LinkedList<AbstarctTreeNode<T>> queue = new LinkedList<>();

        queue.addFirst(x); //根节点入队
        while (!queue.isEmpty()){
            AbstarctTreeNode<T> poll = queue.removeLast();
            poll.visit();
            if (poll.hasLChild()){
                queue.addFirst( poll.leftChild); //左孩子入队
            }
            if (poll.hasRChild()){
                queue.addFirst( poll.rightChild); //右孩子入队
            }

        }

    }


    //end 13245
    public static void main(String[] args) {
        BinNode<Integer> root = buildBinTree();


        TraverseLevelBinTreeTest<Integer> treeTest = new TraverseLevelBinTreeTest<>();
        treeTest.travPre_Itr(root);


    }

    private static BinNode<Integer> buildBinTree() {
        BinNode<Integer> root = new BinNode<>(null, 1);
        BinTree<Integer> binTree = new BinTree<>(root);
        BinNode<Integer> rc1 = binTree.insertAsRC(root, 2);
        binTree.insertAsLC(root,3);
        BinNode<Integer> rc2 = binTree.insertAsLC(rc1, 4);
        binTree.insertAsLC(rc2,5);
        return root;
    }
}
