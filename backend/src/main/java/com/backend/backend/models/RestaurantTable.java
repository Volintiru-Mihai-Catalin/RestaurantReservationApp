package com.backend.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "RESTAURANT_TABLE")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "table_id")
    private Integer tableId;

    @Column(name = "capacity")
    private Integer capacity;

    @ManyToOne
    @JoinColumn(
            name = "restaurant_id",
            referencedColumnName = "restaurant_id",
            foreignKey = @ForeignKey(
                    name = "fk_table_restaurant_id",
                    value = ConstraintMode.CONSTRAINT
            ),
            unique = false
    )
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(
            name = "waiter_id",
            referencedColumnName = "waiter_id",
            foreignKey = @ForeignKey(
                name = "fk_table_waiter_id",
                value = ConstraintMode.CONSTRAINT
            ),
            unique = false
    )
    private Waiter waiter;
}
