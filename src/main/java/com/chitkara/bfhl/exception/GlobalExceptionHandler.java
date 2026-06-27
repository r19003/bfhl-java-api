package com.chitkara.bfhl.exception;

import com.chitkara.bfhl.dto.BfhlResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BfhlResponse> invalidInput(MethodArgumentNotValidException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(failResponse());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BfhlResponse> someError(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(failResponse());
    }

    private BfhlResponse failResponse() {
        BfhlResponse res = new BfhlResponse();
        res.setSuccess(false);
        res.setUserId("");
        res.setEmail("");
        res.setRollNumber("");
        res.setOddNumbers(new ArrayList<>());
        res.setEvenNumbers(new ArrayList<>());
        res.setAlphabets(new ArrayList<>());
        res.setSpecialCharacters(new ArrayList<>());
        res.setSum("0");
        res.setConcatString("");
        return res;
    }
}
