package com.djap.shopeeclone.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class EmailPasswordNotMatchAdvice {
    @ResponseBody
    @ExceptionHandler(EmailPasswordNotMatchException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String emailPasswordNotMatchHandler(EmailPasswordNotMatchException ex) throws JsonProcessingException {
        ObjectMapper objectMapper= new ObjectMapper();

        ObjectNode jsonNodes = objectMapper.createObjectNode();
        jsonNodes.put("message", ex.getMessage());
        jsonNodes.put("type", "authentication exception");

        ObjectNode jsonNodes1 = objectMapper.createObjectNode();
        jsonNodes1.put ("error", jsonNodes.toString());

        return objectMapper.writeValueAsString(jsonNodes1);
    }
}
