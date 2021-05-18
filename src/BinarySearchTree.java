import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class BinarySearchTree {

    public static class Node{

        int key;
        Node left;
        Node right;
        Node parent;
        double priority;

        Random random = new Random();

        public Node(int key){
            this.key = key;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.priority = random.nextDouble();
        }
    }

    Node root;

    // constructor to initialize BST
    public BinarySearchTree(){
        root = null;
    }

    public Node insertNode(Node root, int data)
    {
        // base case
        if (root == null) {
            return new Node(data);
        }


        if (data < root.key)
        {
            root.left = insertNode(root.left, data);

            if (root.left != null && root.left.priority < root.priority) {
                root = rotateRight(root);
            }
        }
        else {
            root.right = insertNode(root.right, data);


            if (root.right != null && root.right.priority < root.priority) {
                root = rotateLeft(root);
            }
        }

        return root;
    }

    public Node rotateLeft(Node root)
    {
        Node R = root.right;
        Node X = root.right.left;

        // rotate
        R.left = root;
        root.right = X;

        // set a new root
        return R;
    }

    public Node rotateRight(Node root)
    {
        Node L = root.left;
        Node Y = root.left.right;

        // rotate
        L.right = root;
        root.left = Y;

        // set a new root
        return L;
    }

    public void traverseInOrder(Node node){

        if(node != null){
            traverseInOrder(node.left);
            System.out.print(" " + node.key + ":" + node.priority + ", ");
            traverseInOrder(node.right);
        }
    }


    public Node search(Node x, int key){

        if((key < x.key && x.left == null) || x.key == key){
            return x;
        } else if((key > x.key && x.right == null) || x.key == key){
            return x;
        }

        if(key < x.key){
            return search(x.left,key);
        } else {
            return search(x.right,key);
        }
    }

    public Node max(Node x){

        while(x.right != null){
            x = x.right;
        }
        return x;
    }


    // returns the min Node, starts at root node
    public Node min(Node x){

        while(x.left != null){
            x = x.left;
        }
        return x;
    }

    //smallest node that is larger than
    // we want to jus go the right sub-tree
    // and then to the left
    public Node successor(Node x){

        //case 1: starting at root and right subtree has a left subtree
        if(x.right != null){
            return min(x.right);
        }

        Node y = x.parent;
        while(y != null && x == y.right){
            x = y;
            y = y.parent;
        }

        return y;
    }

    public Node predecessor(Node x){

        //case 1: starting at root and right subtree has a left subtree
        if(x.left != null){
            return max(x.left);
        }

        Node y = x.parent;

        while(y != null && x == y.left){
            x = y;
            y = y.parent;
        }
        return y;
    }


    public Node deleteNode(Node root, int key)
    {
        if (root == null) {
            return null;
        }

        if (key < root.key) {
            root.left = deleteNode(root.left, key);
        }

        else if (key > root.key) {
            root.right = deleteNode(root.right, key);
        }

        else {
            if (root.left == null && root.right == null)
            {
                root = null;
            }

            else if (root.left != null && root.right != null)
            {
                if (root.left.priority > root.right.priority)
                {
                    root = rotateLeft(root);
                    root.left = deleteNode(root.left, key);
                }
                else {
                    root = rotateRight(root);

                    root.right = deleteNode(root.right, key);
                }
            }

            // Case 3: node to be deleted has only one child
            else {
                // choose a child node
                Node child = (root.left != null)? root.left: root.right;
                root = child;
            }
        }

        return root;
    }

    public void averageGreater(int [] array, int k){

        System.out.print("Given input: ");
        System.out.print("[");
        for (int i =0; i < array.length; i++){
            System.out.print(array[i]);
            if(i != array.length - 1){
                System.out.print( ", ");
            }
        }
        System.out.print("]");

        int sum = 0;
        int avg;
        int total = 0;
        System.out.println();
        System.out.println("\nHere are the sub-arrays with average greater than: " + k);

        for(int i =0; i < array.length; i++){ //i = 2 which is 4
            sum = array[i]; // sum = 4

            if(array[i] >= k){
                System.out.println("[" + array[i] + "],");
                total++;
            }

            for (int j = i +1; j < array.length; j++){ // j = 3 which is 5

                sum = sum + array[j];
                avg = sum / ((j - i)+1);

                if(avg >= k){
                    total++;
                    System.out.print("[");
                    for (int l = i; l <= j; l++){
                        System.out.print(array[l]);
                        if(l != j){
                            System.out.print( ", ");
                        }
                    }

                    System.out.println("],");
                }
            }

        }

        System.out.println("Total sub-arrays: " + total);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("----------------------------------------------------------------");
    }

    public void readInputFile(String file) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currentString;

        // n = length of array and k = target avg
        int n = 0, k = 0;

        //empty array to catch all of the values input file
        int[] keys;

        while ((currentString = reader.readLine()) != null) {

            if (currentString.startsWith("[")) {

                keys = new int[n]; // this will create a array based on n that is in the input file

                currentString = currentString.replace("[", ""); // remove the front brackets
                currentString = currentString.replace("]", ""); // remove the back brackets
                String[] newString = currentString.split("[ , ]");
                for (int i = 0; i < newString.length; i++) {

                    keys[i] = Integer.parseInt(newString[i]);
                }

                BinarySearchTree tree = new BinarySearchTree();

                for (int key : keys) {
                    tree.root = tree.insertNode(tree.root, key);
                }

                tree.averageGreater(keys,k);

            }else {
                char[] charArray = currentString.toCharArray();

                n = Integer.parseInt(String.valueOf(charArray[0]));
                k = Integer.parseInt(String.valueOf(charArray[2]));
            }

        }
    }

    @Override
    public String toString() {
        return "BinarySearchTree{" +
                "root=" + root+
                '}';
    }
}
