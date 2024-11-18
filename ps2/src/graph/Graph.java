package graph;

import java.util.Map;
import java.util.Set;

public interface Graph<L> {

    boolean add(L vertex);

    boolean remove(L vertex);

    int set(L source, L target, int weight);

    Set<L> vertices();

    Map<L, Integer> sources(L target);

    Map<L, Integer> targets(L source);

    static <L> Graph<L> empty() {
        return new ConcreteEdgesGraph<>();
    }
}
