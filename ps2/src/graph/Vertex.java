package graph;

import java.util.HashMap;
import java.util.Map;

/**
 * A mutable class representing a vertex in a directed, weighted graph.
 * Each vertex maintains a map of its outgoing edges to other vertices.
 */
class Vertex<L> {
    private final L label;
    private final Map<L, Integer> edges;

    public Vertex(L label) {
        this.label = label;
        this.edges = new HashMap<>();
    }

    public L getLabel() {
        return label;
    }

    public int setEdge(L target, int weight) {
        if (weight == 0) {
            return edges.containsKey(target) ? edges.remove(target) : 0;
        } else {
            return edges.put(target, weight) == null ? 0 : edges.get(target);
        }
    }

    public Map<L, Integer> getEdges() {
        return new HashMap<>(edges); // returns a defensive copy
    }

    @Override
    public String toString() {
        return label + " -> " + edges;
    }
}
