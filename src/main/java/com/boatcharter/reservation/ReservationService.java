package com.boatcharter.reservation;

import com.boatcharter.boat.Boat;
import com.boatcharter.boat.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BoatService boatService;

    @Autowired
    public ReservationService (ReservationRepository reservationRepository, BoatService boatService) {
        this.reservationRepository = reservationRepository;
        this.boatService = boatService;
    }

    public void addNewReservation (Reservation reservation, Long boatId) {
        reservationRepository.save(reservation);
        Boat boat = boatService.findBoatById(boatId);
        boat.getReservations().add(reservation);

        boatService.updateBoat(boatId, boat);
    }
}
