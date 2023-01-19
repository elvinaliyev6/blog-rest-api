package az.company.blog.controller;

import az.company.blog.dto.response.BaseResponse;
import az.company.blog.dto.response.RespStatus;
import az.company.blog.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleBaseException(BaseException ex){
        RespStatus status=RespStatus.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();
        BaseResponse response=BaseResponse.builder()
                .data(null)
                .status(status)
                .build();

        return response;
    }


}
