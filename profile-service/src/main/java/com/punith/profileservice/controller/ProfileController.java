package com.punith.profileservice.controller;

import com.punith.profileservice.dto.ProfileDto;
import com.punith.profileservice.exception.NotFoundException;
import com.punith.profileservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody ProfileDto profileDto) {
        return ResponseEntity
                .created(null)
                .body(profileService.create(profileDto));
    }

    @GetMapping
    public ResponseEntity<?> getProfiles() throws NotFoundException {
        return ResponseEntity
                .ok()
                .body(profileService.getAllProfiles());
    }

    @GetMapping("/{lastName}")
    public ResponseEntity<?> getProfiles(@PathVariable String lastName) throws NotFoundException {
        return ResponseEntity
                .ok()
                .body(profileService.getProfileByLastName(lastName));
    }

    @GetMapping("/dob/{dob}")
    public ResponseEntity<?> getProfiles(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob) throws NotFoundException {
        return ResponseEntity
                .ok()
                .body(profileService.getProfileByDOB(dob));
    }

    @GetMapping("/{lastName}/{dob}")
    public ResponseEntity<?> getProfiles(@PathVariable String lastName,
                                         @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dob) throws NotFoundException {
        return ResponseEntity
                .ok()
                .body(profileService.getByLastNameAndDob(lastName, dob));
    }
}
