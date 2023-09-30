package Commands;

import picocli.CommandLine.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Objects;

@Command(name = "Replace", mixinStandardHelpOptions = true, version = "1.0",
        description = "this command permits to replaces one word in a text file with another one.")
public class ReplaceCommand implements Runnable {
    // region Private Parameters
    // region Files Path
    @Parameters(paramLabel = "<Input-File>", arity = "1", index = "0",
            description = "Input text file path")
    private String inputFilePath;

    @Parameters(paramLabel = "<Output-File>", arity = "1", index = "1",
            description = "Output text file path")
    private String outputFilePath;
    // endregion

    // region Words
    @Parameters(paramLabel = "<Old-Word>", arity = "1", index = "2",
            description = "The word that will be replaced")
    private String oldWord;

    @Parameters(paramLabel = "<New-Word>", arity = "1", index = "3",
            description = "The new word that will replace the old one")
    private String newWord;
    // endregion

    // region File Encoding Types
    @Option(names = {"-ie", "--input-file-encoding"},
            description = "type of input text file encoding (default : UTF-8)", defaultValue = "UTF-8")
    private String inputFileEncoding;

    @Option(names = {"-oe", "--output-file-encoding"},
            description = "type of output text file encoding (default : UTF-8)",  defaultValue = "UTF-8")
    private String outputFileEncoding;
    // endregion

    // region Replace all
    @Option(names = {"-ao", "--all-occurrences"},
            description = "Replace all occurrences of the text", defaultValue = "true")
    private String replaceAll;
    // endregion
    // endregion

    // region Method
    // region Public Method
    @Override
    public void run() {
        try {
            validateInput();
            ReplaceWord();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    // endregion

    // region Private Method
    private void ReplaceWord() throws IOException {
        // Creating Charset
        Charset inputFileCharset = Charset.forName(inputFileEncoding);
        Charset outputFileCharset = Charset.forName(outputFileEncoding);

        // Creating the files
        try (BufferedReader inputFile = new BufferedReader(new FileReader(inputFilePath, inputFileCharset));
             BufferedWriter outputFile = new BufferedWriter(new FileWriter(outputFilePath, outputFileCharset))) {

            String currentLine;
            String tempLine;
            boolean firstOccurrenceReplaced = false;

            while ((currentLine = inputFile.readLine()) != null) {
                if (currentLine.contains(oldWord) && !firstOccurrenceReplaced) {
                    tempLine = currentLine;

                    if (replaceAll.equals("true"))
                        tempLine = tempLine.replaceAll(oldWord, newWord);

                    else if (replaceAll.equals("false")) {
                        tempLine = tempLine.replaceFirst(oldWord, newWord);
                        firstOccurrenceReplaced = true;
                    }
                    outputFile.write(tempLine);
                }
                else
                    outputFile.write(currentLine);

                outputFile.newLine();
            }
        }
    }

    private void validateInput() throws IOException {
        // No opening of file streams requiring closing, so no try with resources
        File inputFile = new File(inputFilePath);
        File outputFile = new File(outputFilePath);

        if (!inputFile.exists() || !outputFile.exists())
            throw new IOException("Input or output file does not exist.");

        if (!inputFile.isFile() || !outputFile.isFile())
            throw new IOException("Input or output is not a valid file.");

        if (oldWord == null || oldWord.isEmpty())
            throw new IOException("Old word cannot be empty.");

        if (newWord == null)
            throw new IOException("New word cannot be null.");

        if (!Charset.isSupported(inputFileEncoding))
            throw new IOException("Invalid input file encoding specified.");

        if (!Charset.isSupported(outputFileEncoding))
            throw new IOException("Invalid output file encoding specified.");

        if (!Objects.equals(replaceAll, "true") && !Objects.equals(replaceAll, "false"))
            throw new IOException("Replace all parameter can't be different from true or false");
    }
    // endregion
    // endregion
}