package DataStructures;

/**
 * General purpose node for use in algorithms
 */
public class IntNode {
    // TODO: are object properties reference types? Do they init to null by default?
    public int value;
    public IntNode lChild;
    public IntNode rChild;

    public IntNode(int v, IntNode l, IntNode r){
        value = v;
        lChild = l;
        rChild = r;
    }

    public IntNode(){}
}
