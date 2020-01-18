package com.boatcharter.boat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoatRepository extends JpaRepository <Boat, Long> {

    @Override
    List<Boat> findAll();

    @Override
    Optional<Boat> findById(Long boatId);

    @Override
    <S extends Boat> S save(S s);

    @Override
    void deleteById(Long aLong);
}
