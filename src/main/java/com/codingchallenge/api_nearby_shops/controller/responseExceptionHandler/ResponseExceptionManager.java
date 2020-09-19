package com.codingchallenge.api_nearby_shops.controller.responseExceptionHandler;

import com.codingchallenge.api_nearby_shops.Exception.UserAlreadyExistException;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ResponseExceptionManager extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { UserAlreadyExistException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    protected ObjectNode handleRuntimeException(UserAlreadyExistException exception, WebRequest webRequest){
        ObjectNode responseMessage = JsonNodeFactory.instance.objectNode();
        responseMessage.put("exceptionMessage",exception.getMessage());
        return responseMessage;
    }
}
