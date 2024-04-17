package com.backend.backend.services;

import com.backend.backend.models.Waiter;
import com.backend.backend.repositories.WaiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WaiterService {
    private final WaiterRepository waiterRepository;

    public ResponseEntity<List<Waiter>> getWaiters() {
        return new ResponseEntity<>(waiterRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Waiter> addWaiter(Waiter waiter) {
        Optional<Waiter> optionalWaiter = waiterRepository.findByFirstNameAndAndLastName(waiter.getFirstName(), waiter.getLastName());
        if (optionalWaiter.isPresent()) {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(waiterRepository.save(waiter), HttpStatus.CREATED);
    }

    public ResponseEntity<Waiter> updateWaiter(Waiter waiter, Integer id) {
        Optional<Waiter> optionalWaiter = waiterRepository.findById(id);

        if (optionalWaiter.isEmpty()) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Waiter waiterToUpdate = optionalWaiter.get();

        Optional<Waiter> waiterOptional = waiterRepository
                .findByFirstNameAndAndLastName(waiter.getFirstName(), waiter.getLastName());

        if (waiterOptional.isPresent()) {
            if (!waiterToUpdate.getWaiterId().equals(waiterOptional.get().getWaiterId())) {
                return new ResponseEntity<>(null, HttpStatus.CONFLICT);
            }
        }

        waiterToUpdate.setFirstName(waiter.getFirstName());
        waiterToUpdate.setLastName(waiter.getLastName());

        return new ResponseEntity<>(waiterRepository.save(waiterToUpdate), HttpStatus.OK);
    }

    public ResponseEntity<String> deleteWaiter(Integer id) {
        Optional<Waiter> optionalWaiter = waiterRepository.findById(id);

        if (optionalWaiter.isEmpty()) {
            return new ResponseEntity<>("Waiter not found", HttpStatus.NOT_FOUND);
        }

        waiterRepository.delete(optionalWaiter.get());
        return new ResponseEntity<>("Waiter deleted", HttpStatus.OK);
    }
}
