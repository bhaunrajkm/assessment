package com.punith.analytics.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class FilterByResponse {
    private String name;
    private String value;
    private Map<String, Integer> cityAndCountMap;
}
