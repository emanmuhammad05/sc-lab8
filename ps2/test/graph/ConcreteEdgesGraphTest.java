package graph;

import static org.junit.Assert.*;
import org.junit.Test;

public class ConcreteEdgesGraphTest {

    @Test
    public void testEdgeConstructor() {
        Edge edge = new Edge("A", "B", 5);
        assertEquals("A", edge.from());
        assertEquals("B", edge.to());
        assertEquals(5, edge.weight());
    }

    @Test
    public void testEdgeToString() {
        Edge edge = new Edge("A", "B", 5);
        assertEquals("A -> B [weight=5]", edge.toString());
    }

    @Test
    public void testAddAndRemoveVertices() {
        ConcreteEdgesGraph graph = new ConcreteEdgesGraph();
        assertTrue(graph.add("A"));
        assertTrue(graph.add("B"));
        assertTrue(graph.remove("A"));
        assertFalse(graph.vertices().contains("A"));
    }

    @Test
    public void testSetEdge() {
        ConcreteEdgesGraph graph = new ConcreteEdgesGraph();
        graph.add("A");
        graph.add("B");
        assertEquals(0, graph.set("A", "B", 5));
        assertEquals(5, graph.targets("A").get("B").intValue());
    }

    @Test
    public void testGraphToString() {
        ConcreteEdgesGraph graph = new ConcreteEdgesGraph();
        graph.add("A");
        graph.set("A", "B", 3);
        assertTrue(graph.toString().contains("Graph with vertices:\nA\nB\nAnd edges:\nA -> B [weight=3]"));
    }
}
