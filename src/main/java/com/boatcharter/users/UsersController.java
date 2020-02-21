package com.boatcharter.users;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping ("/api/v1/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<List<Users>> findAllUsers() {
        return ResponseEntity.ok(usersService.findAllUsers());
    }

    @PostMapping
    public ResponseEntity<Users> addNewUser(@RequestBody  Users user) {
        return ResponseEntity.ok(usersService.addNewUser(user));
    }

    @DeleteMapping ("/{userId}")
    public ResponseEntity<?> deleteUser (@PathVariable("userId") Long userId) {
        usersService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }


}
