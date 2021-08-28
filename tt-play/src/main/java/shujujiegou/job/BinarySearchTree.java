package shujujiegou.job;


import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {

    private TreeNode<T> root;
    private int ASL;

    public int getASL() {
        return ASL;
    }

    public void incrementASL(){
        ASL++;
    }

    /**
     * 将数组构建为树
     * @param datas 输入数据数组
     */
    public void buildTree(T[] datas) {
        for (int i = 0; i < datas.length; i++) {
            insert(datas[i]);
            incrementASL();
        }
    }

    /**
     * @param data 待查找的数据
     * @return 返回指向 data 数据的结点对象，不存在时返回 null
     */
    public TreeNode<T> get(T data) {
        TreeNode<T> current = root;
        while (current != null && data.compareTo(current.getData()) != 0) {
            incrementASL();
            if (data.compareTo(current.getData()) < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        return current;
    }

    /**
     * @return 返回最小数据的结点对象
     */
    public TreeNode<T> getMin() {
        return getMin(root);
    }

    private TreeNode<T> getMin(TreeNode<T> treeNode) {
        while (treeNode.getLeft() != null) {
            incrementASL();
            treeNode = treeNode.getLeft();
        }
        return treeNode;
    }

    /**
     * @return 返回最大数据的结点对象
     */
    public TreeNode<T> getMax() {
        return getMax(root);
    }

    private TreeNode<T> getMax(TreeNode<T> treeNode) {
        while (treeNode.getRight() != null) {
            incrementASL();
            treeNode = treeNode.getRight();
        }
        return treeNode;
    }

    /**
     * @param data 待查找的数据
     * @return 返回指向 data 后继者的结点对象
     */
    public TreeNode<T> getSuccessor(T data) {
        TreeNode<T> current = get(data);
        if (current == null) {
            return null;
        }
        // 如果结点右子树非空，则后继者一定在右子树最左结点
        if (current.getRight() != null) {
            return getMin(current.getRight());
        }
        // 否则沿上遍历，当 curent不再是parent的右子结点时，后继者一定出现在parent的父节点
        TreeNode<T> parent = current.getParent();
        while (parent != null && current.isRightChild()) {
            incrementASL();
            current = parent;
            parent = parent.getParent();
        }
        return parent;
    }

    /**
     * @param data 待查找的数据
     * @return 返回指向 data 前驱者的结点对象
     */
    public TreeNode<T> getPredecessor(T data) {
        TreeNode<T> current = get(data);
        if (current == null) {
            return null;
        }
        // 如果结点左子树非空，则前驱者一定在左子树最右结点
        if (current.getLeft() != null) {
            return getMax(current.getLeft());
        }
        // 否则沿上遍历，当 curent不再是parent的左子结点时，前驱者一定出现在parent的父节点
        TreeNode<T> parent = current.getParent();
        while (parent != null && current.isLeftChild()) {
            incrementASL();
            current = parent;
            parent = parent.getParent();
        }
        return parent;
    }

    /**
     * @param data 待插入的数据
     */
    public void insert(T data) {
        TreeNode<T> insertNode = new TreeNode<>(data);
        TreeNode<T> parent = null;
        TreeNode<T> current = root;
        while (current != null) { // 先找到要插入的位置
            incrementASL();
            parent = current;
            if (data.compareTo(current.getData()) < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        if (parent == null) {
            root = insertNode; // tree is empty
        } else if (insertNode.getData().compareTo(parent.getData()) < 0) {
            parent.setLeft(insertNode);
        } else {
            parent.setRight(insertNode);
        }
    }

    /**
     * @param data 待删除的数据
     * @return 删除是否成功。当数据不存在树中时删除失败
     */
    public boolean delete(T data) {
        TreeNode<T> deleteNode = get(data);
        if (deleteNode == null) {
            return false; // 不存在的结点
        }
        if (deleteNode.getLeft() == null) {
            transplant(deleteNode, deleteNode.getRight());
        } else if (deleteNode.getRight() == null) {
            transplant(deleteNode, deleteNode.getLeft());
        } else { // 存在两个子结点的情况
            // 先找到后继者
            TreeNode<T> successor = getMin(deleteNode.getRight());
            if (successor.getParent() != deleteNode) {
                transplant(successor, successor.getRight());
                successor.setRight(deleteNode.getRight());
            }
            transplant(deleteNode, successor);
            successor.setLeft(deleteNode.getLeft());
        }
        return true;
    }

    /**
     * 使用新结点替换旧结点
     * @param oldNode 旧结点
     * @param newNode 新结点
     */
    private void transplant(TreeNode<T> oldNode, TreeNode<T> newNode) {
        incrementASL();
        if (oldNode.getParent() == null) {
            root = newNode; // 旧结点是根节点的情况
        } else if (oldNode.isLeftChild()) {
            oldNode.getParent().setLeft(newNode);
        } else {
            oldNode.getParent().setRight(newNode);
        }
    }

    public void printTree() {
        inorderTraversal(root,null); // 二叉搜索树的中序遍历将会从小到大输出
    }

    /**
     * 中序遍历
     * @param root 根结点
     */
    public void inorderTraversal(TreeNode<T> root,List<T> list) {
        if (root == null) return;
        inorderTraversal(root.getLeft(),list);
        if (list != null){
            incrementASL();
            list.add(root.getData());
        }
        inorderTraversal(root.getRight(),list);
    }

    public List<T> inorderTraversal(){
        List<T> list = new ArrayList<>();
        inorderTraversal(root,list);
        return list;
    }


}
