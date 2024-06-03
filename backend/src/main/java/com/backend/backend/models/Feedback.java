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
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "feedback_id")
    private Integer feedbackId;

    @Lob
    @Column(name = "feedback_text")
    private String feedbackText;

    @Column(name = "favourite_category")
    private String favouriteCategory;

    @Column(name = "subscribed")
    private Boolean subscribed;

    @Column(name = "rating")
    private Integer rating;
}
