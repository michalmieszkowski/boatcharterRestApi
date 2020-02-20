package com.boatcharter.boat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/v1/boats")
public class BoatController {

    private BoatService boatService;

    @Autowired
    public BoatController (BoatService boatService) {
        this.boatService = boatService;
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



//    @PostMapping (consumes = {"multipart/form-data"})
//    public ResponseEntity<Boat> addNewBoat1(@RequestParam ("boat") String boat, @RequestParam (value = "image", required = false) MultipartFile image) throws IOException {
//        Boat boatEntity = new ObjectMapper().readValue(boat, Boat.class);
//        boatEntity.setBoatImageFileName(boatImageService.uploadImage(image));
//        return ResponseEntity.ok(boatService.addNewBoat(boatEntity));
//    }

    @PostMapping
    public ResponseEntity<Boat> addNewBoat(@RequestBody Boat boat) {
        return ResponseEntity.ok(boatService.addNewBoat(boat));
    }


    @PutMapping ("/{boatId}")
    public ResponseEntity<Boat> editBoat(@PathVariable("boatId") Long boatId, @RequestBody Boat boat) {
       return ResponseEntity.ok(boatService.updateBoat(boatId, boat));
    }

    @DeleteMapping ("/{boatId}")
    public ResponseEntity<?> deleteBoat(@PathVariable("boatId") Long boatId) {
        boatService.deleteBoat(boatId);
        return ResponseEntity.ok().build();
    }










}
