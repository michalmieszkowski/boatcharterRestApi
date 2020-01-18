package com.boatcharter.reservation;

import com.boatcharter.boat.Boat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Table (name = "reservation")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "begin_of_reservation")
    private Date beginOfReservation;

    @Column (name = "end_of_reservation")
    private Date endOfReservation;

}
