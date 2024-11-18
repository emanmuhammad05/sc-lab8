package poet;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.StringTokenizer;

import graph.Graph;

public class GraphPoet {

    private final Graph<String> graph = Graph.empty();

    // Abstraction function:
    //   Represents a word affinity graph derived from the corpus
    // Representation invariant:
    //   Graph must not contain null vertices or edges
    // Safety from rep exposure:
    //   graph is private and final, no direct access provided

    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        List<String> lines = Files.readAllLines(corpus.toPath());
        StringBuilder content = new StringBuilder();

        // Combine all lines into a single string
        for (String line : lines) {
            content.append(line).append(" ");
        }

        // Tokenize the content into words
        StringTokenizer tokenizer = new StringTokenizer(content.toString());
        String prevWord = null;

        while (tokenizer.hasMoreTokens()) {
            String currentWord = tokenizer.nextToken().toLowerCase();

            if (prevWord != null) {
                // Add vertices and edge
                graph.add(prevWord);
                graph.add(currentWord);
                int weight = graph.set(prevWord, currentWord, graph.targets(prevWord).getOrDefault(currentWord, 0) + 1);
            }

            prevWord = currentWord;
        }
    }

    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        StringTokenizer tokenizer = new StringTokenizer(input);
        StringBuilder poem = new StringBuilder();

        String prevWord = null;

        while (tokenizer.hasMoreTokens()) {
            String currentWord = tokenizer.nextToken();
            if (prevWord != null) {
                // Find a bridge word
                String bridgeWord = findBridgeWord(prevWord.toLowerCase(), currentWord.toLowerCase());
                if (bridgeWord != null) {
                    poem.append(" ").append(bridgeWord);
                }
            }
            // Add current word
            poem.append(" ").append(currentWord);
            prevWord = currentWord;
        }

        return poem.toString().trim();
    }

    private String findBridgeWord(String word1, String word2) {
        String bridge = null;
        int maxWeight = 0;

        // Find all possible bridge words
        for (String candidate : graph.targets(word1).keySet()) {
            if (graph.targets(candidate).containsKey(word2)) {
                int weight = graph.targets(word1).get(candidate) + graph.targets(candidate).get(word2);
                if (weight > maxWeight) {
                    maxWeight = weight;
                    bridge = candidate;
                }
            }
        }

        return bridge;
    }

    // Representation invariant check
    private void checkRep() {
        for (String vertex : graph.vertices()) {
            assert vertex != null : "Graph contains null vertex";
        }
    }
}
