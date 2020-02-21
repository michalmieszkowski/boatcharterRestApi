package com.boatcharter.reservation;

import com.boatcharter.boat.Boat;
import com.boatcharter.boat.BoatService;
import com.boatcharter.users.Users;
import com.boatcharter.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final BoatService boatService;
    private final AvaibilityCheckIn avaibilityCheckIn;
    private final UsersService usersService;

    @Autowired
    public ReservationService (ReservationRepository reservationRepository, BoatService boatService, AvaibilityCheckIn avaibilityCheckIn, UsersService usersService) {
        this.reservationRepository = reservationRepository;
        this.boatService = boatService;
        this.avaibilityCheckIn = avaibilityCheckIn;
        this.usersService = usersService;
    }

    public Reservation addNewReservation (Reservation reservation, Long boatId, Long userId) {
        Boat boat = boatService.findBoatById(boatId);
        Users user = usersService.findUserById(userId);
        if (avaibilityCheckIn.checkAvaibility(reservation, boatId)) {
            reservationRepository.save(reservation);
            boat.getReservations().add(reservation);
            boatService.updateBoat(boatId, boat);
            user.getUserReservations().add(reservation);
            usersService.updateUser(userId, user);
            return reservation;
        } else {
            return null;
            //TODO implements user message class , amount calculator class
        }

    }


}
