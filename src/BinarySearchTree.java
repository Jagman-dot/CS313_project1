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

    // function to insert key
    public void insert(int key){
        Node node = new Node(key); // creates a node with that number
//        insertAtGivenNode(node);

        insertNode(root,node);

    }

    //TODO rotations instead of
    public void insertAtGivenNode(Node node){

        Node y = null;

        if(root != null) {
             y = search(root, node.key);// returns where the new node should be placed // if we are going insert the same element already in the tree back to back then it will go to the right, and if adding three same elements then we have to jump

            if(y.right != null && y.right.key == y.key){
                y = y.right;
            }
        }

        node.parent = y;

        if(y == null){
            root = node;

        } else if(node.key < y.key){
            y.left = node;

            if (node.priority < node.parent.priority){
                rotateRight(node);
            }

        } else{
            y.right = node;

            if (node.priority < node.parent.priority){
                rotateLeft(node);
            }
        }
    }


    //recursive insert function
    public Node insertNode(Node root, Node x) {

        if (root == null) {
            this.root = x;
            return this.root;
        }

        if (x.key < root.key) {

            Node y = insertNode(root.left, x);

            x.parent = y;
            y.left = x;

            if (x.priority < x.parent.priority) {
                rotateRight(x);
            }

            return x;

        } else {

            Node y = insertNode(root.right, x);
            x.parent = y;
            y.right = x;

            if (x.priority < x.parent.priority) {

                rotateLeft(x);

            }
        }

        return x;
    }

    public Node insertNode(Node root, int data)
    {
        // base case
        if (root == null) {
            return new Node(data);
        }

        // if data is less than the root node, insert in the left subtree;
        // otherwise, insert in the right subtree
        if (data < root.key)
        {
            root.left = insertNode(root.left, data);

            // rotate right if heap property is violated
            if (root.left != null && root.left.priority < root.priority) {
                root = rotateRight(root);
            }
        }
        else {
            root.right = insertNode(root.right, data);

            // rotate left if heap property is violated
            if (root.right != null && root.right.priority < root.priority) {
                root = rotateLeft(root);
            }
        }

        return root;
    }



    public void rotateRightold(Node x) {
        Node y = copy(x);
        if (y.parent != null) {
            if (y.parent.left == x) {
                y.parent.left = y;
            } else {
                y.parent.right = y;
            }
        }
        x.left = y.right;
        if (x.left != null) {
            x.left.parent = x;
        }
        x.parent = y;
        y.right = x;
        if (x == root){
            root = y;
            root.parent = null;
        }
    }

   public void rotateLeftold(Node x) {
        Node y = copy(x);
        if (y.parent != null) {
            if (y.parent.left == x) {
                y.parent.left = y;
            } else {
                y.parent.right = y;
            }
        }
        x.right = y.left;
        if (x.right != null) {
            x.right.parent = x;
        }
        x.parent = y;
        y.left = x;
        if (x == root) { root = y; root.parent = null; }
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



//    private void insertGivenNode(Node root, Node node){
//
//
//        //if the root is null, then the tree is empty and the new node is going to be the root
//        if(root == null){
//            root = node;
//
//        } else if(node.key < root.key){ //recurs the tree and figure out if the node goes on the left side of the tree
//
//            root.left = insertGivenNode(root.left, node);
//
//        }
//
//
//    }



    // function to run from the bottom of the tree to the root per insertion to compare priorities to swap if needed
    private void priorityFixer(Node x){

        if (x == root) {
            return;
        }

        if(x.priority < x.parent.priority){

            swapNodes(x,x.parent);


        } else {
            priorityFixer(x.parent);
        }

        // NEW Swap Node function

        /*
                // copy Node y into a Node temp, for this we will need a Node copy function

        // x is the left child of Y and now we have to reverse this relationship and y will become the right child of x


        }
         */

    }


    //this should swap nodes
    private void swapNodes(Node x, Node y) {

        if(x.key < y.key){
            x.parent = y.parent;
            y.left = x.right;
            x.right = y;
            y.parent = x;
        } else if(x.key > y.key){

            x.parent = y.parent;
            y.right = x.left;
            x.left = y;
            y.parent = x;
        }

    }



        //TODO change this swap node instead
    private void swapPriority(Node x, Node y){

        double temp = y.priority; //store x.parent priority in a temp var

        y.priority = x.priority; // replace x.parent priority with x's priority

        x.priority = temp; // replace x priority with temp var..... the priorities now are swaped

    }

    public void traverseInOrder(Node node){

        if(node != null){
            traverseInOrder(node.left);
            System.out.print(" " + node.key + ":" + node.priority + ", ");
            traverseInOrder(node.right);
        }
    }

    //search starts from the root
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


    // inorder to delete we jus break the link from its parent
    public void delete(Node x){

       //case 1: x does not have a left node
       if(x.left == null){
           Transplant(x,x.right);
       }
       // case 2: x doesn't have a right node
       else if(x.right == null){
           Transplant(x,x.left);
       }else {
           //case 3: if x has both left and right node
           Node y = min(x.right); // here we grab the min node that will replace the node that we will delete
           // swap the pirioites right here
           swapPriority(y,x); // y is the node that we are keeping and x is the one that is being deleted

           if (y.parent != x) {
               Transplant(y,y.right);
               y.right = x.right;
               y.right.parent = y;
           }
           Transplant(x,y);
           y.left = x.left;
           y.left.parent = y;
       }
    }

    public void Transplant(Node u, Node v){

        if(u.parent == null){
            this.root = v;
        } else if(u == u.parent.left){
            u.parent.left = v;
        }else{
            u.parent.right = v;
        }

        if(v != null){
            v.parent = u.parent;
        }
    }

    @Override
    public String toString() {
        return "BinarySearchTree{" +
                "root=" + root+
                '}';
    }

    private Node copy(Node x){

        Node y = new Node(x.key);
        y.priority = x.priority;
        y.right = x.right;
        y.parent = x.parent;
        y.left = x.left;

        return y;
    }
}
