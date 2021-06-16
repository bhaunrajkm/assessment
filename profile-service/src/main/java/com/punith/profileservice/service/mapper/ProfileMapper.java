package com.punith.profileservice.service.mapper;

import com.punith.profileservice.dto.ProfileDto;
import com.punith.profileservice.entity.Profile;
import org.springframework.beans.BeanUtils;

public class ProfileMapper {
    public static ProfileDto getProfileDto(Profile profile) {
        ProfileDto profileDto = new ProfileDto();
        BeanUtils.copyProperties(profile, profileDto);
        return profileDto;
    }
}
