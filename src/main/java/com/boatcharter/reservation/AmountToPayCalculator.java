package com.boatcharter.reservation;

import com.boatcharter.boat.Boat;
import com.boatcharter.boat.BoatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;

@Service
public class AmountToPayCalculator {

    private final BoatService boatService;

    @Autowired
    public AmountToPayCalculator(BoatService boatService) {
        this.boatService = boatService;
    }

    public BigDecimal calculateAmountToPay (Reservation reservation, Long boatId) {
        BigDecimal pricePerDay = boatService.findBoatById(boatId).getPrice();
        long reservationDays = ChronoUnit.DAYS.between(reservation.getBeginOfReservation(), reservation.getEndOfReservation());
        return pricePerDay.multiply(BigDecimal.valueOf(reservationDays));
    }
}
