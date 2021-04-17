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


}
