public class Main {

    public static void main(String[] args) {

        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(6);
        tree.insert(4);
        tree.insert(8);
        tree.insert(3);
        tree.insert(5);
        tree.insert(7);
        tree.insert(9);

        tree.traverseInOrder(tree.root);
        System.out.println();
        System.out.println(tree.min(tree.root).key);
        System.out.println(tree.max(tree.root).key);



    }
}
