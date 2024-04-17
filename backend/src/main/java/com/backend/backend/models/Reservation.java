package com.backend.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservation_id")
    private Integer reservationId;

    @Column(name = "reservation_date")
    private Date reservationDate;

    @Column(name = "end_time")
    private Date endTime;

    @ManyToOne
    @JoinColumn(
            name = "restaurant_id",
            referencedColumnName = "restaurant_id",
            foreignKey = @ForeignKey(
                    name = "fk_reservation_restaurant_id",
                    value = ConstraintMode.CONSTRAINT
            ),
            unique = true
    )
    private Restaurant restaurant;

    @OneToOne
    @JoinColumn(
            name = "table_id",
            referencedColumnName = "table_id",
            foreignKey = @ForeignKey(
                    name = "fk_reservation_table_id",
                    value = ConstraintMode.CONSTRAINT
            ),
            unique = true
    )
    private RestaurantTable table;

    @OneToOne
    @JoinColumn(
            name = "email",
            referencedColumnName = "email",
            foreignKey = @ForeignKey(
                    name = "fk_reservation_client_email",
                    value = ConstraintMode.CONSTRAINT
            ),
            unique = true
    )
    private Client client;
}
