import DataStructures.BinarySearchTree;

import java.util.Scanner;

/**
 * Created by brandon on 5/18/16.
 */


public class JavaPlayground {

    public static void main(String[] args){
        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            p("What would you like to test?");
            String choice = scanner.next();
            switch(choice){
                case "1":
                    BinarySearchTree binTree = new BinarySearchTree();
//                    223,500,31,7,55,24,15,13,17,22,67,42,420
                    binTree.deleteNode(223);
                    p("deleted 223");
                    binTree.bstCheck();
                    binTree.deleteNode(3);
                    p("deleted 3 (unknown)");
                    binTree.bstCheck();
                    break;
                case "2":
                    p("Goodbye");
                    running = false;
                    break;
            }
        }
    }

    private static void p(String s){
        System.out.println(s);
    }

}
