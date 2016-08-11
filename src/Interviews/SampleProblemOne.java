package Interviews;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by brandon on 6/16/16.
 */
public class SampleProblemOne {
    public static void main(String[] args){
        // Blegh... wasn't really thinking about design when I did this.  Should just refactor these to be static methods
        SampleProblemOne t = new SampleProblemOne();
        t.findTreasure(0,0,t.treasureMap[0][0]);
    }

    private class Node {
        public boolean visited;
        public Integer distance = 0;
        public char type;
        public Integer x = null;
        public Integer y = null;
        public Node(char key, int xp, int yp){
            type = key;
            x = xp;
            y = yp;
        }
    }

    public Queue<Node> tracker = new ConcurrentLinkedQueue<>();

    private Node[][] treasureMap = {{new Node('O', 0, 0), new Node('O', 0, 1), new Node('O', 0, 2)},
                                         {new Node('O', 1, 0), new Node('L', 1, 1), new Node('O', 1, 2)},
                                         {new Node('O', 2, 0), new Node('T', 2, 1), new Node('O', 2, 2)}};


    private void findTreasure(int height, int length, Node current){

        if (current.type == 'T'){
            System.out.println("Treasure found!");
            System.out.println(String.format("The path can be reached in %s steps", current.distance));
            return;
        }

        current.visited = true;

        // Add all adjacent nodes to the Queue, (We're doing BFS)
        if ((height + 1 < 3) && treasureMap[height+1][length].type != 'L' && !treasureMap[height+1][length].visited){
            tracker.add(treasureMap[height+1][length]);
            treasureMap[height+1][length].distance = current.distance + 1;
        }
        if ((length + 1 < 3) && treasureMap[height][length+1].type != 'L' && !treasureMap[height][length+1].visited){
            tracker.add(treasureMap[height][length+1]);
            treasureMap[height][length+1].distance = current.distance + 1;
        }

        if ((height - 1 >= 0) && treasureMap[height-1][length].type != 'L' && !treasureMap[height-1][length].visited){
            tracker.add(treasureMap[height-1][length]);
            treasureMap[height-1][length].distance = current.distance + 1;
        }
        if ((length - 1 >= 0) && treasureMap[height][length-1].type != 'L' && !treasureMap[height][length-1].visited) {
            tracker.add(treasureMap[height][length - 1]);
            treasureMap[height][length-1].distance = current.distance + 1;
        }

        // Pull Current node off of queue
        Node next = tracker.poll();
        findTreasure(next.x, next.y, next);
    }
}
