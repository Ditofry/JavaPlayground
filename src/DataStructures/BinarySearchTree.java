package DataStructures;

import java.util.List;

/**
 * Created by brandon on 5/28/16.
 * This is a very basic BST to illustrate the basic BST properties and operations.
 * There's not much here for real-world utility... just a simple example to see what the
 * code looks like in Java
 */
public class BinarySearchTree {

    private class Node {
        private int value;
        private Node parent;
        private Node leftChild;
        private Node rightChild;
        public Node(int v, Node p, Node lc, Node rc){
            value = v;
            parent = p;
            leftChild = lc;
            rightChild = rc;
        }
    }

    public BinarySearchTree(){
        System.out.println("Created Binary Search Tree without args, using nodes: 223,500,31,7,55,24,15,13,17,22,67,42,420");
        int[] startingData = {223,500,31,7,55,24,15,13,17,22,67,42,420};

        for (int val : startingData){
            addNode(val);
        }

        if (isBST()){
            System.out.println("Properly-formed BST generated");
        } else {
            System.out.println("BST was IMPROPERLY-formed!!! Check your code and try again");
        }
    }

    public BinarySearchTree(int[] startingData){
        System.out.println("Creating BST with supplied data");

        for (int val : startingData){
            addNode(val);
        }

        if (isBST()){
            System.out.println("Properly-formed BST generated");
        } else {
            System.out.println("BST was IMPROPERLY-formed!!! Check your code and try again");
        }
    }

    private int bstSize = 0;
    private Node root;

    public int getMin(){
        Node current = root;
        while(current.leftChild != null){
            current = current.leftChild;
        }
        return current.value;
    }

    public int getMax(){
        Node current = root;
        while(current.rightChild != null){
            current = current.rightChild;
        }
        return current.value;
    }

    // Note, this approach ignores duplicates, as that seems to be a common convention
    // in generic, simplified BST implementation.  We could easily as a "duplicates are smaller"
    // rule or add a counter or a list on Node for duplicates
    public void addNode(int newValue){
        if (root == null) {
            root = new Node(newValue, null, null, null);
            return;
        }

        Node current = root;
        while (current != null) {
            if (newValue == current.leftChild.value || newValue == current.rightChild.value){
                return;
            } else if (newValue < current.value){
                if (current.leftChild != null){
                    current = current.leftChild;
                } else {
                    current.leftChild = new Node(newValue, current, null, null);
                }
            } else {
                if (current.rightChild != null){
                    current = current.rightChild;
                } else {
                    current.rightChild = new Node(newValue, current, null, null);
                }
            }
        }
    }

    public int deleteNode(int value){
        // Find node by giving DFS a target
        Node nodeForDelete = binarySearch(value, root);
        if (nodeForDelete == null){
            throw new Error("Node could not be found in tree");
        }
        return value;
    }

    public Node binarySearch(int target){ return binarySearch(target, root); }
    private Node binarySearch(int target, Node n){
        if (n == null || target == n.value) {
            return n;
        } else if (target < n.value) { // Note: this condition must be consistent with the way nodes are added
            return binarySearch(target, n.leftChild);
        } else {
            return binarySearch(target, n.rightChild);
        }
    }

    public int getTreeSize(){
        return bstSize;
    }

    /* returns true if given search tree is binary
 search tree (efficient version) */
    boolean isBST()  {
        return isBSTUtil(root, Integer.MIN_VALUE,
                Integer.MAX_VALUE);
    }

    // *** FROM http://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/ cause I'm lazy
    /* Returns true if the given tree is a BST and its
      values are >= min and <= max. */
    boolean isBSTUtil(Node node, int min, int max)
    {
        /* an empty tree is BST */
        if (node == null)
            return true;

        /* false if this node violates the min/max constraints */
        if (node.value < min || node.value > max)
            return false;

        /* otherwise check the subtrees recursively
        tightening the min/max constraints */
        // Allow only distinct values
        return (isBSTUtil(node.leftChild, min, node.value - 1) &&
                isBSTUtil(node.rightChild, node.value + 1, max));
    }
}
