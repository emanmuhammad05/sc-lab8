package poet;

import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GraphPoetTest {

    private File createCorpusFile(String content) throws IOException {
        File corpus = new File("test/poet/temp-corpus.txt");
        corpus.getParentFile().mkdirs(); // Ensure the directory exists
        try (FileWriter writer = new FileWriter(corpus)) {
            writer.write(content);
        }
        return corpus;
    }

    @Test(expected = AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // Ensure assertions are enabled.
    }

    @Test
    public void testPoemWithBridgeWords() throws IOException {
        File corpus = createCorpusFile("To explore strange new worlds\nTo seek out new life and new civilizations");
        GraphPoet poet = new GraphPoet(corpus);
        String input = "Seek to explore new and exciting synergies!";
        String expected = "Seek to explore strange new life and exciting synergies!";
        assertEquals(expected, poet.poem(input));
    }

    @Test
    public void testPoemNoBridgeWords() throws IOException {
        File corpus = createCorpusFile("To explore strange new worlds\nTo seek out new life and new civilizations");
        GraphPoet poet = new GraphPoet(corpus);
        String input = "Hello world!";
        assertEquals("Hello world!", poet.poem(input));
    }

    @Test
    public void testCaseInsensitivity() throws IOException {
        File corpus = createCorpusFile("To explore strange new worlds\nTo seek out new life and new civilizations");
        GraphPoet poet = new GraphPoet(corpus);
        String input = "To Seek Life";
        String expected = "To seek out life";
        assertEquals(expected, poet.poem(input));
    }

    @Test
    public void testSpecialCharacters() throws IOException {
        File corpus = createCorpusFile("To explore strange new worlds\nTo seek out new life and new civilizations");
        GraphPoet poet = new GraphPoet(corpus);
        String input = "Hello, world!";
        String expected = "Hello, world!";
        assertEquals(expected, poet.poem(input));
    }
}

