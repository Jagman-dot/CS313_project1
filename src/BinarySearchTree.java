@SuppressWarnings("SuspiciousNameCombination")
public class BinarySearchTree {

    public static class Node{

        int key;
        Node left;
        Node right;
        Node parent;


        public Node(int key){
            this.key = key;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    Node root;

    // constructor to initialize BST
    public BinarySearchTree(){
        root = null;
    }

    // function to insert key
    public void insert(int key){

        Node node = new Node(key);
        insertNode(node);

    }

    private void insertNode(Node node){

        Node y = null;
        Node x = root;

        while(x != null){
            y = x;
            if(node.key < x.key){
                x = x.left;
            } else{
                x=x.right;
            }
        }

        node.parent = y;

        if(y == null){
            root = node;
        } else if(node.key < y.key){
            y.left = node;
        } else{
            y.right = node;
        }

    }

    public void traverseInOrder(Node node){

        if(node != null){
            traverseInOrder(node.left);
            System.out.print(" " + node.key);
            traverseInOrder(node.right);
        }

    }

    //TODO Delete function



    //search starts from the root
    public Node search(Node x, int key){

        if(x == null || x.key == key){
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

    //TODO Successor function




    //TODO Predecessor function





}
