package com.boatcharter.users;

import com.boatcharter.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collection;
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

    private String permissions;

    @OneToMany
    @JoinColumn (name = "users_id")
    private Set<Reservation> userReservations;

}
