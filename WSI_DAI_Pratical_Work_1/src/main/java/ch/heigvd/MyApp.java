/**
 *  -----------------------------------------------------------------------------------
 * @Fichier      : MyApp.java
 * @Labo         : Laboratoire 1 : CLI using Picocli
 * @Authors      : Slimani Walid
 * @Date         : 05.10.2023
 * @Description  : This program allows you to run line commands using Picocli. It features the following
 *                 functions:
 *                 - Replace a word : searches for a word in a text file and replaces either the first
 *                                    or all occurrences.
 *                 - Convert image to B&W : Converts an image to black and white.
 * @Remarque     : /
 * @Modification : /
 *  -----------------------------------------------------------------------------------
 **/

package ch.heigvd;

import Commands.ConvertBlackWhiteCommand;
import Commands.ReplaceCommand;
import picocli.CommandLine;
import picocli.CommandLine.Command;

@Command(name = "cli",
         subcommands = {ReplaceCommand.class, ConvertBlackWhiteCommand.class, CommandLine.HelpCommand.class},
         description = "My CLI app can be used to execute 2 commands. The first is called <Replace> and" +
                       "the second is called <Convert>. if you need more information on how to use" +
                       "the 2 commands, you can get an instruction by typing :" +
                       "<Replace -help> or <Convert -help>.")
public class MyApp {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new MyApp()).execute(args);
        System.exit(exitCode);
    }
}
