# MyApp - Java CLI Application with Picocli


## Description

MyApp is a Java Command-Line Interface (CLI) application that operates using the Picocli library. It allows you to execute two main commands: Replace and Convert. To use this application, you need to compile the Java classes and packages into a JAR file. Make sure to also import the Picocli library into your project.

## Compilation and Execution

1. Compile the Java classes and packages of the application.
2. Create a JAR file containing the compiled classes.
3. Ensure you import the Picocli library into your project.

To run the application, use the following command :

```bash
$ java -jar MyApp.jar [command] [options]
```

Where [command] is one of the available commands (Replace or Convert), and [options] are the specific arguments for each command. For detailed information on each command, run :

```bash
$ java -jar MyApp.jar [command] -h
```

## Features
### Replace Command
The Replace command allows you to search and replace a given word with another one in a text file. The original input file must be created and edited beforehand, as well as the output file, which can be empty. You can choose to replace only the first occurrence or all occurrences of the word.

#### Example Usage 1 :

```bash
$ java -jar MyApp.jar Replace input.txt output.txt oldWord newWord
```

Additional options :

+ -oe or --output-file-encoding : Specify the output file encoding type (default: UTF-8).
+ -ao or --all-occurrences : If this option is set to true (default), all occurrences of oldWord will be replaced by newWord. If it is set to false, only the first occurrence will be replaced.
  
#### Example Usage 2 (replace only the first occurrence) :

```bash
$ java -jar MyApp.jar Replace input.txt output.txt oldWord newWord -ao false
```

### Convert Command
The Convert command allows you to take an input image and create a grayscale version as output. You can specify the path and name of the output file, as well as the extension of the output image. The output image can be saved in JPG, JPEG, or PNG format.

#### Example Usage :

```bash
$ java -jar MyApp.jar Convert input.jpg output.png
```

## Running Unit Tests
To ensure the proper functioning of the application, unit tests have been included. You can execute these tests using the following commands :

```bash
# For Convert command tests
mvn test -Dtest=ConvertFunctionTest

# For Replace command tests
mvn test -Dtest=ReplaceFunctionTest
```
### Replace Command Unit Tests
#### Test 1 : Verification of Input File Integrity
This test involves comparing the input file with the validation input file after executing the command. Make sure you have an input file with text and prepare validation files manually.

```bash
mvn test -Dtest=ReplaceFunctionTest#testInputFileModification
```

#### Test 2 : Replacement of the First Occurrence
This test involves comparing the output file (edited during command execution) with the validation file where only the first occurrence is replaced.

```bash
mvn test -Dtest=ReplaceFunctionTest#testReplaceFirstOccurrence
```

#### Test 3 : Remplacement de toutes les occurrences
This test involves comparing the output file (edited during command execution) with the validation file where all occurrences are replaced.

```bash
mvn test -Dtest=ReplaceFunctionTest#testReplaceAllOccurrence
```

### Convert Command Unit Test
#### Test 1 : Conversion to Black and White
This test involves executing the convert command and iterating over each pixel of the output image. For each pixel, we compare the RGB components. If they are not equal, it means that the pixel is not a shade of gray.

```bash
mvn test -Dtest=ConvertFunctionTest
```

## File Locations

### Replace Function

+ Original input file: `WSI_DAI_Practical_Work_1/inputFile.txt`
+ Edited file by the Replace function when modifying only the first occurrence: `WSI_DAI_Practical_Work_1/outputFileFirst.txt`
+ Edited file by the Replace function when modifying all occurrences: `WSI_DAI_Practical_Work_1/outputFileAll.txt`
+ Validation file for the input file: `WSI_DAI_Practical_Work_1/src/test/testInputFile.txt`
+ Validation file for modifying the first occurrence: `WSI_DAI_Practical_Work_1/src/test/testOutputFileReplaceFirstOccurrence.txt`
+ Validation file for modifying all occurrences: `WSI_DAI_Practical_Work_1/src/test/testOutputFileReplaceAllOccurrence.txt`

### Convert Command

+ Input image: `WSI_DAI_Practical_Work_1/bird.jpg`
+ Image converted to grayscale: `WSI_DAI_Practical_Work_1/<name.extension>` (the name of the output image and its extension will be the argument provided when using the command, for example: `bird_converted.png`)

## Remarks
This project was developed as part of a lab using the Picocli library for creating a Java command-line interface. It is provided as an example and can be used as a basis for developing more complex CLI applications.
