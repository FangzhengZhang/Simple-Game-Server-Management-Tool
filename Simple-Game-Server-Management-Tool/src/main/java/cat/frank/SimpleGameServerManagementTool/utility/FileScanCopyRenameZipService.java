package cat.frank.SimpleGameServerManagementTool.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class FileScanCopyRenameZipService {
    // Logger of this class
    private static final Logger logger = LoggerFactory.getLogger(FileScanCopyRenameZipService.class);

    // Scan a directory and find all files that start with a specific string
    public static List<Path> findFilesStartingWith(String directoryPath, String fileNameStartsWith) throws Exception {
        logger.info("Scanning directory: {} for files starting with: {}", directoryPath,fileNameStartsWith);
        // Get the Path object of the directory
        Path directory = Paths.get(directoryPath);

        // Use the Files class to get a list of all files in the directory
        try (Stream<Path> files = Files.list(directory)) {
            // Use the Files class to get a list of all files in the directory
            return files.filter(path -> path.getFileName().toString().startsWith(fileNameStartsWith))
                    .collect(Collectors.toList());
        }
    }

    public static List<Path> findFilesTypeStartingWith(String directoryPath, String fileNameStartsWith, String type)
            throws Exception {
        logger.info("Scanning directory: {} for files starting with: {} and type: {}",
                directoryPath,fileNameStartsWith,type);
        // Get the Path object of the directory
        Path directory = Paths.get(directoryPath);

        // Use the Files class to get a list of all files in the directory
        try (Stream<Path> files = Files.list(directory)) {
            // Use the Files class to get a list of all files in the directory
            return files.filter(path -> path.getFileName().toString().startsWith(fileNameStartsWith)
                            && path.getFileName().toString().endsWith(type))
                    .collect(Collectors.toList());
        }
    }

    public static List<Path> findFilesTypeStartingWithAndSizeLagerThan(String directoryPath, String fileNameStartsWith,
                                                                String type, long size) throws Exception {
        logger.info("Scanning directory: {} for files starting with: {} and type: {} and size larger than: {}",
                directoryPath,fileNameStartsWith,type,size);
        // Get the Path object of the directory
        Path directory = Paths.get(directoryPath);

        // Use the Files class to get a list of all files in the directory
        try (Stream<Path> files = Files.list(directory)) {
            // Use the Files class to get a list of all files in the directory
            return files.filter(path -> path.getFileName().toString().startsWith(fileNameStartsWith)
                            && path.getFileName().toString().endsWith(type)
                            && path.toFile().length() >= size)
                    .collect(Collectors.toList());
        }

    }

    // Copy a file to a new location with a new name
    public static void copyFile(Path source, Path destination, String newFileName) throws Exception {
        logger.info("Copying file: {} to: {} with new name: {}", source, destination, newFileName);
        // Copy the file to the new location with a new name
        Files.copy(source, destination.resolve(newFileName));
    }

    // Rename a file
    public static Path renameFile(Path source, String newFileName) throws Exception {
        logger.info("Renaming file: {} to: {}", source,newFileName);
        // Rename the file
        return Files.move(source, source.resolveSibling(newFileName));
    }

    // Zip a file
    public static void zipFile(Path source, Path destination, String newFileName) throws Exception {
        logger.info("Zipping file: {} to: {} with a new name: {}", source, destination, newFileName);
        // Zip the file to the new location with a new name
        // Create a FileOutputStream for the zip file
        FileOutputStream fos = new FileOutputStream(destination + File.separator + newFileName);

        // Create a ZipOutputStream from the FileOutputStream
        ZipOutputStream zos = new ZipOutputStream(fos);

        // Create a FileInputStream for the source file
        FileInputStream fis = new FileInputStream(source.toFile());

        // Add the file to the zip archive
        ZipEntry zipEntry = new ZipEntry(newFileName);
        zos.putNextEntry(zipEntry);

        // Write the file contents to the zip archive
        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        // Close the streams
        zos.closeEntry();
        zos.close();
        fis.close();
        fos.close();
    }

    // Delete a file
    public static void deleteFile(Path source) throws Exception {
        logger.info("Deleting file: {}", source);
        // Delete the file
        Files.delete(source);
    }

    // Rename a file, zip and delete the renamed file
    public static void renameZipDelete(Path source, Path destination, String newFileName) throws Exception {
        logger.info("Renaming file: {} to: {} with a new name: {} and zip it", source, destination, newFileName);
        // Rename the file
        Path tempFile = renameFile(source, "tmp_"+newFileName);
        // Zip the file
        zipFile(tempFile, destination, newFileName + "."+ StaticVariables.Log_File_Zip_Type);
        // Delete the renamed file
        deleteFile(tempFile);
    }

    // Find the files that start with a specific string and has a specific type.
    // If the file count more than given number, delete the oldest file
    public static void deleteOldestFileIfMoreThan(Path directory, String fileNameStartsWith, String type, int count)
            throws Exception {
        logger.info("Scanning directory: {} for files starting with: {} and type: {} and delete the oldest if more " +
                        "than: {}", directory,fileNameStartsWith,type,count);

        // Use the Files class to get a list of all files in the directory
        try (Stream<Path> files = Files.list(directory)) {
            // Use the Files class to get a list of all files in the directory
            List<Path> fileList = files.filter(path -> path.getFileName().toString().startsWith(fileNameStartsWith)
                            && path.getFileName().toString().endsWith(type))
                    .collect(Collectors.toList());
            if (fileList.size() > count) {
                fileList.sort((p1, p2) -> {
                    try {
                        return Files.getLastModifiedTime(p1).compareTo(Files.getLastModifiedTime(p2));
                    } catch (Exception e) {
                        logger.error("Error when comparing file: {} and file: {}", p1, p2);
                        return 0;
                    }
                });
                logger.info("Deleting old zipped log file: {}", fileList.get(0));
                deleteFile(fileList.get(0));
            }
        }
    }

}
