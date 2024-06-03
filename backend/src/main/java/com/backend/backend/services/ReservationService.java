package com.backend.backend.services;

import com.backend.backend.dto.ReservationGetDTO;
import com.backend.backend.dto.ReservationTableRequestBody;
import com.backend.backend.models.Client;
import com.backend.backend.models.Reservation;
import com.backend.backend.models.Restaurant;
import com.backend.backend.models.RestaurantTable;
import com.backend.backend.repositories.ClientRepository;
import com.backend.backend.repositories.ReservationRepository;
import com.backend.backend.repositories.RestaurantRepository;
import com.backend.backend.repositories.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.time.Duration;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final TableRepository tableRepository;
    private final RestaurantRepository restaurantRepository;

    public ResponseEntity getReservations() {
        List<Reservation> reservationList = reservationRepository.findAll();
        List<ReservationGetDTO> reservationGetDTOList = reservationList.stream()
                .map(reservation -> ReservationGetDTO.builder()
                        .restaurantName(reservation.getRestaurant().getName())
                        .tableId(reservation.getTable().getTableId().toString())
                        .reservationDate(reservation.getReservationDate().toString())
                        .endTime(reservation.getEndTime().toString())
                        .build())
                .toList();
        return new ResponseEntity<>(reservationGetDTOList, HttpStatus.OK);
    }

    public ResponseEntity addReservation(ReservationTableRequestBody reservationTableRequestBody) {
        Optional<Client> optionalClient = clientRepository.findByEmail(reservationTableRequestBody.getEmail());

        if (optionalClient.isEmpty()) {
            return new ResponseEntity<>("Client not found!", HttpStatus.NOT_FOUND);
        }

        Optional<Restaurant> optionalRestaurant = restaurantRepository
                .findByName(reservationTableRequestBody.getRestaurantName());

        if (optionalRestaurant.isEmpty()) {
            return new ResponseEntity<>("Restaurant not found!", HttpStatus.NOT_FOUND);
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(reservationTableRequestBody.getReservationDate());

            List<Integer> busyTableIds = getBusyTables(parsedDate).stream().map(Reservation::getTable).map(RestaurantTable::getTableId).toList();
            List<Integer> tablesIds = tableRepository.findAll().stream().map(RestaurantTable::getTableId).filter(tableId -> !busyTableIds.contains(tableId)).toList();

            if (tablesIds.isEmpty()) {
                return new ResponseEntity<>("No table available!", HttpStatus.NOT_FOUND);
            }

            Optional<RestaurantTable> optionalRestaurantTable = tableRepository.findById(tablesIds.get(new Random().nextInt(tablesIds.size())));

            Reservation reservation = Reservation
                    .builder()
                    .restaurant(optionalRestaurant.get())
                    .client(optionalClient.get())
                    .table(optionalRestaurantTable.get())
                    .reservationDate(parsedDate)
                    .endTime(Date
                            .from(parsedDate
                                    .toInstant()
                                    .plus(Duration
                                            .ofHours(2)
                                    )
                            )
                    )
                    .build();

            return new ResponseEntity<>(reservationRepository.save(reservation), HttpStatus.CREATED);
        } catch(ParseException ignored) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private List<Reservation> getBusyTables(Date reservationDate) {
        List<Reservation> reservationList = reservationRepository.findAll();
        return reservationList.stream().filter(reservation -> {
            boolean isBusy = ((Date
                    .from(reservation
                            .getReservationDate()
                            .toInstant()
                            .minus(Duration.ofMinutes(1)))).before(reservationDate)
                    &&
                    reservationDate.before((Date
                            .from(reservation
                                    .getEndTime()
                                    .toInstant()
                                    .minus(Duration.ofMinutes(1))))))
                    ||
                    ((Date.from(reservationDate
                            .toInstant()
                            .plus(Duration.ofHours(2)))).before(reservation.getEndTime())
                    &&
                    (reservation.getReservationDate().before(Date.from(reservationDate
                            .toInstant()
                            .plus(Duration.ofHours(2)))))
                    );
            return isBusy;
        }).toList();
    }
}
