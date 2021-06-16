package com.punith.profileservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
public class ExceptionDto {
    private Date timeStamp;
    private String message;
    private String details;
}
