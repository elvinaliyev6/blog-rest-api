package az.company.blog.controller;

import az.company.blog.dto.response.BaseResponse;
import az.company.blog.dto.response.RespStatus;
import az.company.blog.enums.ErrorCodeEnum;
import az.company.blog.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleBaseException(BaseException ex){
        ex.printStackTrace();
        RespStatus status = RespStatus.builder()
                .code(ex.getCode())
                .message(ex.getMessage())
                .build();
        BaseResponse response = BaseResponse.builder()
                .data(null)
                .status(status)
                .build();
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        ex.printStackTrace();
        RespStatus status = RespStatus.builder()
                .code(ErrorCodeEnum.VALIDATION.getCode())
                .message(ErrorCodeEnum.VALIDATION.getMessage())
                .build();
        BaseResponse response = BaseResponse.builder()
                .data(null)
                .status(status).build();
        return response;
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public BaseResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ex.printStackTrace();
        RespStatus status = RespStatus
                .builder()
                .code(ErrorCodeEnum.INPUT.getCode())
                .message(ErrorCodeEnum.INPUT.getMessage())
                .build();
        return BaseResponse.builder()
                .data(null)
                .status(status)
                .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse handleException(Exception ex) {
        ex.printStackTrace();
        RespStatus status = RespStatus.builder()
                .code(ErrorCodeEnum.UNKNOWN.getCode())
                .message(ErrorCodeEnum.UNKNOWN.getMessage())
                .build();
        return BaseResponse.builder()
                .data(null)
                .status(status)
                .build();
    }


}
