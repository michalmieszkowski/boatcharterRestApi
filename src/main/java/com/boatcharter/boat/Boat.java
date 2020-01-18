package com.boatcharter.boat;

import com.boatcharter.reservation.Reservation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table (name = "boat")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Boat {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column (name = "boat_length")
    private Float boatLength;

    @Column (name = "boat_width")
    private Float boatWidth;

    private Float submergence;

    @Column (name = "water_tank")
    private Integer waterTank;

    @Column (name = "fuel_tank")
    private Integer fuelTank;

    private Integer cabins;

    private Integer berths;

    private Integer toilets;

    private BigDecimal price;

    @Enumerated (EnumType.STRING)
    private Category category;

    @OneToMany
    @JoinColumn (name = "boat_id")
    private Set<Reservation> reservations;

    @Column (name = "boat_image_name")
    private String boatImageFileName;

}
