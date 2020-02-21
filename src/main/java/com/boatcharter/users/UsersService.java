package com.boatcharter.users;

import com.boatcharter.exception.EntityNotFound;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }

    public Users addNewUser (Users user) {
        return usersRepository.save(user);
    }

    public void deleteUser (Long userId) {
        usersRepository.deleteById(userId);
    }

    public Users findUserById (Long userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new EntityNotFound(userId));
    }

    public void updateUser (Long userId, Users user) {
        usersRepository.findById(userId).ifPresent(updateUser -> {
            if (user.getUserReservations() != null) {
                updateUser.setUserReservations(user.getUserReservations());
            }
            usersRepository.save(updateUser);
        });
    }


}
