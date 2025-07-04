package info.kgeorgiy.ja.stolbov.walk;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MyFileVisitor extends SimpleFileVisitor<Path> {


    private String hashValue;
    final String NULL_HASH = "0000000000000000";
    final String FUNC_HASH = "SHA-256";
    final byte[] buffer = new byte[1024]; // :NOTE: private?
    // :NOTE: buffer size in static const

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        try (InputStream inputStream = Files.newInputStream(file)) {
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            System.err.println("Problems with file processing during crawling" + e.getMessage());
            hashValue = NULL_HASH;
            return FileVisitResult.CONTINUE;
        }


        try {
            MessageDigest digest = MessageDigest.getInstance(FUNC_HASH);
            byte[] encodedHash = digest.digest(result.toByteArray());
            StringBuilder hexString = new StringBuilder(2 * encodedHash.length);

            for (int i = 0; i < 8; i++) {
                String hex = Integer.toHexString(0xff & encodedHash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            hashValue = hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException e) throws IOException { // :NOTE: redundant throw
        System.err.println("Problems with file processing during crawling ");
        hashValue = NULL_HASH;
        return FileVisitResult.CONTINUE;
    }

    public String getHashValue() {
        return hashValue;
    }

}