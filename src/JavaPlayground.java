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
            System.out.println("What would you like to test?");
            String choice = scanner.next();
            switch(choice){
                case "1":
                    BinarySearchTree binTree = new BinarySearchTree();
                    break;
            }
        }

    }

}
