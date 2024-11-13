package graph;

/**
 * An immutable class representing a weighted directed edge between two vertices in a graph.
 */
public class Edge {
    private final String from;
    private final String to;
    private final int weight;

    /**
     * Constructs an edge from a source vertex to a target vertex with a specified weight.
     * 
     * @param from the source vertex
     * @param to the target vertex
     * @param weight the weight of the edge
     */
    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
        checkRep();
    }

    private void checkRep() {
        assert from != null : "from vertex must not be null";
        assert to != null : "to vertex must not be null";
        assert weight > 0 : "weight must be positive";
    }

    public String from() { return from; }

    public String to() { return to; }

    public int weight() { return weight; }

    @Override
    public String toString() {
        return from + " -> " + to + " [weight=" + weight + "]";
    }
}
