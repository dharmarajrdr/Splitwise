package com.dharmaraj.splitwise.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.dharmaraj.splitwise.dtos.ResponseDto;
import com.dharmaraj.splitwise.dtos.ResponseStatus;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseDto getResponseDto() {
        
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponseStatus(ResponseStatus.FAILURE);
        return responseDto;
    }
    
    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ResponseDto> handleUserNotFound(UserNotFound e) {

        ResponseDto responseDto = getResponseDto();
        responseDto.setError(e.getMessage());
        return ResponseEntity.status(404).body(responseDto);
    }
}
