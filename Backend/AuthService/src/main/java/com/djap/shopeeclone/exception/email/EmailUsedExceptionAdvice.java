package com.djap.shopeeclone.exception.email;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmailUsedExceptionAdvice {
    @ResponseBody
    @ExceptionHandler(EmailUsedException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String EmailUsedExceptionHandler(EmailUsedException ex) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("message", ex.getMessage());

        return mapper.writeValueAsString(node);
    }
}
