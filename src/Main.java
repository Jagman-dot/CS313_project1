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
        //System.out.println(tree.min(tree.root).key);
        //System.out.println(tree.max(tree.root).key);
//        System.out.println(tree.successor(tree.root).key);
//        System.out.println(tree.predecessor(tree.root).key);
//        System.out.println(tree.successor(tree.root.left.right).key);
//
//
//        System.out.println(tree.predecessor(tree.root.left.right).key);



    }
}
