package com.boatcharter.reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository <Reservation, Long> {

    @Override
    List<Reservation> findAll();

    @Override
    <S extends Reservation> S save(S s);

    @Override
    void deleteById(Long aLong);
}
