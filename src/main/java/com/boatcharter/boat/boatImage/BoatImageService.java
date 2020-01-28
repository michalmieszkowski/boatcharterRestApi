package com.boatcharter.boat.boatImage;

import com.boatcharter.boat.Boat;
import com.boatcharter.boat.BoatRepository;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class BoatImageService {

  //  https://panel43.mydevil.net/file_manager/?goto=my-files%2FboatsImages



    private final static String IMAGES_DIR = "C:/boatsImages/";

    private BoatRepository boatRepository;

    public BoatImageService (BoatRepository boatRepository) {
        this.boatRepository = boatRepository;
    }

    public String uploadImage(MultipartFile image) throws IOException {

        if (null != image) {
            File boatImage = new File(IMAGES_DIR + image.getOriginalFilename());
            FileOutputStream fileOutputStream = new FileOutputStream(boatImage);
            fileOutputStream.write(image.getBytes());
            fileOutputStream.close();
            return image.getOriginalFilename();
        } else {
            return "no image.jpg";
        }
    }

    public byte[] loadImage(Long boatId) throws IOException {
        Boat boat = boatRepository.findById(boatId).orElseThrow(() -> new IllegalStateException("Boat with id: " + boatId + " not found"));
        String boatImageFileName = boat.getBoatImageFileName();
        UrlResource externalImagesDir = new UrlResource("file:///C:/boatsImages/" + boatImageFileName);
        //UrlResource externalImagesDir = new UrlResource("boatsimages/" + boatImageFileName);
        InputStream inputStream = externalImagesDir.getInputStream();
        return StreamUtils.copyToByteArray(inputStream);
    }

    public void deleteImage(Long boatId) {
        Boat boat = boatRepository.findById(boatId).orElseThrow(() -> new IllegalStateException("Boat with id: " + boatId + " not found"));
        String boatImageFileName = boat.getBoatImageFileName();
        if (!boatImageFileName.equals("no image.jpg")) {
            File boatImage = new File("C:/boatsImages/" + boatImageFileName);
            boatImage.delete();
            System.out.println("file " + boatImageFileName + " deleted");
        }

    }
}
