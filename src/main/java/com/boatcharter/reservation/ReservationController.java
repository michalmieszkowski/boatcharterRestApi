package com.boatcharter.reservation;

import com.boatcharter.boat.Boat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("api/v1/reservation")
public class ReservationController {


    private final ReservationService reservationService;

    @Autowired
    public ReservationController (ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @PostMapping ("/boats/{boatId}/users/{userId}")
    public Reservation addNewReservation (@RequestBody Reservation reservation, @PathVariable ("boatId") Long boatId, @PathVariable ("userId") Long userId) {
        //return ResponseEntity.ok(reservationService.addNewReservation(reservation, boatId));
         return reservationService.addNewReservation(reservation, boatId, userId);
    }


}
