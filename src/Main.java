public class Main {

    public static void main(String[] args) {

        BinarySearchTree tree = new BinarySearchTree();

        tree.insert(8);
        tree.insert(6);
        tree.insert(10);
        tree.insert(5);
        tree.insert(7);
        tree.insert(9);
        tree.insert(11);

        tree.traverseInOrder(tree.root);
        System.out.println();

        System.out.println(tree.root.priority);

        System.out.println(tree.search(tree.root, 12).key);


    }
}
