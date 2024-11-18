package graph;

/**
 * An immutable class representing a weighted directed edge between two vertices in a graph.
 */
class Edge<L> {
    private final L from;
    private final L to;
    private final int weight;

    public Edge(L from, L to, int weight) {
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

    public L from() { return from; }

    public L to() { return to; }

    public int weight() { return weight; }

    @Override
    public String toString() {
        return from + " -> " + to + " [weight=" + weight + "]";
    }
}
