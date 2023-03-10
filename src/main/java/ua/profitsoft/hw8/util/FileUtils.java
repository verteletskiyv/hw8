package ua.profitsoft.hw8.util;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.profitsoft.hw8.controller.PepController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


/**
 * Creates temporary files in {@link PepController#upload(MultipartFile) method}.
 */
public class FileUtils {

    public static File getUnzippedFile(MultipartFile file, File tempDir) {
        Path zip = getFile(file, tempDir);
        File newFile = tempDir;
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zip.toString()))) {
            byte[] buffer = new byte[1024];
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                newFile = newFile(tempDir, zipEntry);
                if (zipEntry.isDirectory()) {
                    if (!newFile.isDirectory() && !newFile.mkdirs())
                        throw new IOException("Failed to create directory " + newFile);
                } else {
                    File parent = newFile.getParentFile();
                    if (!parent.isDirectory() && !parent.mkdirs())
                        throw new IOException("Failed to create directory " + parent);
                    try (FileOutputStream fos = new FileOutputStream(newFile)) {
                        int len;
                        while ((len = zis.read(buffer)) > 0)
                            fos.write(buffer, 0, len);
                    }
                }
                zipEntry = zis.getNextEntry();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to unzip file: " + zip.getFileName());
        } finally {
            zip.toFile().delete();
        }
        return newFile;
    }

    public static Path getFile(MultipartFile file, File tempDir) {
        tempDir.mkdirs();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
        Path path = Paths.get(tempDir.toString() +'/'+ fileName);
        try {
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Failed to process file: " + fileName +"; "+e.getMessage());
        }
        return path;
    }

    private static File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        File destFile = new File(destinationDir, zipEntry.getName());
        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();
        if (!destFilePath.startsWith(destDirPath + File.separator))
            throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
        return destFile;
    }
}
