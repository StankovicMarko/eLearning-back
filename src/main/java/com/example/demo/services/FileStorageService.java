package com.example.demo.services;

import com.example.demo.model.FileStorageProperties;
import com.example.demo.model.exceptions.FileStorageException;
import com.example.demo.model.exceptions.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

@Service
public class FileStorageService {

    private final List<String> allowedExtensions = Arrays.asList("txt", "pdf", "doc", "jpg", "jpeg", "png", "gif");

    private Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file, long timestamp) {
        if (!isValidExtension(file.getOriginalFilename())) {
            throw new FileStorageException("Sorry! Invalid file extension.");
        }

        String fileName = StringUtils.cleanPath(String.valueOf(timestamp) + "_" +
                file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }

    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    public boolean deleteFile(String fileName) {
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        try {
            Files.delete(targetLocation);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    private boolean isValidExtension(String fileName) {
        String[] splittedName = fileName.split("\\.");
        String extension = splittedName[splittedName.length - 1].toLowerCase();
        return allowedExtensions.contains(extension);
    }

}
