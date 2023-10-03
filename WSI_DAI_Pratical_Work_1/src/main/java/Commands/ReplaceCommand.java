package Commands;

import picocli.CommandLine.*;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 *  -----------------------------------------------------------------------------------
 * @author       : Slimani Walid
 * @date         : 28.09.2023
 *
 * @Description  : This class is used to perform the command that can replace the first or all occurrences
 *                 of a word with another one. It can also specify the encoding types of the
 *                 output file.
 *  -----------------------------------------------------------------------------------
 **/

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
    @Option(names = {"-oe", "--output-file-encoding"},
            description = "type of output text file encoding (default : UTF-8)", defaultValue = "UTF-8")
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

    /**
     * Nom          : run
     * Description  : This is the method called when the replace command is used. This method calls a first
     *                method that checks that the arguments are correct, then a second that makes the
     *                word replacement.
     */
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

    /**
     * Nom          : ReplaceWord
     * Description  : This function reads the input text and rewrites it in the output text, replacing
     *                a given word with another one.
     * @throws IOException if an argument is incorrect or there's a problem when reading / writing a text file.
     */
    private void ReplaceWord() throws IOException {
        // Creating Charset for output file
        Charset outputFileCharset = Charset.forName(outputFileEncoding);

        // Creating the files
        try (BufferedReader inputFile = new BufferedReader(new FileReader(inputFilePath, StandardCharsets.UTF_8));
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
                } else
                    outputFile.write(currentLine);

                outputFile.newLine();
            }
        }
    }

    /**
     * Nom          : validateInput
     * Description  : This function checks that the arguments given are valid.
     * @throws IOException if an argument is incorrect.
     */
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

        if (!Charset.isSupported(outputFileEncoding))
            throw new IOException("Invalid output file encoding specified.");

        if (!replaceAll.equals("true") && !replaceAll.equals("false"))
            throw new IOException("Replace all parameter can't be different from true or false");
    }
    // endregion
    // endregion
}