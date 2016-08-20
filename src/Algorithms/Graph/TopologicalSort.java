package Algorithms.Graph;

import java.util.Set;
import java.util.HashSet;
import java.util.Deque;
import java.util.ArrayDeque;

/**
 * From Wikipedia: In the field of computer science, a topological sort or topological ordering of a directed graph is
 * a linear ordering of its vertices such that for every directed edge uv from vertex u to vertex v, u comes before v
 * in the ordering.
 *
 * In other words, move only down stream.  This is useful in situations like figuring out dependencies, or any other
 * scenario where it's important to figure out the order of tasks to be completed, places to be visited etc.
 *
 * GRAPH MUST BE A DAG
 */
public class TopologicalSort<T> {

    Set<Vertex<T>> visitedSet = new HashSet<>();
    Deque<Vertex<T>> stack = new ArrayDeque<>();

    public Deque<Vertex<T>> topologicalSort(Graph<T> sortableGraph){
        for (Vertex<T> startingPoint : sortableGraph.getAllVertex()){
            if (visitedSet.contains(startingPoint)){ continue; }
            topologicalIncrement(startingPoint);
        }
        return stack;
    }

    // Visit node
    private void topologicalIncrement(Vertex<T> node){
        visitedSet.add(node);

        for (Vertex<T> child : node.getAdjacentVertexes()){
            if (visitedSet.contains(child)){ continue; }
            topologicalIncrement(child);
        }
        stack.push(node);
    }

    public static void test(){
        Graph<Integer> graph = new Graph<>(true);
        graph.addEdge(1, 3);
        graph.addEdge(1, 2);
        graph.addEdge(3, 4);
        graph.addEdge(5, 6);
        graph.addEdge(6, 3);
        graph.addEdge(3, 8);
        graph.addEdge(8, 11);

        TopologicalSort<Integer> sort = new TopologicalSort<Integer>();
        Deque<Vertex<Integer>> result = sort.topologicalSort(graph);
        while(!result.isEmpty()){
            System.out.println(result.poll());
        }

        // should print out 5, 6, 1, 2, 3, 8, 11, 4
    }
}
