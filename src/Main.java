import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new FileReader("./input.txt"));

        String currentString;

        // n = length of array and k = target avg
        int n = 0, k = 0;

        //empty array to catch all of the values input file
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

                tree.averageGreater(keys,k);


            } else {
                char[] charArray = currentString.toCharArray();

                n = Integer.parseInt(String.valueOf(charArray[0]));
                k = Integer.parseInt(String.valueOf(charArray[2]));

            }






        }
    }

}





