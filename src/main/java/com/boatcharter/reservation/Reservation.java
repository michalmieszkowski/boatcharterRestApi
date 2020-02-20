package com.boatcharter.reservation;

import com.boatcharter.boat.Boat;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
//    @JsonFormat (shape = JsonFormat.Shape.STRING ,pattern = "yyyy-MM-dd")
    private Date beginOfReservation;

    @Column (name = "end_of_reservation")
//    @JsonFormat (shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date endOfReservation;

}
