import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import Commands.ReplaceCommand;

/**
 * -----------------------------------------------------------------------------------
 *
 * @author      : Slimani Walid
 * @date        : 03.10.2023
 * @Description : This class contains unit tests for the ReplaceCommand class, which
 *                replaces occurrences of a word in a text file.
 * -----------------------------------------------------------------------------------
 **/

public class ReplaceFunctionTest {
    // region Input file and file wrote by the code
    private final String inputFilePath = "inputFile.txt";
    private final String outputFileAllPath = "outputFileAll.txt";
    private final String outputFileFirstPath = "outputFileFirst.txt";
    // endregion

    // region File created manually for validate the tests
    private final String expectedInputFilePath = "./src/test/testInputFile.txt";
    private final String expectedOutputFileAllPath = "./src/test/testOutputFileReplaceAllOccurrence.txt";
    private final String expectedOutputFileFirstPath = "./src/test/testOutputFileReplaceFirstOccurrence.txt";
    // endregion

    // region Test functions

    /**
     * Nom          : testReplaceAllOccurrence
     * Description  : Tests the replacement of all occurrences of a word in a file.
     * @throws IOException if an input/output error occurs during file playback.
     */
    @Test
    @DisplayName("Test all occurrences")
    public void testReplaceAllOccurrence() throws IOException {
        String assertMsg = "Not all occurrences have been replaced";

        try {
            compareFile(inputFilePath, outputFileAllPath, expectedOutputFileAllPath,
                    "fruits", "noix", "UTF-8", "true", assertMsg);
        }catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Nom          : testReplaceFirstOccurrence
     * Description  : Tests the replacement of the first occurrence of a word in a file.
     * @throws IOException if an input/output error occurs during file playback.
     */
    @Test
    @DisplayName("Test first occurrence")
    public void testReplaceFirstOccurrence() throws IOException {
        String assertMsg = "More than one or zero occurrence has been replaced";

        try {
            compareFile(inputFilePath, outputFileFirstPath,expectedOutputFileFirstPath,
                    "fruits", "noix", "UTF-8", "false", assertMsg);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    /**
     * Nom          : testInputFileModification
     * Description  : Tests the modification of the input file by the replace function.
     * @throws IOException if an input/output error occurs during file playback.
     */
    @Test
    @DisplayName("Input file modification")
    public void testInputFileModification() throws IOException {
        String assertMsg = "The input file has been modified by the Replace function.";

        try {
            compareInputFile(inputFilePath, outputFileFirstPath,expectedInputFilePath,
                    "fruits", "noix", "UTF-8", "false", assertMsg);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }
    // endregion

    // region Private function

    /**
     * Nom                 : compareFile
     * Description         : Compares the content of a file with the expected content after replacing$
     *                       specific word occurrences and verifies that the content matches the
     *                       expected content.
     * @info               : This method compares the file that has been written with the expected file.
     * @param inputFile    : Path of the input file containing the text to process.
     * @param fileToWrite  : Path of the output file where the processed text will be written.
     * @param validateFile : Path of the validation file containing the expected text after replacement.
     * @param oldWord      : Word to replace in the text.
     * @param newWord      : New word that will replace the old word.
     * @param charset      : File encoding.
     * @param replaceAll   : Indicates whether all occurrences of the word should be replaced (true) or
     *                       only the first one (false).
     * @param assertMsg    : Assertion message in case of a difference between the obtained content and
     *                       the expected content.
     * @throws IOException if an I/O error occurs while reading or writing files.
     */
    private void compareFile(String inputFile, String fileToWrite, String validateFile,
                             String oldWord, String newWord, String charset, String replaceAll,
                             String assertMsg) throws IOException {

        try (BufferedReader wroteFile = new BufferedReader(new FileReader(fileToWrite));
             BufferedReader expectedFile = new BufferedReader(new FileReader(validateFile))) {

            executeReplaceCommand(inputFile, fileToWrite, oldWord, newWord, charset, replaceAll);

            String inputLine;
            String expectedLine;

            while ((inputLine = wroteFile.readLine()) != null) {
                expectedLine = expectedFile.readLine();
                assert inputLine.equals(expectedLine) : assertMsg;
            }
        }
    }

    /**
     * Nom                 : compareInputFile
     * Description         : Compares the content of a file with the expected content after replacing$
     *                       specific word occurrences and verifies that the content matches the
     *                       expected content.
     * @info               : This method compares the input file with the expected input file.
     * @param inputFile    : Path of the input file containing the text to process.
     * @param fileToWrite  : Path of the output file where the processed text will be written.
     * @param validateFile : Path of the validation file containing the expected text after replacement.
     * @param oldWord      : Word to replace in the text.
     * @param newWord      : New word that will replace the old word.
     * @param charset      : File encoding.
     * @param replaceAll   : Indicates whether all occurrences of the word should be replaced (true) or
     *                       only the first one (false).
     * @param assertMsg    : Assertion message in case of a difference between the obtained content and
     *                       the expected content.
     * @throws IOException if an I/O error occurs while reading or writing files.
     */
    private void compareInputFile(String inputFile, String fileToWrite,
                             String validateFile,String oldWord, String newWord,
                             String charset, String replaceAll,
                             String assertMsg) throws IOException {

        try (BufferedReader inputTextFile = new BufferedReader(new FileReader(inputFile));
             BufferedReader expectedFile = new BufferedReader(new FileReader(validateFile))) {

            executeReplaceCommand(inputFile, fileToWrite, oldWord, newWord, charset, replaceAll);

            String inputLine;
            String expectedLine;

            while ((inputLine = inputTextFile.readLine()) != null) {
                expectedLine = expectedFile.readLine();
                assert inputLine.equals(expectedLine) : assertMsg;
            }
        }
    }

    /**
     * nom                : executeReplaceCommand
     * Description        : Executes the replace command using the parameters given, such as the old word,
     *                      the new word, the encoding and whether to replace all occurrences or just
     *                      the first.
     *
     * @param inputFile   : Path of the input file containing the text to process.
     * @param fileToWrite : Path of the output file where the processed text will be written.
     * @param oldWord     : Word to replace in the text.
     * @param newWord     : New word that will replace the old word.
     * @param charset     : File encoding.
     * @param replaceAll  : Indicates whether all occurrences of the word should be replaced (true) or
     *                      only the first one (false).
     * @throws IOException if an I/O error occurs while reading or writing files.
     */
    private void executeReplaceCommand(String inputFile, String fileToWrite,
                                       String oldWord, String newWord,
                                       String charset, String replaceAll) throws IOException {
        ReplaceCommand replaceCommand = new ReplaceCommand();
        replaceCommand.setInputFilePath(inputFile);
        replaceCommand.setOutputFilePath(fileToWrite);
        replaceCommand.setOldWord(oldWord);
        replaceCommand.setNewWord(newWord);
        replaceCommand.setCharSet(charset);
        replaceCommand.setReplaceAll(replaceAll);
        replaceCommand.ReplaceWord();
    }
    // endregion
}