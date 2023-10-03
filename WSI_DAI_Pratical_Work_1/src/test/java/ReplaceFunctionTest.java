import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import Commands.ReplaceCommand;


public class ReplaceFunctionTest {

    // region Input file and file wrote by the code
    private final String inputFilePath = "../../inputFile.txt";
    private final String outputFileAllPath = "../../outputFileAll.txt";
    private final String outputFileFirstPath = "../../outputFileFirst.txt";
    // endregion

    // region File created manually for validate the tests
    private final String expectedInputFilePath = "testInputFile.txt";
    private final String expectedOutputFileAllPath = "testOutputFileReplaceAllOccurrence.txt";
    private final String expectedOutputFileFirstPath = "testOutputFileReplaceFirstOccurrence.txt";
    // endregion


    @Test
    public void testReplaceFirstOccurrence() throws IOException {
        try (BufferedReader outputFileAll = CreateTextFile(outputFileAllPath);
             BufferedReader expectedOutputFileAll = CreateTextFile(expectedOutputFileAllPath)) {

        }

    }

    @Test
    public void testReplaceAllOccurrence() throws IOException {
        try (BufferedReader outputFileFirst = CreateTextFile(outputFileFirstPath);
             BufferedReader expectedOutputFileFirst = CreateTextFile(expectedOutputFileFirstPath)) {
//        Assertions.assertEquals
        }
    }

    @Test
    public void testInputFileModification() throws IOException {
        try (BufferedReader inputFile = CreateTextFile(inputFilePath);
             BufferedReader expectedInputFile = CreateTextFile(expectedInputFilePath)) {
//        Assertions.assertEquals
        }

    }

    private BufferedReader CreateTextFile(String filePath) throws IOException {
        Charset standardCharset = StandardCharsets.UTF_8;
        try (BufferedReader inputFile = new BufferedReader(new FileReader(filePath, standardCharset))) {
            return inputFile;
        }
    }
}