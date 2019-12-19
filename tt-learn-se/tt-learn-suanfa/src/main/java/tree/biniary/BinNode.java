package tree.biniary;

public class BinNode<T> {
    public BinNode parent;
    public BinNode leftChild;
    public BinNode rightChild;

    public T data;

    public int height;



    public BinNode(BinNode parent, T data) {
        this.parent = parent;
        this.data = data;
    }

    //O(n)
    public int size() {
        int s = 1;
        if (leftChild!= null){
            s += leftChild.size();
        }

        if (rightChild!= null){
            s += rightChild.size();
        }
        return s;
    }

    //最为左孩子插入行节点O(1)
    public BinNode<T> insertAsLC(T t){
         leftChild = new BinNode<T>(this,t);
         return leftChild;
    }
    //作为右孩子插入新节点O(1)
    public BinNode<T> insertAsRC(T t){
        rightChild = new BinNode<T>(this,t);
        return rightChild;
    }

    public boolean hasRChild(){
        if (rightChild == null){
            return false;
        }
        return true;
    }

    public boolean hasLChild(){
        if (leftChild == null){
            return false;
        }
        return true;
    }

    public T visit(){
        System.out.println(this.toString());
        return this.data;
    }

    @Override
    public String toString() {

        String parentResult ="parent = null";
        if (parent!= null){
            parentResult = "parent=" + parent.data;
        }

        String leftChildResult = "leftChild = null";
        if (leftChild!= null){
            leftChildResult = "leftChild=" + leftChild.data;
        }

        String rightChildResult = "rightChild = null";
        if (rightChild!= null){
            rightChildResult = "rightChild=" + rightChild.data;
        }

        return "BinNode{" + parentResult + ", " + leftChildResult + ", " + rightChildResult + ", data=" + data + ", height=" + height + '}';
    }

    public static void main(String[] args) {
        BinNode<Integer> root = new BinNode<>(null, 1);

        BinNode<Integer> lc1 = root.insertAsLC(2);
        root.insertAsRC(3);

        lc1.insertAsLC(4);
        lc1.insertAsRC(5);

        System.out.println( "size：" +root.size());

    }

}
