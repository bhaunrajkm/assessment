package com.punith.analytics.service.service;

import com.punith.analytics.service.dto.FilterByResponse;
import com.punith.analytics.service.dto.ProfileDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {
    @Value("${profile-service.end.point}")
    private String profileServiceEndPoint;

    public FilterByResponse getFilterByLastName(String lastName) {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<ProfileDto[]> response =
                restTemplate.getForEntity(profileServiceEndPoint + "/" + lastName,
                        ProfileDto[].class);

        ProfileDto[] profileDtos = response.getBody();

        Map<String, List<ProfileDto>> map = Arrays.stream(profileDtos)
                .collect(Collectors.groupingBy(ProfileDto::getCity));

        FilterByResponse filterByResponse = new FilterByResponse();
        filterByResponse.setName("LastName");
        filterByResponse.setValue(lastName);
        Map<String, Integer> cityAndCountMap = new HashMap<>();

        for (Map.Entry<String, List<ProfileDto>> entry : map.entrySet()) {
            cityAndCountMap.put(entry.getKey(), entry.getValue().size());
        }
        filterByResponse.setCityAndCountMap(cityAndCountMap);

        return filterByResponse;
    }

    public FilterByResponse getFilterByDob(Date dob) {
        RestTemplate restTemplate = new RestTemplate();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(dob);

        ResponseEntity<ProfileDto[]> response =
                restTemplate.getForEntity(profileServiceEndPoint + "/dob/" + date,
                        ProfileDto[].class);

        Map<String, List<ProfileDto>> map = Arrays.stream(response.getBody())
                .collect(Collectors.groupingBy(ProfileDto::getCity));

        FilterByResponse filterByResponse = new FilterByResponse();
        filterByResponse.setName("DOB");
        filterByResponse.setValue(dob.toString());
        Map<String, Integer> cityAndCountMap = new HashMap<>();

        for (Map.Entry<String, List<ProfileDto>> entry : map.entrySet()) {
            cityAndCountMap.put(entry.getKey(), entry.getValue().size());
        }
        filterByResponse.setCityAndCountMap(cityAndCountMap);

        return filterByResponse;
    }

    @Override
    public List<ProfileDto> getByLastNameAndDob(String lastName, Date dob) {
        RestTemplate restTemplate = new RestTemplate();
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(dob);

        ResponseEntity<ProfileDto[]> response =
                restTemplate.getForEntity(profileServiceEndPoint + "/" + lastName + "/" + date,
                        ProfileDto[].class);

        return Arrays.stream(response.getBody())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getTop10LastNames() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ProfileDto[]> response = restTemplate.getForEntity(profileServiceEndPoint,
                        ProfileDto[].class);

        Map<String, List<ProfileDto>> map = Arrays.stream(response.getBody())
                .collect(Collectors.groupingBy(ProfileDto::getLastName));

        Map<String, Integer> cityAndCountMap = new HashMap<>();

        for (Map.Entry<String, List<ProfileDto>> entry : map.entrySet()) {
            cityAndCountMap.put(entry.getKey(), entry.getValue().size());
        }

        List<String> top10Names = new ArrayList<>();
        cityAndCountMap.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(10)
                .forEach( x -> top10Names.add(x.getKey()));

        return top10Names;
    }

}
