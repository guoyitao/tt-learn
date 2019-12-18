package tree;

public class BinTree<T> {

    int size; //规模

    BinNode<T> root; //根节点

    public BinTree(BinNode<T> root) {
        this.root = root;
        size = 1;
    }

    /*
    * 更新节点x的高度
    * */
    protected int updateHeight(BinNode<T> binNode){
        binNode.height = 1 + Math.max(stature(binNode.leftChild), stature(binNode.rightChild));
        return binNode.height;
    }

    /*
    * 更新x及祖先的高度 O(n = binNode的深度)
    * */
    private void updateHeightAbove(BinNode<T> binNode){
        while (true){
            updateHeight(binNode);
            if (binNode.parent != null){
                binNode = binNode.parent;
            }else {
                break;
            }
        }
    }

    public BinNode<T> insertAsRC(BinNode<T> x,T t){
        size++;
        x.insertAsRC(t);
        updateHeightAbove(x);
        return x.rightChild;
    }

    public BinNode<T> insertAsLC(BinNode<T> x,T t){
        size++;
        x.insertAsLC(t);
        updateHeightAbove(x);
        return x.leftChild;
    }

    public int getSize() {
        return size;
    }

    public boolean empty(){
        return root==null;
    }

    public BinNode<T> getRoot() {
        return root;
    }

    //高度 特殊情况
    private int stature(BinNode binNode){
        if (binNode == null){
            return -1;
        }
        if (binNode.parent != null && binNode.rightChild ==null && binNode.leftChild == null){
            return 0;
        }else if (binNode.parent == null && binNode.rightChild ==null && binNode.leftChild == null){
            return -1;
        }
        return binNode.height;

    }



    public static void main(String[] args) {
//        BinNode<Integer> root = new BinNode<>(null, 1);
//
//        BinNode<Integer> lc1 = root.insertAsLC(2);
//        root.insertAsRC(3);
//
//        lc1.insertAsLC(4);
//        lc1.insertAsRC(5);
//
//        System.out.println( "size：" +root.size());
//
//        BinTree<Integer> integerBinTree = new BinTree<Integer>();
//        System.out.println(integerBinTree.updateHeight(root));
        BinNode<Integer> integerBinNode = new BinNode<>(null, 1);
        BinTree<Integer> binTree = new BinTree<>(integerBinNode);
        BinNode<Integer> rc1 = binTree.insertAsRC(integerBinNode, 2);
        binTree.insertAsLC(integerBinNode,3);

        BinNode<Integer> rc2 = binTree.insertAsLC(rc1, 4);
        binTree.insertAsLC(rc2,5);




    }

}
