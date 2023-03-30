package org.example;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.example.Main.MAX_LENGTH;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {

    private static final int WORDS_COUNT = 100;
    private static final String STRING80 =
            "abcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghij"; //80 symbols
    private final String regex = "^(?:[a-zA-Z](?:[a-zA-Z ]{0,78}[a-zA-Z])?\\n)+$";

    private static final Random random = new Random();

    @Test
    @RepeatedTest(100)
    public void shouldSplitRandomString() {
        var input = generateRandomString();
        var output = Main.splitString(input);
        System.out.println("INPUT: " + input);
        System.out.println("\nOUTPUT: = " + output);

        assertTrue(output.matches(regex));
    }

    @Test
    public void shouldSplitStringWithMaxSymbols() {
        assertTrue(Main.splitString(STRING80).matches(regex));
    }
    @Test
    public void shouldReturnEmptyStringForIncorrectInput() {
        assertEquals("", Main.splitString(""));
        assertEquals("", Main.splitString("  "));
        assertEquals("", Main.splitString(null));
        assertEquals("", Main.splitString(STRING80 + "a"));
    }

    private String generateRandomString() {
        String[] words = new String[WORDS_COUNT];
        for (int i = 0; i < WORDS_COUNT; i++) {
            int wordLength = random.nextInt(MAX_LENGTH) + 1;
            StringBuilder word = new StringBuilder();
            for (int j = 0; j < wordLength; j++) {
                char c = (char) (random.nextInt(26) + 'a');
                word.append(c);
            }
            words[i] = word.toString();
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < WORDS_COUNT; i++) {
            result.append(words[i]);
            int numSpaces = random.nextInt(5) + 1;
            for (int j = 0; j < numSpaces; j++) {
                result.append(' ');
            }
        }
        return result.toString();
    }
}