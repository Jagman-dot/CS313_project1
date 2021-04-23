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

    // function to insert key
    public void insert(int key){
        Node node = new Node(key);
        insertAtGivenNode(node);
    }

    //TODO figure out the min-heap property for this method
    public void insertAtGivenNode(Node node){

        Node y = null;

        if(root != null) {
             y = search(root, node.key);
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
           Node y = min(x.right);

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
}
