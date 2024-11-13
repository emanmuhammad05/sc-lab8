package graph;

import static org.junit.Assert.*;
import java.util.Collections;
import java.util.Set;
import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 */
public abstract class GraphInstanceTest {
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }

    @Test
    public void testAddVertex() {
        Graph<String> graph = emptyInstance();
        assertTrue("expected new vertex to be added", graph.add("A"));
        assertEquals("expected one vertex in graph", 
                Collections.singleton("A"), graph.vertices());
        assertFalse("expected duplicate vertex to not be added", graph.add("A"));
    }
    
    @Test
    public void testRemoveVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        assertTrue("expected vertex A to be removed", graph.remove("A"));
        assertEquals("expected no vertices in graph", 
                Collections.emptySet(), graph.vertices());
        assertFalse("expected removing non-existent vertex to return false", graph.remove("B"));
    }
    
    @Test
    public void testSetEdgeAddNewEdge() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        int previousWeight = graph.set("A", "B", 5);
        assertEquals("expected previous weight to be 0 for new edge", 0, previousWeight);
        assertEquals("expected new edge weight to be 5", Integer.valueOf(5), graph.targets("A").get("B"));
    }
    
    @Test
    public void testSetEdgeUpdateExistingEdge() {
        Graph<String> graph = emptyInstance();
        graph.set("A", "B", 3);
        int previousWeight = graph.set("A", "B", 5);
        assertEquals("expected previous weight to be 3", 3, previousWeight);
        assertEquals("expected updated edge weight to be 5", Integer.valueOf(5), graph.targets("A").get("B"));
    }
    
    @Test
    public void testSetEdgeRemoveEdge() {
        Graph<String> graph = emptyInstance();
        graph.set("A", "B", 3);
        int previousWeight = graph.set("A", "B", 0);
        assertEquals("expected previous weight to be 3", 3, previousWeight);
        assertFalse("expected edge to be removed", graph.targets("A").containsKey("B"));
    }
    
    @Test
    public void testSources() {
        Graph<String> graph = emptyInstance();
        graph.set("A", "B", 3);
        graph.set("C", "B", 4);
        Set<String> sources = graph.sources("B").keySet();
        assertEquals("expected sources of B to be A and C", Set.of("A", "C"), sources);
    }
    
    @Test
    public void testTargets() {
        Graph<String> graph = emptyInstance();
        graph.set("A", "B", 3);
        graph.set("A", "C", 4);
        Set<String> targets = graph.targets("A").keySet();
        assertEquals("expected targets of A to be B and C", Set.of("B", "C"), targets);
    }
}
