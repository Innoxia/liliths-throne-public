package com.lilithsthrone.utils;

import com.lilithsthrone.main.Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @since 0.2.10
 * @version 0.2.10
 * @author Addi
 */
public class FileUtils {

    /**
     * Returns the formatted date of the last modification of the given file.
     * @param file The file to check for the last modification
     * @return A string containing the date of the modification
     */
    public static String getFileTime(File file) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy - hh:mm");
        return dateFormat.format(file.lastModified());
    }

    /**
     * Compiles a list of all files in the given path matching the given pattern, e.g. "*.xml" for all XML files.
     * @param path The directory to search
     * @param pattern The file name pattern to include
     * @return A list of files matching the pattern
     */
    public static List<File> listFiles(String path, String pattern) {
        List<File> filesList = new ArrayList<>();

        // Iterate over all files in the directory matching the pattern
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(path), pattern)) {
            for (Path entry : stream) {
                filesList.add(entry.toFile());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sort the files from newest to oldest
        filesList.sort(Comparator.comparingLong(File::lastModified).reversed());
        return filesList;
    }

    /**
     * Utility method to construct the paths from strings. See {@link FileUtils#copyFile(Path, Path, boolean)} for details.
     * @param source A string containing the path to the source file
     * @param destination A string containing the path to the destination file
     */
    public static boolean copyFile(String source, String destination) {
        return copyFile(Paths.get(source), Paths.get(destination), false);
    }

    /**
     * Copies a single file from the source to the target. Returns success and flashes a message if the operation fails.
     * @param source The path of the source file
     * @param destination The path of the destination file
     * @param atomic Indicates if the operation should execute in a single step, which guarantees thread safety and
     *               completeness, but is more likely to cause an error
     * @return True if the operation succeeded, false otherwise
     */
    public static boolean copyFile(Path source, Path destination, boolean atomic) {
        try {
            // Skip existing directories
            if (Files.isDirectory(destination)) {
                return true;
            }

            // Create parent directories if necessary
            if (!Files.exists(destination.getParent())) {
                Files.createDirectories(destination.getParent());
            }

            if (atomic) {
                // Copy to temporary file then move atomically
                Path tmp = destination.getParent().resolve(destination.getFileName() + ".tmp");
                Files.copy(source, tmp, LinkOption.NOFOLLOW_LINKS);
                Files.move(tmp, destination, StandardCopyOption.ATOMIC_MOVE);
            } else {
                // Copy directly
                Files.copy(source, destination, LinkOption.NOFOLLOW_LINKS, StandardCopyOption.REPLACE_EXISTING);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Main.game.flashMessage(Colour.GENERIC_BAD, "Invalid file!");
            return false;
        }
    }

    /**
     * Copies a directory recursively from the source to the target. Returns success and flashes a message if the
     * operation fails.
     * @param sourcePath The path of the source directory
     * @param destinationPath The path of the destination directory
     * @return True if the operation succeeded, false otherwise
     */
    public static boolean copyDirectory(String sourcePath, String destinationPath) {
        Path sourceFolder = Paths.get(sourcePath);
        if (Files.exists(sourceFolder)) {
            Path destinationFolder = Paths.get(destinationPath);

            // Traverse the file tree and copy each file
            try (Stream<Path> fileStream = Files.walk(sourceFolder)) {
                fileStream.forEach(source -> {
                    Path destination = destinationFolder.resolve(sourceFolder.relativize(source));
                    copyFile(source, destination, false);
                });
            } catch (IOException e) {
                e.printStackTrace();
                Main.game.flashMessage(Colour.GENERIC_BAD, "Invalid folder!");
                return false;
            }
        }
        return true;
    }

    /**
     * Utility method to construct the path from the given string. See {@link FileUtils#deleteFile(Path)} for details.
     * @param path A string containing the file path
     */
    public static boolean deleteFile(String path) {
        return deleteFile(Paths.get(path));
    }

    /**
     * Deletes the file with the given path. Returns success and flashes a message if the operation fails.
     * @param p The path of the file
     * @return True if the operation succeeded, false otherwise
     */
    public static boolean deleteFile(Path p) {
        try {
            Files.deleteIfExists(p);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Main.game.flashMessage(Colour.GENERIC_BAD, "Invalid file!");
            return false;
        }
    }

    /**
     * Deletes the directory with the given path recursively. Returns success and flashes a message if the operation
     * fails.
     * @param path A string containing the folder path
     * @return True if the operation succeeded, false otherwise
     */
    public static boolean deleteDirectory(String path) {
        Path target = Paths.get(path);
        if (Files.exists(target)) {
            // Traverse the file tree in reverse order and delete each file
            try (Stream<Path> fileStream = Files.walk(target)) {
                fileStream.sorted(Comparator.reverseOrder())
//                        .peek(System.out::println)
                        .forEach(FileUtils::deleteFile);
            } catch (IOException e) {
                e.printStackTrace();
                Main.game.flashMessage(Colour.GENERIC_BAD, "Invalid folder!");
                return false;
            }
        }
        return true;
    }

}
