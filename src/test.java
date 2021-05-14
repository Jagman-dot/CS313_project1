import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class test {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("./input.txt"));

        String currentString;

        //
        int n = 0, k = 0;

        //
        int[] keys; // declared an empty array of ints

        while ((currentString = reader.readLine()) != null) {

            if (currentString.startsWith("[")) {

                keys = new int[n]; // this will create a array based on n that is in the input file

                //lets slpit the string for [ , ]

                currentString = currentString.replace("[","");
                currentString = currentString.replace("]","");
                String [] newString = currentString.split("[ , ]");
                for(int i =0; i < newString.length;i++){

                    keys[i] = Integer.parseInt(newString[i]);
                }


//                int j =0;
//                for (int i = 0; i < charArrays.length; i++) {
//
//
//                    if (charArrays[i] != '[' && charArrays[i] != ',' && charArrays[i] != ']') {
//
//                        keys[j] = Integer.parseInt(String.valueOf(charArrays[i]));
//
//                        j++;
//                    }
//                }

//                BinarySearchTree tree = new BinarySearchTree();
//
//                for (int key : keys) {
//                    tree.root = tree.insertNode(tree.root, key);
//                }
//
//                BTreePrinter.printNode(tree.root);

            } else {
                char[] charArray = currentString.toCharArray();

                n = Integer.parseInt(String.valueOf(charArray[0]));
                k = Integer.parseInt(String.valueOf(charArray[2]));

                //System.out.println(n);
            }
        }
    }
}