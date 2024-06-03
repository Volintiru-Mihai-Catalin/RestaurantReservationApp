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
            unique = false
    )
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(
            name = "table_id",
            referencedColumnName = "table_id",
            foreignKey = @ForeignKey(
                    name = "fk_reservation_table_id",
                    value = ConstraintMode.CONSTRAINT
            )
    )
    private RestaurantTable table;

    @ManyToOne
    @JoinColumn(
            name = "email",
            referencedColumnName = "email",
            foreignKey = @ForeignKey(
                    name = "fk_reservation_client_email",
                    value = ConstraintMode.CONSTRAINT
            )
    )
    private Client client;
}
