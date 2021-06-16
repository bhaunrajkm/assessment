package com.punith.profileservice.exception;

import com.punith.profileservice.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    @ExceptionHandler({NotFoundException.class})
    public final ResponseEntity<ExceptionDto> handleNotFoundException(Exception ex, WebRequest request) {
        ExceptionDto exceptionModel = new ExceptionDto(new Date(), ex.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(exceptionModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({Exception.class})
    public final ResponseEntity<ExceptionDto> handleOtherException(Exception ex, WebRequest request) {
        ExceptionDto exceptionModel = new ExceptionDto(new Date(), ex.getMessage(), request.getDescription(true));
        return new ResponseEntity<>(exceptionModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
