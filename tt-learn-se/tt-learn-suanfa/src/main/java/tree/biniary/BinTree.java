package tree.biniary;



public class BinTree<T> extends tree.AbstractTree<T> {

    public BinTree(BinNode<T> root) {
        super(root);
    }


    @Override
    public BinNode<T> insertAsRC(BinNode<T> x, T t){
        size++;
        x.insertAsRC(t);
        updateHeightAbove(x);
        return x.rightChild;
    }


    @Override
    public BinNode<T> insertAsLC(BinNode<T> x, T t){
        size++;
        x.insertAsLC(t);
        updateHeightAbove(x);
        return x.leftChild;
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
