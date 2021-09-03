package com.udemy.socialnetwork.service;

import com.udemy.socialnetwork.exception.ImageTooSmallException;
import com.udemy.socialnetwork.exception.InvalidImageFileException;
import com.udemy.socialnetwork.model.FileInfo;
import marvin.image.MarvinImage;
import org.marvinproject.image.transform.scale.Scale;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Random;

@Service
public class FileService {

    Random random = new Random();

    @Value("${photo.file.extensions}")
    private String imageExtensions;

    private String getFileExtension(String filename) {
        int dotPosition = filename.lastIndexOf(".");

        if (dotPosition < 0) {
            return null;
        }

        return filename.substring(dotPosition + 1).toLowerCase(Locale.ROOT);
    }

    private boolean isImageExtension(String extension) {
        String testExtension = extension.toLowerCase(Locale.ROOT);

        for(String validExtension : imageExtensions.split(",")) {
            if(testExtension.equals(validExtension)) {
                return true;
            }
        }
        return false;
    }

    private File makeSubDirectory(String basePath, String prefix) {
        int nDirectory = random.nextInt(1000);
        String sDirectory = String.format("%s%03d", prefix, nDirectory);

        File directory = new File(basePath, sDirectory);

        if(!directory.exists()) {
            directory.mkdir();
        }
        return directory;
    }

    public FileInfo saveImageFile(MultipartFile file, String baseDirectory, String subDirPrefix, String filePrefix, int width, int height) throws InvalidImageFileException, IOException, ImageTooSmallException {
        int nFilename = random.nextInt(1000);
        String fileName = String.format("%s%03d", filePrefix, nFilename);

        String extension = getFileExtension(file.getOriginalFilename());

        if(extension == null ) {
            throw new InvalidImageFileException("No file exception");
        }

        if (isImageExtension(extension) == false) {
            throw new InvalidImageFileException("Not an image file");
        }

        File subDirectory = makeSubDirectory(baseDirectory, subDirPrefix);

        Path filepath = Paths.get(subDirectory.getCanonicalPath(), fileName + "." + extension);

        BufferedImage resizedImage = resizeImage(file, width, height);
        ImageIO.write(resizedImage, extension, filepath.toFile());

        return new FileInfo(fileName, extension, subDirectory.getName(), baseDirectory);


    }

    private BufferedImage resizeImage(MultipartFile inputFile, int targetWidth, int targetHeight) throws IOException, ImageTooSmallException {
        BufferedImage originalImage = ImageIO.read(inputFile.getInputStream());

        if (originalImage.getWidth() < 100 || originalImage.getHeight() < 100) {
            throw new ImageTooSmallException();
        }
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        graphics2D.dispose();
        return resizedImage;

    }

}
