package Commands;

import picocli.CommandLine.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * -----------------------------------------------------------------------------------
 * @author      : Slimani Walid
 * @date        : 28.09.2023
 * @Description : This class is used to perform the command that can convert an image to black and white.
 * -----------------------------------------------------------------------------------
 **/

@Command(name = "Convert", mixinStandardHelpOptions = true, version = "1.0",
        description = "This command converts an image to black and white.")
public class ConvertBlackWhiteCommand implements Runnable {
    // region Private Parameters
    // region Files Path
    @Parameters(paramLabel = "<Input-Image>", arity = "1", index = "0",
                description = "Input image path")
    private String inputImagePath;

    @Parameters(paramLabel = "<Output-Image>", arity = "1", index = "1",
                description = "Output image path")
    private String outputImagePath;
    // endregion
    // endregion

    // region Method
    // region Public Method

    /**
     * Nom          : run
     * Description  : This is the method called when the replace command is used. This method calls a first
     *                method that checks that the arguments are correct, then a second that makes
     *                the image conversion.
     */
    @Override
    public void run() {
        try {
            validateInput();
            convertToBlackAndWhite();
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // region Setter
    /**
     * Nom                   : setInputImagePath
     * Description           : Defines the path of the input image to be used for the convert function.
     * @param inputImagePath : Path of the input image.
     * @info                 : This setter exists only to be able to use the replacement command in
     *                         the test class.
     */
    public void setInputImagePath(String inputImagePath) {
        this.inputImagePath = inputImagePath;
    }

    /**
     * Nom                    : setOutputImagePath
     * Description            : Defines the path of the output image to be used for the convert function.
     * @param outputImagePath : Path of the output image.
     * @info                  : This setter exists only to be able to use the replacement command in
     *                          the test class.
     */
    public void setOutputImagePath(String outputImagePath) {
        this.outputImagePath = outputImagePath;
    }
    // endregion
    // endregion

    // region Private Method

    /**
     * Nom          : convertToBlackAndWhite
     * Description  : This function converts input image to black & white.
     * @throws IOException if the input image or output path does not exist.
     * @throws IOException if the image format is incorrect.
     * @throws IllegalArgumentException if the output file extension is not valid for the output format.
     */
    private void convertToBlackAndWhite() throws IOException, IllegalArgumentException {
        // Loading the input image
        BufferedImage image = ImageIO.read(new File(inputImagePath));

        // Create a new image with the same dimensions as the input image
        BufferedImage blackAndWhiteImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        blackAndWhiteImage.getGraphics().drawImage(image, 0, 0, null);

        // Get output file extension
        String outputExtension = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1).toLowerCase();

        // Check if the extension is valid for the output format
        if (!isValidOutputFormat(outputExtension))
            throw new IllegalArgumentException("Invalid output format. Supported formats: jpg, jpeg, png.");

        // Save converted black & white image in jpg format
        ImageIO.write(blackAndWhiteImage, outputExtension, new File(outputImagePath));
    }

    /**
     * Nom          : validateInput
     * Description  : This function checks that the arguments given are valid.
     * @throws IOException if the input image does not exist or is invalid.
     * @throws IOException If the output image directory does not exist.
     */
    private void validateInput() throws IOException {
        // Create File object for input image
        File inputImage = new File(inputImagePath);

        // Check existence of input image
        if (!inputImage.exists())
            throw new IOException("The input file does not exist.");

        // Check that the input image is a valid file (not a directory)
        if (!inputImage.isFile())
            throw new IOException("The input file is invalid.");

        // Check that the output directory exists
        File outputDir = new File(outputImagePath).getParentFile();
        if (outputDir != null && !outputDir.exists())
            throw new IOException("The output directory does not exist.");
    }

    /**
     * Nom              : isValidOutputFormat
     * Description      : This function checks if the specified extension is valid for the output format.
     *                    Accepted formats are jpg, jpeg and png.
     * @param extension : Extension we want to check.
     * @return : Boolean value indicates if the extension is valid or not.
     */
    private boolean isValidOutputFormat(String extension) {
        // List of supported image extensions
        String[] supportedExtensions = {"jpg", "jpeg", "png"};

        // Check if the extension is in the list of supported extensions
        for (String ext : supportedExtensions) {
            if (extension.equals(ext))
                return true;
        }
        return false;
    }
    // endregion
    // endregion
}
