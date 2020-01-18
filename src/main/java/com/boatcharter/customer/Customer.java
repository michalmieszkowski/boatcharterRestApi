package com.boatcharter.customer;

import com.boatcharter.reservation.Reservation;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Table (name = "customer")
@Entity
public class Customer {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    @NotNull
    @UniqueElements
    private String login;

    @NotNull
    private String password;

    @OneToMany
    @JoinColumn (name = "customer_id")
    private Set<Reservation> customerReservations;

}
