package com.backend.backend.services;

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
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final TableRepository tableRepository;
    private final RestaurantRepository restaurantRepository;

    public ResponseEntity<List<Reservation>> getReservations() {
        return new ResponseEntity<>(reservationRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Reservation> addReservation(ReservationTableRequestBody reservationTableRequestBody) {
        Optional<Client> optionalClient = clientRepository.findByEmail(reservationTableRequestBody.getEmail());

        if (optionalClient.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Optional<Restaurant> optionalRestaurant = restaurantRepository
                .findByName(reservationTableRequestBody.getRestaurantName());

        if (optionalRestaurant.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        List<Integer> tableIds = tableRepository.findAll().stream().map(RestaurantTable::getTableId).toList();
        Optional<RestaurantTable> optionalRestaurantTable = tableRepository.findById(tableIds.get(new Random().nextInt(tableIds.size())));

        if (optionalRestaurantTable.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date parsedDate = dateFormat.parse(reservationTableRequestBody.getReservationDate());

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

    public ResponseEntity<String> deleteReservation(Integer id) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);

        if (optionalReservation.isEmpty()) {
            return new ResponseEntity<>("Reservation not found", HttpStatus.NOT_FOUND);
        }

        reservationRepository.delete(optionalReservation.get());
        return new ResponseEntity<>("Reservation deleted", HttpStatus.OK);
    }
}
