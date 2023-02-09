package az.company.blog.exception;

import az.company.blog.enums.ErrorCodeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BaseException extends RuntimeException{

    private int code;
    private String message;

    public BaseException(ErrorCodeEnum errorCodeEnum){
        super(errorCodeEnum.getMessage());
        this.code=errorCodeEnum.getCode();
        this.message= errorCodeEnum.getMessage();
    }

}
