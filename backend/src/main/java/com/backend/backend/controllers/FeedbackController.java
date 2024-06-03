package com.backend.backend.controllers;

import com.backend.backend.models.Feedback;
import com.backend.backend.services.FeedbackService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
@SecurityRequirement(name = "bearerAuth")
public class FeedbackController {
    private final FeedbackService feedbackService;

    @GetMapping("/feedback")
    public ResponseEntity getFeedback() {
        return feedbackService.getFeedback();
    }

    @PostMapping("/feedback")
    public ResponseEntity postFeedback(@RequestBody Feedback feedback) {
        return feedbackService.postFeedback(feedback);
    }
}
