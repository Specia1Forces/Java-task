package info.kgeorgiy.ja.stolbov.walk;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class Walk {

    final static String NULL_HASH = "0000000000000000";
    final static Charset STANDARD_CHARSETS = StandardCharsets.UTF_8;

    public static void main(String[] args) throws IOException {

        if (args == null || args.length != 2 || args[0] == null || args[1] == null) {
            System.err.println("The problem with command line arguments");
            return;
        }
        Path inputFile;
        Path outputFile;
        try {
            inputFile = Path.of(args[0]);
            outputFile = Path.of(args[1]);
        } catch (InvalidPathException ex) {
            System.err.println("Incorrect file paths " + ex.getMessage());
            return;
        }

        try {
            final Path parentPathFile = outputFile.getParent();
            if (parentPathFile != null) {
                Files.createDirectories(parentPathFile);
            }
        } catch (IOException ex) {
            System.err.println("Couldn't create directories " + ex.getMessage());
            return;
        }

        try (BufferedReader bufferedReader = Files.newBufferedReader(inputFile, STANDARD_CHARSETS)) {
            try (BufferedWriter bufferedWriter = Files.newBufferedWriter(outputFile, STANDARD_CHARSETS)) {
                String tempFile;
                MyFileVisitor myFileVisitor = new MyFileVisitor();
                while ((tempFile = bufferedReader.readLine()) != null) {

                    try {
                        Path path = Path.of(tempFile);
                        if (Files.isDirectory(path)) {
                            bufferedWriter.write(NULL_HASH + " " + tempFile + "\n");
                            continue;
                        }
                        Files.walkFileTree(path, myFileVisitor);
                        bufferedWriter.write(myFileVisitor.getHashValue() + " " + tempFile + "\n");
                    } catch (InvalidPathException ex) {
                        System.err.println("There were problems with the processed file during the crawl: " + ex.getMessage());
                        bufferedWriter.write(NULL_HASH + " " + tempFile + "\n");
                    }
                }
            } catch (IOException ex) {
                System.err.println("Errors occurred with the output file: " + ex.getMessage());
            }
        } catch (IOException ex) {
            System.err.println("There were errors with the input file: " + ex.getMessage());
        }

    }
}


