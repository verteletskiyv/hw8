package ua.profitsoft.hw8.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ua.profitsoft.hw8.dto.RestResponse;
import ua.profitsoft.hw8.exception.EmptyFileException;
import ua.profitsoft.hw8.exception.InvalidFileTypeException;
import ua.profitsoft.hw8.exception.InvalidJsonException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private RestResponse handleException(InvalidJsonException ignoredE) {
        return new RestResponse("File reading failure: Invalid JSON");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private RestResponse handleException(InvalidFileTypeException ignoredE) {
        return new RestResponse("File reading failure: Acceptable types: .zip, .json");
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private RestResponse handleException(EmptyFileException e) {
        return new RestResponse("File reading failure: Unable to locate");
    }
}
