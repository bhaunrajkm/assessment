package com.punith.analytics.service.controller;

import com.punith.analytics.service.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {
    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/byLastName/{lastName}")
    public ResponseEntity<?> getByLastName(@PathVariable String lastName) {
        return ResponseEntity
                .ok()
                .body(analyticsService.getFilterByLastName(lastName));
    }

    @GetMapping("/byDob/{dob}")
    public ResponseEntity<?> getByLastName(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob) {
        return ResponseEntity
                .ok()
                .body(analyticsService.getFilterByDob(dob));
    }

    @GetMapping("/{lastName}/{dob}")
    public ResponseEntity<?> getProfiles(@PathVariable String lastName,
                                         @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob) {
        return ResponseEntity
                .ok()
                .body(analyticsService.getByLastNameAndDob(lastName, dob));
    }

    @GetMapping("/top10Names")
    public ResponseEntity<?> getTop10Names() {
        return ResponseEntity
                .ok()
                .body(analyticsService.getTop10LastNames());
    }
}
