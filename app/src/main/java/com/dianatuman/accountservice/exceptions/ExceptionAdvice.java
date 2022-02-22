package com.dianatuman.accountservice.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Void> handleDefaultError(HttpServletRequest req, Exception e) {
        logger.error("Unexpected error handling request on {} {}", req.getMethod(), req.getContextPath(), e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Void> handleIllegalArgumentException(HttpServletRequest req, Exception e) {
        logger.error("Unexpected error handling request on {} {}", req.getMethod(), req.getContextPath(), e);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
