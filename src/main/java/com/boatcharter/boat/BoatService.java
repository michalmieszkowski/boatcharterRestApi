package com.boatcharter.boat;

import com.boatcharter.boatImage.BoatImageService;
import com.boatcharter.exception.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BoatService {

    private final BoatRepository boatRepository;
    private final BoatImageService boatImageService;



    @Autowired
    public BoatService (BoatRepository boatRepository, BoatImageService boatImageService) {
        this.boatRepository = boatRepository;
        this.boatImageService = boatImageService;
    }

    public List<Boat> findAllBoats() {
        return boatRepository.findAll();
    }

    public Boat findBoatById(Long boatId) {
        return boatRepository.findById(boatId).orElseThrow(()-> new EntityNotFound(boatId));
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
        return boatRepository.findById(boatId).orElseThrow(()-> new EntityNotFound(boatId));
    }

    public void deleteBoat(Long boatId) {
        boatImageService.deleteImage(boatId);
        boatRepository.deleteById(boatId);
    }

    public Boat saveBoatImageName (Long boatId, String boatImageName) {
        boatRepository.findById(boatId).ifPresent(saveBoat -> {
            if (boatImageName != null) {
                saveBoat.setBoatImageFileName(boatImageName);
            }
        });
        return boatRepository.findById(boatId).orElseThrow(()-> new EntityNotFound(boatId));
    }


}
