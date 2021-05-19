package com.mydigieyes.carzzzat.handler;

import com.mydigieyes.carzzzat.enums.ResponseCode;
import com.mydigieyes.carzzzat.exception.OfferException;
import com.mydigieyes.carzzzat.general.GeneralResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<GeneralResponse> exception(Exception exception) {
        log.error("Error during request.", exception);
        GeneralResponse response = new GeneralResponse(ResponseCode.TECHNICAL_ERROR, "Something wrong happened");
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = OfferException.class)
    public ResponseEntity<GeneralResponse> offerException(OfferException exception) {
        log.error("Error during request.", exception);
        GeneralResponse response = new GeneralResponse(exception.getResponseCode(), exception.getMessage());
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeneralResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("error in the request MethodArgumentNotValidException ", e);
        FieldError fieldError = e.getBindingResult().getFieldError();
        String field = fieldError.getField();
        GeneralResponse response = new GeneralResponse(ResponseCode.TECHNICAL_ERROR, ("Error in " + field + " : " + fieldError.getDefaultMessage()));
        return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
