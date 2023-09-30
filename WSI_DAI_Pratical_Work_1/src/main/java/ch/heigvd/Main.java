/**
 *  -----------------------------------------------------------------------------------
 * @Fichier      : Main.java
 * @Labo         : Laboratoire 1 : CLI using Picocli
 * @Authors      : Slimani Walid
 * @Date         : 28.09.2023
 *
 * @Description  : This program allows you to run line commands using Picocli. It features the following
 *                 functions:
 *                 - Replace a word : searches for a word in a text file and replaces either the first
 *                                    or all occurrences.
 *                 - Convert image to B&W : Converts an image to black and white.
 *
 * @Remarque     : /
 * @Modification : /
 *  -----------------------------------------------------------------------------------
 **/

package ch.heigvd;

import Commands.*;
import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new ConvertBlackWhiteCommand()).execute(args);
        System.exit(exitCode);
    }
}