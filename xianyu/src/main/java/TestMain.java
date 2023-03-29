public class TestMain {
    public static void main(String[] args) {
        TreeNode<String> root = init();
        BaseBinaryTree<String> baseBinaryTree = new BaseBinaryTree<>();
        baseBinaryTree.root = root;

        //start test  Preorder
        System.out.println("start test  Preorder");
        TreeIterator<String> iterator = new TreeIterator<>(baseBinaryTree);
        iterator.setPreorder();
        while (iterator.hasNext()) {
            System.out.print(iterator.next()+ "  ");
        }
        System.out.println("\nend test  Preorder\n");

        System.out.println("start test Inorder");
        iterator.setInorder();
        while (iterator.hasNext()) {
            System.out.print(iterator.next()+ "  ");
        }
        System.out.println("\nend test Inorder\n");

        System.out.println("start test Preorder");
        iterator.setPreorder();
        while (iterator.hasNext()) {
            System.out.print(iterator.next()+ "  ");
        }
        System.out.println("\nend test Preorder\n");

        System.out.println("start test delete");
        iterator.setPreorder();
        while (iterator.hasNext()) {
//            System.out.print(iterator.next()+ "  ");
            iterator.remove();
        }
        System.out.println("\nend test delete\n");
    }

    private static TreeNode<String> init() {
        TreeNode<String> root = new TreeNode<>();
        root.element = "root";
        TreeNode<String> l1 = new TreeNode<>();
        l1.element = "l1";
        TreeNode<String> r1 = new TreeNode<>();
        r1.element = "r1";
        TreeNode<String> r2 = new TreeNode<>();
        r2.element = "r2";


        root.right = l1;
        root.left = r1;

        r1.right = r2;
        r2.root = r1;
        return root;
    }
}
