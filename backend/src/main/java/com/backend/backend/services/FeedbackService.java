package com.backend.backend.services;

import com.backend.backend.models.Feedback;
import com.backend.backend.repositories.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    public ResponseEntity getFeedback() {
        return new ResponseEntity(feedbackRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity postFeedback(Feedback feedback) {
        feedbackRepository.save(feedback);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
