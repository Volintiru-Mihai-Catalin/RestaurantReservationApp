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
@Entity
@Table(name = "_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(
            name = "product_id",
            referencedColumnName = "product_id",
            foreignKey = @ForeignKey(
                    name = "fk_order_product_id",
                    value = ConstraintMode.CONSTRAINT
            )
    )
    private Product product;

    @ManyToOne
    @JoinColumn(
            name = "table_id",
            referencedColumnName = "table_id",
            foreignKey = @ForeignKey(
                    name = "fk_order_table_id",
                    value = ConstraintMode.CONSTRAINT
            )
    )
    private RestaurantTable restaurantTable;
}
