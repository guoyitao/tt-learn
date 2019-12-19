package tree.biniary;


import java.util.Stack;

/**
 * @Description: 左中右
 * @Author: guo
 * @CreateDate: 2019/12/19
 * @UpdateUser:
 */
public class TraverseCenterBinTreeTest<T> {



    //中序列，递归遍历 O（n）
    public  void traverse(BinNode<T> x){
        if (x== null){
            return;
        }

        traverse(x.leftChild); //左
        T visit = x.visit();
        traverse(x.rightChild); //右
    }

    /*
     * 从根节点开始访问，一直往左侧链下行
     * 直到左侧链底部然后访问左侧链，把控制权转交给左侧链底部的右节点，
     * 左侧链底部的右节点 访问完毕之后就自下而上的访问左侧链的倒数第二个然后访问左侧链倒数第二个节点的右节点..... O(n)
     * 整体呈自下而上，从左到右的访问方式
     *
     * 中序遍历非递归实现   O（n）
     * */
    public void travPre_Itr(BinNode<T> x){
        Stack<BinNode<T>> stack = new Stack<>();

        while (true){
            goAlongLeftBranch(x,stack); //从当前节点出发，逐批入栈go不访问
            if (stack.empty()){break;} //退出条件
            x = stack.pop();//从栈顶也就是 从树下面往上访问
            x.visit();

            x = x.rightChild; //可能为空 在goAlongLeftBranch 的while（）有判空手法
        }

    }

    //stack 的口朝下
    private void goAlongLeftBranch(BinNode<T> x, Stack<BinNode<T>> stack){
        while (x != null){ //一直走左孩子直到没有左孩子
            stack.push(x); // 左孩子入栈 ，将来会逆序从树的下面往上访问
            if (x.hasLChild()) {
                x = x.leftChild;
            }else {
                break;
            }
        }
    }

    //end 31542
    public static void main(String[] args) {
        BinNode<Integer> root = buildBinTree();


        TraverseCenterBinTreeTest<Integer> treeTest = new TraverseCenterBinTreeTest<>();
        treeTest.travPre_Itr(root);
//        treeTest.traverse(root);


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
