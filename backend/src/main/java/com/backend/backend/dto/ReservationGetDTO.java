package com.backend.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationGetDTO {
    private String reservationDate;
    private String endTime;
    private String restaurantName;
    private String tableId;

}
