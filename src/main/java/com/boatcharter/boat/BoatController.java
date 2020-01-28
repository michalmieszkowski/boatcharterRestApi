package com.boatcharter.boat;

import com.boatcharter.boat.boatImage.BoatImageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping ("/boats")
public class BoatController {

    private BoatService boatService;
    private BoatImageService boatImageService;

    @Autowired
    public BoatController (BoatService boatService, BoatImageService boatImageService) {
        this.boatService = boatService;
        this.boatImageService = boatImageService;
    }

    @GetMapping
    public ResponseEntity<List<Boat>> findAllBoats(@RequestParam (required = false) String category){
        if (category.equals("motorboat")) {
            return ResponseEntity.ok(boatService.findByCategory("MOTORBOAT"));
        } else if (category.equals("yacht")) {
            return ResponseEntity.ok(boatService.findByCategory("YACHT"));
        } else if (category.equals("catamaran")) {
            return ResponseEntity.ok(boatService.findByCategory("CATAMARAN"));
        }
        return ResponseEntity.ok(boatService.findAllBoats());
    }

    @GetMapping ("/{boatId}")
    public ResponseEntity<Boat> findBoatById(@PathVariable("boatId") Long boatId)  {
        return ResponseEntity.ok(boatService.findBoatById(boatId));
    }

    @GetMapping (value = "/images/{boatId}", produces = {MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<byte[]> getBoatImage (@PathVariable("boatId") Long boatId) throws IOException {
       return ResponseEntity.ok(boatImageService.loadImage(boatId));
    }

    @PostMapping (consumes = {"multipart/form-data"})
    public ResponseEntity<Boat> addNewBoat(@RequestParam ("boat") String boat, @RequestParam (value = "image", required = false) MultipartFile image) throws IOException {
        Boat boatEntity = new ObjectMapper().readValue(boat, Boat.class);
        boatEntity.setBoatImageFileName(boatImageService.uploadImage(image));
        return ResponseEntity.ok(boatService.addNewBoat(boatEntity));
    }

    @PutMapping ("/{boatId}")
    public ResponseEntity<Boat> editBoat(@PathVariable("boatId") Long boatId, @RequestBody Boat boat) {
       return ResponseEntity.ok(boatService.updateBoat(boatId, boat));
    }

    @DeleteMapping ("/{boatId}")
    public ResponseEntity<?> deleteBoat(@PathVariable("boatId") Long boatId) {
        boatImageService.deleteImage(boatId);
        boatService.deleteBoat(boatId);
        return ResponseEntity.ok().build();
    }










}
