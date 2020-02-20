package com.boatcharter.boatImage;

import com.boatcharter.boat.Boat;
import com.boatcharter.boat.BoatRepository;
import com.boatcharter.exception.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping ("api/v1/image")
public class BoatImageController {

    private final BoatImageService boatImageService;
    private final BoatRepository boatRepository;

    @Autowired
    public BoatImageController (BoatImageService boatImageService, BoatRepository boatRepository) {
        this.boatImageService = boatImageService;
        this.boatRepository = boatRepository;
    }

    @GetMapping(value = "/{boatId}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<byte[]> getBoatImage (@PathVariable("boatId") Long boatId) throws IOException {
        return ResponseEntity.ok(boatImageService.loadImage(boatId));
    }

    @PostMapping("/{boatId}")
    public ResponseEntity<String> uploadBoatImage (@PathVariable("boatId") Long boatId, @RequestParam("boatImage") MultipartFile boatImage) {
        String boatImageFileName = "";
        try {
            boatImageFileName = boatImageService.uploadImage(boatImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Boat updateBoat = boatRepository.findById(boatId).orElseThrow(()->new EntityNotFound(boatId));
        updateBoat.setBoatImageFileName(boatImageFileName);
        boatRepository.save(updateBoat);
        return ResponseEntity.ok(boatImageFileName);
    }



}
