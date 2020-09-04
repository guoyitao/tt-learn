package tree.bst.impl;

import tree.AbstarctTreeNode;
import tree.biniary.BinNode;
import tree.biniary.TraverseCenterBinTreeTest;
import tree.bst.AbstractBSTree;


public class BSTree<T extends Comparable> extends AbstractBSTree<T> {


    public BSTree(AbstarctTreeNode<T> root) {
        super(root);
    }

    @Override
    public AbstarctTreeNode<T> search(T e) {
        return searchIn(root,e);
    }

    /**
     *  // O(高)
     * @author guoyitao
     * @date 2019/12/20 13:00
     * @params  x 当前（子）节点
     *          e 目标关键码 也就是key
     *          hot 记忆热点
     * @return
     */
    private AbstarctTreeNode<T> searchIn(AbstarctTreeNode<T> v, T e){

        if (v==null || e.compareTo(v.data)==0){ //失败或者成功
            return v;
        }

        this.hot = v; //记录当前非空节点

        if (e.compareTo(v.data)<0){
            return searchIn(v.hasLChild()?v.leftChild:null,e);
        }

        return searchIn(v.hasRChild()?v.rightChild:null,e);
    }


    /**
     * //O(高)
     * 添加：
     *
     *  搜索成功就说明元素存在，添加元素失败
     *
     *  hot的作用-----查找失败后用于记录要添加节点的父节点，然后再比一下大小就可以添加了
     * @author guoyitao
     * @date 2019/12/20 15:14
     * @params e要搜索的元素
     * @return 找到就是返回要搜索元素的节点，没找到就是null
     */
    @Override
    public AbstarctTreeNode<T> insert(T e) {
        AbstarctTreeNode<T> x = search(e);
        if (x==null){
            x = new BinNode<>((BinNode) hot(),e);
            if (e.compareTo(hot().data)<0){
                (hot()).insertAsLC(e);
            }else {
                (hot()).insertAsRC(e);
            }
            size++;
            updateHeightAbove(x);
            return x;
        }

        return null;
    }

    @Override
    public AbstarctTreeNode<T> hot() {
        return this.hot;
    }

    /**
     * @author guoyitao
     * @date 2019/12/20 15:26
     * @params
     * @return
     */
    @Override
    public boolean remove(T e) {

        AbstarctTreeNode<T> x = search(e);
        if (x==null){ 
           return false; //元素不存在
        }
        removeAt(x,hot());
        size++;
        updateHeightAbove(hot());
        return true;
    }

    /**
     *  O(h)
     * @author guoyitao
     * @date 2019/12/20 15:34
     * @params  hot 搜索命中节点的上一个节点 ,也就是x的父节点
     * @return
     */
    private AbstarctTreeNode<T> removeAt(AbstarctTreeNode<T> x, AbstarctTreeNode<T> hot) {

        AbstarctTreeNode<T> w = x;
        //成功删除的元素的接替者
        AbstarctTreeNode<T> succ = null;
        //记录从hot下一步的走向 true为left  false为right
        boolean b = w.data.compareTo(hot().data) < 0;

        //---------------------------------tree/bst/impl/删除元素的情况1.png 还可额外处理左右子树都为空
        if (!w.hasRChild()){ //右子树为空
            if (b){
                hot.leftChild = w.leftChild;
            }else {
                hot.rightChild = w.leftChild;
            }
            succ = w.leftChild;
        }else if (!w.hasLChild()){ //左子树为空
            if (b){
                hot.leftChild = w.rightChild;
            }else {
                hot.rightChild = w.rightChild;
            }
            succ = w.rightChild;
        //--------------------------------tree/bst/impl/删除元素的情况1.png
        }else { //-------------情况2 tree/bst/impl/删除元素情况2  有两个子叶的节点图1.png    tree/bst/impl/删除元素情况2  有两个子叶的节点图2.png

            w = w.succ(); //后继 tree/succ说明2.png
            swap(w,x); //两个节点的数据互换

            //后继的父节点，被替换元素的父节点
            AbstarctTreeNode<T> u = w.parent;

            //链接后继元素的right元素和后继元素的parent
            u.leftChild = w.rightChild;//后继元素只会存在right元素，如果不存在的话就已经被 情况1解决了
            w.rightChild.parent = u;
            succ = w.rightChild;


        }

        this.hot = w.parent;//记录被删除节点的父节点
        if (succ != null){
            succ.parent = hot();
        }


        //返回接替者
        return succ;

    }




    //1456910
    public static void main(String[] args) {
        BinNode<Integer> root = new BinNode<>(null,36);
        BSTree<Integer> bsTree = new BSTree<>(root);
        bsTree.insert(27);
        bsTree.insert(6);
        bsTree.insert(58);
        bsTree.insert(53);
        AbstarctTreeNode<Integer> insert64 = bsTree.insert(64);
        AbstarctTreeNode<Integer> insert40 = bsTree.insert(40);
        bsTree.insert(46);

        print(root);

        int stature = bsTree.stature(root);
        int stature40 = bsTree.stature(insert40);
        int stature64 = bsTree.stature(insert64);



        bsTree.remove(36);
        print(root);
        bsTree.remove(53);
        print(root);
        bsTree.remove(64);
        print(root);

        AbstarctTreeNode<Integer> search = bsTree.search(6);

    }

    private static void print(BinNode<Integer> root) {
        System.out.println("----before remove()----");
        new TraverseCenterBinTreeTest<Integer>().travPre_Itr(root);
    }


}
