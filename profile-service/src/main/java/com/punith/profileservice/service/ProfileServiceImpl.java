package com.punith.profileservice.service;

import com.punith.profileservice.dto.ProfileDto;
import com.punith.profileservice.entity.Profile;
import com.punith.profileservice.exception.NotFoundException;
import com.punith.profileservice.repository.ProfileRepo;
import com.punith.profileservice.service.mapper.ProfileMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    private ProfileRepo profileRepo;

    @Override
    public ProfileDto create(ProfileDto profileDto) {
        Profile profile = new Profile();
        BeanUtils.copyProperties(profileDto, profile);
        profileRepo.save(profile);
        profileDto.setId(profile.getId());
        return profileDto;
    }

    @Override
    public List<ProfileDto> getProfileByLastName(String lastName) throws NotFoundException {
        List<Profile> profiles = profileRepo.findByLastNameIgnoreCase(lastName);
        if (!CollectionUtils.isEmpty(profiles)) {
            return profiles.stream()
                    .map(x -> ProfileMapper.getProfileDto(x))
                    .collect(Collectors.toList());
        } else {
            throw new NotFoundException("No profile exist with the last name=" + lastName);
        }
    }

    @Override
    public List<ProfileDto> getProfileByDOB(Date dob) throws NotFoundException {
        List<Profile> profiles = profileRepo.findByDob(dob);
        if (!CollectionUtils.isEmpty(profiles)) {
            return profiles.stream()
                    .map(x -> ProfileMapper.getProfileDto(x))
                    .collect(Collectors.toList());
        } else {
            throw new NotFoundException("No profile exist with the last dob=" + dob.toString());
        }
    }

    @Override
    public List<ProfileDto> getByLastNameAndDob(String lastName, Date dob) throws NotFoundException {
        List<Profile> profiles = profileRepo.findByLastNameIgnoreCaseAndDob(lastName, dob);
        if (!CollectionUtils.isEmpty(profiles)) {
            return profiles.stream()
                    .map(x -> ProfileMapper.getProfileDto(x))
                    .collect(Collectors.toList());
        } else {
            throw new NotFoundException("No profile exist with the last name=" + lastName + " last dob=" + dob.toString());
        }
    }

    @Override
    public List<ProfileDto> getAllProfiles() throws NotFoundException {
        List<Profile> profiles = profileRepo.findAll();
        if (!CollectionUtils.isEmpty(profiles)) {
            return profiles.stream()
                    .map(x -> ProfileMapper.getProfileDto(x))
                    .collect(Collectors.toList());
        } else {
            throw new NotFoundException("No profile exist");
        }
    }
}
