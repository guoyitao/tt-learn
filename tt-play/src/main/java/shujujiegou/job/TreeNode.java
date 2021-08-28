package shujujiegou.job;

public class TreeNode<T extends Comparable<T>> {

    private T data; // 数据必须是可比较的
    private TreeNode<T> parent; // 父结点
    private TreeNode<T> left; // 左子结点
    private TreeNode<T> right; // 右子结点

    TreeNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
        if (this.left != null) {
            this.left.parent = this;
        }
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
        if (this.right != null) {
            this.right.parent = this;
        }
    }

    public boolean isRoot() {
        // 是否是根节点
        return parent == null;
    }

    public boolean isLeaf() {
        // 是否是叶子节点，即没有子结点
        return left == null && right == null;
    }

    public boolean isLeftChild() {
        // 是否是其父结点的左子结点
        if (parent == null) {
            return false;
        }
        return this == parent.left;
    }

    public boolean isRightChild() {
        // 是否是其父结点的右子结点
        if (parent == null) {
            return false;
        }
        return this == parent.right;
    }

    @Override
    public String toString() {
        return "TreeNode [data=" + data +
                ", parent=" + (parent == null ? null : parent.data) +
                ", left=" + (left == null ? null : left.data) +
                ", right=" + (right == null ? null : right.data) + "]";
    }

}