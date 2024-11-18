package graph;

import static org.junit.Assert.*;
import org.junit.Test;

public class ConcreteVerticesGraphTest {

    @Test
    public void testVertexConstructor() {
        Vertex vertex = new Vertex("A");
        assertEquals("A", vertex.getLabel());
        assertTrue(vertex.getEdges().isEmpty());
    }

    @Test
    public void testVertexSetEdge() {
        Vertex vertex = new Vertex("A");
        assertEquals(0, vertex.setEdge("B", 5));
        assertEquals(Integer.valueOf(5), vertex.getEdges().get("B"));
        assertEquals(5, vertex.setEdge("B", 10)); // update edge weight
        assertEquals(Integer.valueOf(10), vertex.getEdges().get("B"));
        assertEquals(10, vertex.setEdge("B", 0)); // remove edge
        assertFalse(vertex.getEdges().containsKey("B"));
    }

    @Test
    public void testVertexToString() {
        Vertex vertex = new Vertex("A");
        vertex.setEdge("B", 5);
        assertEquals("A -> {B=5}", vertex.toString());
    }

    @Test
    public void testAddAndRemoveVertices() {
        ConcreteVerticesGraph graph = new ConcreteVerticesGraph();
        assertTrue(graph.add("A"));
        assertTrue(graph.add("B"));
        assertTrue(graph.remove("A"));
        assertFalse(graph.vertices().contains("A"));
    }

    @Test
    public void testSetEdge() {
        ConcreteVerticesGraph graph = new ConcreteVerticesGraph();
        graph.add("A");
        graph.add("B");
        assertEquals(0, graph.set("A", "B", 5));
        assertEquals(Integer.valueOf(5), graph.targets("A").get("B"));
    }

    @Test
    public void testGraphToString() {
        ConcreteVerticesGraph graph = new ConcreteVerticesGraph();
        graph.add("A");
        graph.set("A", "B", 3);
        assertTrue(graph.toString().contains("Graph with vertices:\nA -> {B=3}"));
    }
}
