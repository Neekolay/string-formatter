package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static final int MAX_LENGTH = 80;

    public static void main(String[] args) throws IOException {
        System.out.println("Type some text:");
        var reader = new BufferedReader( new InputStreamReader(System.in));
        var input = reader.readLine();
        System.out.println("Output: " + splitString(input));
    }

    public static String splitString(String input) {
        if (input == null || input.isBlank()) {
            return "";
        }
        var output = new StringBuilder();
        var line = new StringBuilder();
        var words = input.split(" ");

        for (var word : words) {
            if (word.isEmpty() || word.length() > MAX_LENGTH) {
                continue;
            }
            if (line.length() + word.length() <= MAX_LENGTH) {
                line.append(word).append(" ");
            } else {
                writeLineToOutput(output, line);
                line = new StringBuilder();
                line.append(word).append(" ");
            }
        }
        writeLineToOutput(output, line);
        return output.toString();
    }

    private static void writeLineToOutput(StringBuilder output, StringBuilder line) {
        if (!line.isEmpty()) {
            line.replace(line.length() - 1, line.length(), "\n");
        }
        output.append(line);
    }

}