package Commands;

import picocli.CommandLine.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

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
            description = "type of input text file encoding (default : UTF-8)")
    private String inputFileEncoding = "UTF-8";

    @Option(names = {"-oe", "--output-file-encoding"},
            description = "type of output text file encoding (default : UTF-8)")
    private String outputFileEncoding = "UTF-8";
    // endregion
    // endregion

    // region Method
    // region Public Method
    @Override
    public void run() {
        try {
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

            while ((currentLine = inputFile.readLine()) != null) {
                if (currentLine.contains(oldWord)) {
                    tempLine = currentLine;
                    tempLine = tempLine.replace(oldWord, newWord);
                    outputFile.write(tempLine);
                } else {
                    outputFile.write(currentLine);
                }
            }
        }
    }
    // endregion
    // endregion
}
