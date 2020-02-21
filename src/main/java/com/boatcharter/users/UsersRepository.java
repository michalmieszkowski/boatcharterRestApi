package com.boatcharter.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository <Users, Long> {

    @Override
    List<Users> findAll();

    @Override
     <S extends Users> S save(S s);

    @Override
    Optional<Users> findById(Long aLong);

    @Override
    void deleteById(Long aLong);
}
