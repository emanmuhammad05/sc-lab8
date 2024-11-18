package graph;

import java.util.*;

/**
 * An implementation of a weighted directed graph with generic vertex labels,
 * represented by a set of vertices and a list of edges.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();

    /**
     * Abstraction function:
     *   Represents a weighted directed graph with vertices labeled by any type L,
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
        for (Edge<L> edge : edges) {
            assert vertices.contains(edge.from()) && vertices.contains(edge.to()) :
                    "Edge endpoints must exist in vertices";
            assert edge.weight() > 0 : "Edge weight must be positive";
        }
    }

    @Override
    public boolean add(L vertex) {
        boolean added = vertices.add(vertex);
        checkRep();
        return added;
    }

    @Override
    public boolean remove(L vertex) {
        boolean removed = vertices.remove(vertex);
        edges.removeIf(edge -> edge.from().equals(vertex) || edge.to().equals(vertex));
        checkRep();
        return removed;
    }

    @Override
    public int set(L source, L target, int weight) {
        int previousWeight = 0;
        Optional<Edge<L>> existingEdge = edges.stream()
                .filter(edge -> edge.from().equals(source) && edge.to().equals(target))
                .findFirst();
        if (existingEdge.isPresent()) {
            previousWeight = existingEdge.get().weight();
            edges.remove(existingEdge.get());
        }

        if (weight > 0) {
            edges.add(new Edge<>(source, target, weight));
            vertices.add(source);
            vertices.add(target);
        }
        checkRep();
        return previousWeight;
    }

    @Override
    public Set<L> vertices() {
        return Collections.unmodifiableSet(vertices);
    }

    @Override
    public Map<L, Integer> sources(L target) {
        Map<L, Integer> sources = new HashMap<>();
        for (Edge<L> edge : edges) {
            if (edge.to().equals(target)) {
                sources.put(edge.from(), edge.weight());
            }
        }
        return sources;
    }

    @Override
    public Map<L, Integer> targets(L source) {
        Map<L, Integer> targets = new HashMap<>();
        for (Edge<L> edge : edges) {
            if (edge.from().equals(source)) {
                targets.put(edge.to(), edge.weight());
            }
        }
        return targets;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Graph with vertices:\n");
        for (L vertex : vertices) {
            builder.append(vertex).append("\n");
        }
        builder.append("And edges:\n");
        for (Edge<L> edge : edges) {
            builder.append(edge.toString()).append("\n");
        }
        return builder.toString();
    }
}

