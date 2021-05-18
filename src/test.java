import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class test {

    public static void main(String[] args) throws IOException {


        int k = 4;
        int [] array = {5,2,4,5,1};

        BinarySearchTree tree = new BinarySearchTree();

        tree.averageGreater(array,k);

    }
}