package com.boatcharter.users;

import com.boatcharter.exception.EntityNotFound;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsersService {

    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UsersService(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Users> findAllUsers() {
        return usersRepository.findAll();
    }

    public Users addNewUser (Users user) {
        String primitivePassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(primitivePassword));
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
