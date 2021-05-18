import java.util.Random;

//TODO rename class as TREAP
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
        // base case: the key is not found in the tree
        if (root == null) {
            return null;
        }

        // if the key is less than the root node, recur for the left subtree
        if (key < root.key) {
            root.left = deleteNode(root.left, key);
        }

        // if the key is more than the root node, recur for the right subtree
        else if (key > root.key) {
            root.right = deleteNode(root.right, key);
        }

        // if the key is found
        else {
            // Case 1: node to be deleted has no children (it is a leaf node)
            if (root.left == null && root.right == null)
            {
                // deallocate the memory and update root to null
                root = null;
            }

            // Case 2: node to be deleted has two children
            else if (root.left != null && root.right != null)
            {
                // if the left child has less priority than the right child
                if (root.left.priority > root.right.priority)
                {
                    // call `rotateLeft()` on the root
                    root = rotateLeft(root);

                    // recursively delete the left child
                    root.left = deleteNode(root.left, key);
                }
                else {
                    // call `rotateRight()` on the root
                    root = rotateRight(root);

                    // recursively delete the right child
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

        int sum = 0;
        int avg;
        for(int i =0; i < array.length; i++){ //i = 2 which is 4
            sum = array[i]; // sum = 4

            if(array[i] >= k){
                System.out.println("[" + array[i] + "],");
            }

            for (int j = i +1; j < array.length; j++){ // j = 3 which is 5

                sum = sum + array[j];
                avg = sum / ((j - i)+1);

                if(avg >= k){
                    System.out.print("[");
                    for (int l = i; l <= j; l++){
                        System.out.print(array[l] + ",");
                    }

                    System.out.println("]");
                }
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
