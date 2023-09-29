package ch.heigvd;

import Commands.ReplaceCommand;
import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new ReplaceCommand()).execute(args);
        System.exit(exitCode);
    }
}