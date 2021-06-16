package com.punith.analytics.service.service;

import com.punith.analytics.service.dto.FilterByResponse;
import com.punith.analytics.service.dto.ProfileDto;

import java.util.Date;
import java.util.List;

public interface AnalyticsService {
    FilterByResponse getFilterByLastName(String lastName);

    FilterByResponse getFilterByDob(Date dob);

    List<ProfileDto> getByLastNameAndDob(String lastName, Date dob);

    List<String> getTop10LastNames();
}
