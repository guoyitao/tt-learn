package tree.biniary;


import tree.AbstarctTreeNode;

import java.util.Stack;

public class TraversePreBinTreeTest<T extends Comparable> {



    //先序列，递归遍历 O（n）
    public  void traverse(AbstarctTreeNode<T> x){
        if (x== null){
            return;
        }
        x.visit();
        traverse(x.leftChild); //左
        traverse(x.rightChild); //右
    }

    /*
    * 从根节点开始访问，先左后右 O(n)
    * 先序遍历非递归实现 1
    * */
    public void travPre_Itr1(AbstarctTreeNode<T> x){
        Stack<AbstarctTreeNode<T>> stack = new Stack<>();
        if (x != null) {
            stack.push(x);
        }
        while (!stack.empty()){
            x = stack.pop();
            x.visit();
            if (x.hasRChild()){
                stack.push(x.rightChild);  //右先进
            }
            if (x.hasLChild()){
                stack.push(x.leftChild);  //左进入
            }
        }
    }


    /*
     * 从根节点开始访问，如何访问看图二叉树非递归先序遍历travPre_Itr2.png O(n)
     * 这个算法可类似的用于中序和层次遍历算法
     * 先序遍历非递归实现 2 O（n） ---> 因为每个节点只发生一次出入栈操作 n^2是假象
     * */
    public void travPre_Itr2(AbstarctTreeNode<T> x){
        Stack<AbstarctTreeNode<T>> stack = new Stack<>();

        while (true){
            visitAlongLeftBranch(x,stack);
            if (stack.empty()){break;}
            x = stack.pop();
        }

    }

    private void visitAlongLeftBranch(AbstarctTreeNode<T> x,Stack<AbstarctTreeNode<T>> stack){
        while (x!=null){ //一直访问左孩子直到没有左孩子
            T visit = x.visit();// 访问当前节点

            if (x.hasRChild()){
                stack.push(x.rightChild); // 右孩子入栈 ，将来会逆序从树的下面往上访问
            }

            if (x.hasLChild()) {
                x = x.leftChild;  //沿着左侧下行直到没有
            }else{
                x = null;
            }
        }
    }


    private T visit(AbstarctTreeNode<T> binNode){
        System.out.println(binNode.toString());
        return binNode.data;
    }


    // 13245
    public static void main(String[] args) {
        BinNode<Integer> root = buildBinTree();


        TraversePreBinTreeTest<Integer> treeTest = new TraversePreBinTreeTest<>();
//        treeTest.traverse(root);
//
        treeTest.travPre_Itr1(root);
//        treeTest.travPre_Itr2(root);

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
