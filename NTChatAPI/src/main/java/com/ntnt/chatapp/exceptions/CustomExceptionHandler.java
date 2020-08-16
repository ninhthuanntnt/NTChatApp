package com.ntnt.chatapp.exceptions;

import com.ntnt.chatapp.models.responses.MessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public MessageResponse handleValidationException(
            ConstraintViolationException e
    ) {
        Set<ConstraintViolation<?>> exceptions = e.getConstraintViolations();
        StringBuilder userMessage = new StringBuilder();
        StringBuilder internalMessage = new StringBuilder();

        exceptions.forEach(constraintViolation -> {
            userMessage.append(constraintViolation.getMessage()).append("\n");
            internalMessage.append(String.format("Property Path: %s, Message template: %s",
                                                constraintViolation.getPropertyPath(),
                                                constraintViolation.getMessageTemplate())).append("\n");
        });
        userMessage.replace(userMessage.lastIndexOf("\n"),
                userMessage.length(),
                "");
        internalMessage.replace(internalMessage.lastIndexOf("\n"),
                internalMessage.length(),
                "");
        return new MessageResponse(userMessage.toString(),
                internalMessage.toString(),
                HttpStatus.NOT_ACCEPTABLE.value());
    }
}
