package com.boatcharter.boatImage;

import com.boatcharter.boat.Boat;
import com.boatcharter.boat.BoatRepository;
import com.boatcharter.exception.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.IOUtils;
import sun.nio.ch.IOUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class BoatImageService {

  //  https://panel43.mydevil.net/file_manager/?goto=my-files%2FboatsImages

    private final Path fileStorageLocation;
    private final BoatRepository boatRepository;

    @Autowired
    public BoatImageService(FileStorageProperties fileStorageProperties, BoatRepository boatRepository) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();
        this.boatRepository = boatRepository;
    }

    public String uploadImage(MultipartFile image) throws IOException {
        if (null != image) {
            File boatImage = new File(fileStorageLocation + "/" + image.getOriginalFilename());
            FileOutputStream fileOutputStream = new FileOutputStream(boatImage);
            fileOutputStream.write(image.getBytes());
            fileOutputStream.close();
            return image.getOriginalFilename();
        } else {
            return "no image.jpg";
        }
    }

    public byte[] loadImage(Long boatId) throws IOException {
        Boat boat = boatRepository.findById(boatId).orElseThrow(() -> new EntityNotFound(boatId));
        String boatImageFileName = boat.getBoatImageFileName();
        File boatImage = new File (fileStorageLocation + "/" + boatImageFileName);
        UrlResource externalImagesDir = new UrlResource(boatImage.toURI());
        InputStream inputStream = externalImagesDir.getInputStream();
        return StreamUtils.copyToByteArray(inputStream);
    }

    public void deleteImage(Long boatId) {
        Boat boat = boatRepository.findById(boatId).orElseThrow(() -> new EntityNotFound(boatId));
        String boatImageFileName = boat.getBoatImageFileName();
        if (!boatImageFileName.equals("no image.jpg")) {
            File boatImage = new File(fileStorageLocation + "/" + boatImageFileName);
            boatImage.delete();
            System.out.println("file " + boatImageFileName + " deleted");
        }

    }
}
