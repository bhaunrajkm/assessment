package com.punith.profileservice.service;

import com.punith.profileservice.dto.ProfileDto;
import com.punith.profileservice.exception.NotFoundException;

import java.util.Date;
import java.util.List;

public interface ProfileService {
    ProfileDto create(ProfileDto profileDto);

    List<ProfileDto> getProfileByLastName(String lastName) throws NotFoundException;

    List<ProfileDto> getProfileByDOB(Date dob) throws NotFoundException;

    List<ProfileDto> getByLastNameAndDob(String lastName, Date dob) throws NotFoundException;

    List<ProfileDto> getAllProfiles() throws NotFoundException;
}
