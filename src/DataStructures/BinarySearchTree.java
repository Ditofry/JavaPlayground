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
        private Node parent = null;
        private Node leftChild = null;
        private Node rightChild = null;
        public Node(int v, Node p, Node lc, Node rc, Node par){
            value = v;
            parent = p;
            leftChild = lc;
            rightChild = rc;
            parent = par;
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

    private Node root;

    public Node getMin(Node current){
        while(current.leftChild != null){
            current = current.leftChild;
        }
        return current;
    }

    public Node getMax(Node current){
        while(current.rightChild != null){
            current = current.rightChild;
        }
        return current;
    }

    // Note, this approach ignores duplicates, as that seems to be a common convention
    // in generic, simplified BST implementation.  We could easily as a "duplicates are smaller"
    // rule or add a counter or a list on Node for duplicates
    public void addNode(int newValue){
        if (root == null) {
            root = new Node(newValue, null, null, null, null);
            return;
        } else {
            addNodeUtil(newValue, root);
        }
    }

    // My Kingdom for default parameter values...
    public void addNodeUtil(int val, Node current){
        if (val < current.value){
            if (current.leftChild == null){
                current.leftChild = new Node(val, current, null, null, current);
            } else {
                addNodeUtil(val, current.leftChild);
            }
        } else {
            if (current.rightChild == null) {
                current.rightChild = new Node(val, current, null, null, current);
            } else {
                addNodeUtil(val, current.rightChild);
            }
        }
    }

    public void deleteNode(int value){
        deleteNodeUtil(value, root);
    }

    // This could probably use a refactor.  It isn't very readable.
    public void deleteNodeUtil(int value, Node nodeForDelete){
        // Recurse to deletion point
        if (nodeForDelete == null){
            return;
        } else if (value < nodeForDelete.value){
            deleteNodeUtil(value, nodeForDelete.leftChild);
        } else if (value > nodeForDelete.value){
            deleteNodeUtil(value, nodeForDelete.rightChild);
        } else if (value == nodeForDelete.value) {
            // Now that we're at the deletion point we can handle our 3 cases
            if (nodeForDelete != null && nodeForDelete.leftChild != null && nodeForDelete.rightChild != null) {
                nodeForDelete = getMin(nodeForDelete.leftChild);
                deleteNodeUtil(nodeForDelete.value, nodeForDelete);
            } else if (nodeForDelete.leftChild != null) {
                replaceNodeForParent(nodeForDelete, nodeForDelete.leftChild);
            } else if (nodeForDelete.rightChild != null){
                replaceNodeForParent(nodeForDelete, nodeForDelete.rightChild);
            } else {
                replaceNodeForParent(nodeForDelete, null);
            }

        }
    }

    private void replaceNodeForParent(Node replaced, Node replacement){
        if (replaced.parent.leftChild == replaced){ // Note: compares reference, not content
            replaced.parent.leftChild = replacement;
            replaced = null;
        } else if (replaced.parent.rightChild == replaced){
            replaced.parent.rightChild = replacement;
            replaced = null;
        }
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

    /* returns true if given search tree is binary
 search tree (efficient version) */
    public boolean isBST()  {
        return isBSTUtil(root, Integer.MIN_VALUE,
                Integer.MAX_VALUE);
    }

    // *** FROM http://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/ cause I'm lazy
    /* Returns true if the given tree is a BST and its
      values are >= min and <= max. */
    private boolean isBSTUtil(Node node, int min, int max)
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

    public void bstCheck(){
        if ( isBST() ){
            System.out.println("BST is proper");
        } else {
            System.out.println("BST MALFORMED");
        }
    }
}
