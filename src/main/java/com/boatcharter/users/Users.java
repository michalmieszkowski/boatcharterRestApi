package com.boatcharter.users;

import com.boatcharter.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String login;

    @NotNull
    private String password;

    private String name;

    private String surname;

    @Embedded
    private Address address;

    @Enumerated (EnumType.STRING)
    private UserRole roles;


    @OneToMany (fetch = FetchType.EAGER)
    @JoinColumn (name = "users_id")
    private Set<Reservation> userReservations;

}
