package graph;

import static org.junit.Assert.*;

import java.util.Collections;

import org.junit.Test;

public class GraphStaticTest {

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }

    @Test
    public void testEmptyVerticesEmpty() {
        Graph<String> graph = Graph.empty();
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), graph.vertices());
    }

    @Test
    public void testEmptyGenericTypeInteger() {
        Graph<Integer> graph = Graph.empty();
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), graph.vertices());
    }

    @Test
    public void testEmptyGenericTypeCustomClass() {
        Graph<TestLabel> graph = Graph.empty();
        assertEquals("expected empty() graph to have no vertices",
                Collections.emptySet(), graph.vertices());
    }

    private static class TestLabel {
        private final String value;

        public TestLabel(String value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TestLabel)) return false;
            TestLabel that = (TestLabel) o;
            return value.equals(that.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public String toString() {
            return value;
        }
    }
}

