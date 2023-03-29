public class TreeNode<T>{
    public T element;
    public TreeNode<T> root;
    public TreeNode<T> left;
    public TreeNode<T> right;


    public TreeNode<T> getRoot() {
        return root;
    }

    public void setRoot(TreeNode<T> root) {
        this.root = root;
    }
    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }


    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
}
