public class BinarySearchTree {

    public static class Node{

        int data;
        Node left;
        Node right;
        Node parent;


        public Node(int data){
            this.data = data;
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

    // function to insert data
    public void insert(int data){

        Node node = new Node(data);

        //if the tree is empty
        if(root == null){
            root = node;
            node.parent = null;
        } else{
            insertNode(node);
        }
    }

    private void insertNode(Node node){

        Node y = null;
        Node x = root;

        while(x != null){
            y=x;
            if(node.data < x.data){
                x = x.left;
            } else{
                x=x.right;
            }
        }

        node.parent = y;
    }

    public void traveraInOrder(Node node){

        if(node != null){
            traveraInOrder(node.left);
            System.out.print(" " + node.data);
            traveraInOrder(node.right);
        }

    }


}
