import Commands.ConvertBlackWhiteCommand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * -----------------------------------------------------------------------------------
 * @author      : Slimani Walid
 * @date        : 04.10.2023
 * @Description : This class contains unit tests for the convertBlackWhiteCommand class, which
 *                convert an image to black and white.
 * -----------------------------------------------------------------------------------
 **/

public class ConvertFunctionTest {
    // region Images
    private final String inputImagePath = "bird.jpg";
    private final String outputImagePath = "bird_converted.jpg";
    // endregion

    // region Test function

    /**
     * Nom          : testConvertToBlackAndWhite
     * Description  : Test if the input image has been converted to black & white.
     */
    @Test
    @DisplayName("Convert to black and white")
    public void testConvertToBlackAndWhite() {
        try {
            verifyOutputImage(inputImagePath, outputImagePath);
        }catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    // endregion

    // region Private function

    /**
     * nom : verifyOutputImage
     * Description          : Verifies if the converted output image is in grayscale (black and white)
     *                        by analysing each pixel of the image.
     * @param inputImage    : The path to the input image.
     * @param outputImage   : The path to the converted output image.
     * @throws IOException If there is an error reading the output image.
     */
    private void verifyOutputImage(String inputImage, String outputImage) throws IOException {
        // Execute convert command
        executeConvertCommand(inputImage, outputImage);

        // Load output image
        BufferedImage convertedImage = ImageIO.read(new File(outputImage));

        // Check if the output image is converted to black and white
        int width = convertedImage.getWidth();
        int height = convertedImage.getHeight();

        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                int pixelColor = convertedImage.getRGB(x, y);
                Color color = new Color(pixelColor);
                // If the R, G and B components are equal for a given pixel,
                // this means that the pixel is grayscale.
                assertEquals(color.getRed(), color.getGreen(),
                        "The pixel in position (" + x + ", " + y + ") is not grayscale.");
                assertEquals(color.getGreen(), color.getBlue(),
                        "The pixel in position (" + x + ", " + y + ") is not grayscale.");
            }
        }
    }

    /**
     * nom                : executeConvertCommand
     * Description        : Executes the command to convert an image to black and white using
     *                      the ConvertBlackWhiteCommand class.
     * @param inputImage  : The path to the input image.
     * @param outputImage : The path to the converted output image.
     */
    private void executeConvertCommand(String inputImage, String outputImage) {
        ConvertBlackWhiteCommand convertCommand = new ConvertBlackWhiteCommand();
        convertCommand.setInputImagePath(inputImage);
        convertCommand.setOutputImagePath(outputImage);
        convertCommand.run();
    }
    // endregion
}
