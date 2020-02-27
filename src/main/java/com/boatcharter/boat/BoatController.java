package com.boatcharter.boat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        List<Boat> boatList;
        if (category.equals("motorboat")) {
            boatList = boatService.findByCategory("MOTORBOAT");
            if (boatList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else
                return ResponseEntity.ok(boatList);
        } else if (category.equals("yacht")) {
            boatList = boatService.findByCategory("YACHT");
            if (boatList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else
                return ResponseEntity.ok(boatList);
        } else if (category.equals("catamaran")) {
            boatList = boatService.findByCategory("CATAMARAN");
            if (boatList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else
                return ResponseEntity.ok(boatList);
        } else
        boatList = boatService.findAllBoats();
        if (boatList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else
        return ResponseEntity.ok(boatList);
    }

    @GetMapping ("/{boatId}")
    public ResponseEntity<Boat> findBoatById(@PathVariable("boatId") Long boatId)  {
        return ResponseEntity.ok(boatService.findBoatById(boatId));
    }

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
