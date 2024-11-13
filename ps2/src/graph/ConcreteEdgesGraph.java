package graph;

import java.util.*;

/**
 * An implementation of a weighted directed graph with String vertex labels,
 * represented by a set of vertices and a list of edges.
 */
public class ConcreteEdgesGraph implements Graph<String> {
    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();

    /**
     * Abstraction function:
     *   Represents a weighted directed graph with vertices labeled by strings,
     *   where each edge connects two vertices with a given weight.
     *   
     * Representation invariant:
     *   - vertices contains unique, non-null labels.
     *   - edges contains unique, non-null edges where each edge weight is positive,
     *     and edge endpoints exist in vertices.
     *
     * Safety from rep exposure:
     *   - vertices and edges are private and final.
     *   - Edge is immutable, ensuring edges cannot be modified externally.
     */
    private void checkRep() {
        for (Edge edge : edges) {
            assert vertices.contains(edge.from()) && vertices.contains(edge.to()) : 
                "Edge endpoints must exist in vertices";
            assert edge.weight() > 0 : "Edge weight must be positive";
        }
    }

    @Override
    public boolean add(String vertex) {
        boolean added = vertices.add(vertex);
        checkRep();
        return added;
    }

    @Override
    public boolean remove(String vertex) {
        boolean removed = vertices.remove(vertex);
        edges.removeIf(edge -> edge.from().equals(vertex) || edge.to().equals(vertex));
        checkRep();
        return removed;
    }

    @Override
    public int set(String source, String target, int weight) {
        int previousWeight = 0;
        Optional<Edge> existingEdge = edges.stream()
                                           .filter(edge -> edge.from().equals(source) && edge.to().equals(target))
                                           .findFirst();
        if (existingEdge.isPresent()) {
            previousWeight = existingEdge.get().weight();
            edges.remove(existingEdge.get());
        }

        if (weight > 0) {
            edges.add(new Edge(source, target, weight));
            vertices.add(source);
            vertices.add(target);
        }
        checkRep();
        return previousWeight;
    }

    @Override
    public Set<String> vertices() {
        return Collections.unmodifiableSet(vertices);
    }

    @Override
    public Map<String, Integer> sources(String target) {
        Map<String, Integer> sources = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.to().equals(target)) {
                sources.put(edge.from(), edge.weight());
            }
        }
        return sources;
    }

    @Override
    public Map<String, Integer> targets(String source) {
        Map<String, Integer> targets = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.from().equals(source)) {
                targets.put(edge.to(), edge.weight());
            }
        }
        return targets;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Graph with vertices:\n");
        for (String vertex : vertices) {
            builder.append(vertex).append("\n");
        }
        builder.append("And edges:\n");
        for (Edge edge : edges) {
            builder.append(edge.toString()).append("\n");
        }
        return builder.toString();
    }
}
