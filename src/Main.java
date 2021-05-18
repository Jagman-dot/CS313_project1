import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new FileReader("./input.txt"));

        String currentString;

        //
        int n = 0, k = 0;

        //
        int[] keys;

        while ((currentString = reader.readLine()) != null) {

            if (currentString.startsWith("[")) {

                keys = new int[n]; // this will create a array based on n that is in the input file



                currentString = currentString.replace("[",""); // remove the front brackets
                currentString = currentString.replace("]",""); // remove the back brackets
                String [] newString = currentString.split("[ , ]");
                for(int i =0; i < newString.length;i++){

                    keys[i] = Integer.parseInt(newString[i]);
                }


                BinarySearchTree tree = new BinarySearchTree();

                for (int key: keys){
                    tree.root = tree.insertNode(tree.root, key);
                }

                BTreePrinter.printNode(tree.root);


            } else {
                char[] charArray = currentString.toCharArray();

                n = Integer.parseInt(String.valueOf(charArray[0]));
                k = Integer.parseInt(String.valueOf(charArray[2]));

            }



//        BinarySearchTree tree = new BinarySearchTree();
//
//        int [] keys = {5,2,1,4,9,8,10};
//
//        for (int key: keys){
//            tree.root = tree.insertNode(tree.root, key);
//        }
//
//        tree.traverseInOrder(tree.root);
//        System.out.println();
//        BTreePrinter.printNode(tree.root);
//
//        tree.root = tree.deleteNode(tree.root,10);
//        System.out.println();
//

//        BTreePrinter.printNode(tree.root);



        }
    }

}

class BTreePrinter {

    public static <T extends Comparable<?>> void printNode(BinarySearchTree.Node root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static <T extends Comparable<?>> void printNodeInternal(List<BinarySearchTree.Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<BinarySearchTree.Node> newNodes = new ArrayList<BinarySearchTree.Node>();
        for (BinarySearchTree.Node node : nodes) {
            if (node != null) {
                System.out.print(node.key);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static <T extends Comparable<?>> int maxLevel(BinarySearchTree.Node node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }

}



