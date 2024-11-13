package graph;

import java.util.HashMap;
import java.util.Map;

/**
 * A mutable class representing a vertex in a directed, weighted graph.
 * Each vertex maintains a map of its outgoing edges to other vertices.
 */
public class Vertex {
    private final String label;
    private final Map<String, Integer> edges;

    /**
     * Constructs a new vertex with the specified label.
     * 
     * @param label the label for this vertex
     */
    public Vertex(String label) {
        this.label = label;
        this.edges = new HashMap<>();
    }

    public String getLabel() {
        return label;
    }

    /**
     * Adds or updates an edge from this vertex to another vertex with a specified weight.
     * If the weight is zero, the edge is removed instead.
     * 
     * @param target the target vertex label
     * @param weight the weight of the edge
     * @return the previous weight of the edge, or 0 if there was no edge
     */
    public int setEdge(String target, int weight) {
        if (weight == 0) {
            // Retrieve the existing weight before removing the edge, if it exists
            return edges.containsKey(target) ? edges.remove(target) : 0;
        } else {
            // Add or update the edge with the specified weight
            return edges.put(target, weight) == null ? 0 : edges.get(target);
        }
    }


    /**
     * Gets the outgoing edges from this vertex.
     * 
     * @return a map of target vertices and weights of edges
     */
    public Map<String, Integer> getEdges() {
        return new HashMap<>(edges); // returns a defensive copy
    }

    @Override
    public String toString() {
        return label + " -> " + edges;
    }
}
