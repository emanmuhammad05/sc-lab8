package graph;

import java.util.*;

/**
 * An implementation of a directed, weighted graph with generic vertex labels,
 * using vertices represented as Vertex objects.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    private final List<Vertex<L>> vertices = new ArrayList<>();

    /**
     * Abstraction function:
     *   Represents a directed, weighted graph with vertices labeled by any type L.
     *
     * Representation invariant:
     *   - vertices contains unique, non-null Vertex objects with unique labels.
     *
     * Safety from rep exposure:
     *   - vertices is private and final.
     *   - Vertex provides a defensive copy of its edges.
     */
    private void checkRep() {
        Set<L> labels = new HashSet<>();
        for (Vertex<L> vertex : vertices) {
            assert vertex != null : "vertex must not be null";
            assert labels.add(vertex.getLabel()) : "vertex labels must be unique";
        }
    }

    private Vertex<L> getVertex(L label) {
        for (Vertex<L> vertex : vertices) {
            if (vertex.getLabel().equals(label)) {
                return vertex;
            }
        }
        return null;
    }

    @Override
    public boolean add(L vertex) {
        if (getVertex(vertex) != null) {
            return false;
        }
        vertices.add(new Vertex<>(vertex));
        checkRep();
        return true;
    }

    @Override
    public boolean remove(L vertex) {
        Vertex<L> v = getVertex(vertex);
        if (v == null) {
            return false;
        }
        vertices.remove(v);
        for (Vertex<L> other : vertices) {
            other.setEdge(vertex, 0); // Remove all edges pointing to the removed vertex
        }
        checkRep();
        return true;
    }

    @Override
    public int set(L source, L target, int weight) {
        Vertex<L> sourceVertex = getVertex(source);
        Vertex<L> targetVertex = getVertex(target);
        if (sourceVertex == null) {
            sourceVertex = new Vertex<>(source);
            vertices.add(sourceVertex);
        }
        if (targetVertex == null) {
            targetVertex = new Vertex<>(target);
            vertices.add(targetVertex);
        }
        int previousWeight = sourceVertex.setEdge(target, weight);
        checkRep();
        return previousWeight;
    }

    @Override
    public Set<L> vertices() {
        Set<L> vertexLabels = new HashSet<>();
        for (Vertex<L> vertex : vertices) {
            vertexLabels.add(vertex.getLabel());
        }
        return vertexLabels;
    }

    @Override
    public Map<L, Integer> sources(L target) {
        Map<L, Integer> sources = new HashMap<>();
        for (Vertex<L> vertex : vertices) {
            Integer weight = vertex.getEdges().get(target);
            if (weight != null) {
                sources.put(vertex.getLabel(), weight);
            }
        }
        return sources;
    }

    @Override
    public Map<L, Integer> targets(L source) {
        Vertex<L> vertex = getVertex(source);
        if (vertex == null) {
            return Collections.emptyMap();
        }
        return vertex.getEdges();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("Graph with vertices:\n");
        for (Vertex<L> vertex : vertices) {
            builder.append(vertex.toString()).append("\n");
        }
        return builder.toString();
    }
}
