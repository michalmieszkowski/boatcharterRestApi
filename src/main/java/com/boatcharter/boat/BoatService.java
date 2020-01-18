package com.boatcharter.boat;

import com.boatcharter.boat.boatImage.BoatImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoatService {

    private BoatRepository boatRepository;
    private BoatImageService boatImageService;

    @Autowired
    public BoatService (BoatRepository boatRepository, BoatImageService boatImageService) {
        this.boatRepository = boatRepository;
        this.boatImageService = boatImageService;
    }

    public List<Boat> findAllBoats() {
        return boatRepository.findAll();
    }

    public Boat findBoatById(Long boatId) {
        return boatRepository.findById(boatId).orElseThrow(()-> new RuntimeException("Boat with id: " + boatId + " not found"));

//        Map<Boat, MultipartFile> boatMultipartFileMap = new HashMap<>();
//        boatMultipartFileMap.put(boatRepository.findById(boatId)
//                .orElseThrow(() -> new RuntimeException("Boat with id: " + boatId + " not found")), boatImageService.loadImage(boatId));
//        return boatMultipartFileMap;
    }

    public List<Boat> findByCategory (String category) {
        return boatRepository.findAll()
                .stream()
                .filter(boat -> boat.getCategory().toString().equals(category))
                .collect(Collectors.toList());
    }

    public Boat addNewBoat (Boat boat) {
        return boatRepository.save(boat);
    }


}
