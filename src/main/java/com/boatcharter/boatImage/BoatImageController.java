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
import java.util.Base64;

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

    @GetMapping(value = "/{boatId}")
    public ResponseEntity<String> getBoatImage (@PathVariable("boatId") Long boatId) throws IOException {
        String responseByte = Base64.getEncoder().encodeToString(boatImageService.loadImage(boatId));
        return ResponseEntity.ok(responseByte);
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
