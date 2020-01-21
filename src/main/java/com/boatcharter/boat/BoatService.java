package com.boatcharter.boat;

import com.boatcharter.boat.boatImage.BoatImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Boat updateBoat (Long boatId, Boat boat) {
        boatRepository.findById(boatId).ifPresent(updateBoat -> {
            if (boat.getName() != null) {
                updateBoat.setName(boat.getName());
            }
            if (boat.getFuelTank() != null) {
                updateBoat.setFuelTank(boat.getFuelTank());
            }
            if (boat.getWaterTank() != null) {
                updateBoat.setWaterTank(boat.getWaterTank());
            }
            if (boat.getBerths() != null) {
                updateBoat.setBerths(boat.getBerths());
            }
            if (boat.getPrice() != null) {
                updateBoat.setPrice(boat.getPrice());
            }
            boatRepository.save(updateBoat);
        });
        return boatRepository.findById(boatId).orElseThrow(()-> new RuntimeException("Boat with id: " + boatId + " not found"));
    }


}
