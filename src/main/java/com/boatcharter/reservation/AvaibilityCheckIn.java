package com.boatcharter.reservation;

import com.boatcharter.boat.Boat;
import com.boatcharter.boat.BoatRepository;
import com.boatcharter.exception.EntityNotFound;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AvaibilityCheckIn {

    private final BoatRepository boatRepository;

    public AvaibilityCheckIn(BoatRepository boatRepository) {
        this.boatRepository = boatRepository;
    }

    public boolean checkAvaibility (Reservation newReservation, Long boatId) {
        boolean avaibility = true;
        Boat boat = boatRepository.findById(boatId).orElseThrow(()-> new EntityNotFound(boatId));
        Set<Reservation> boatReservations= boat.getReservations();
        for (Reservation currentReservation: boatReservations) {
            if (currentReservation.getEndOfReservation().after(newReservation.getBeginOfReservation()))
                avaibility = false;
        }
        return avaibility;
    }
}
