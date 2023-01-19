package az.company.blog.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
public enum ErrorCodeEnum {

    VALIDATION(410,"Validation error"),
    UNKNOWN(403,"Unknown error"),
    CATEGORY_NOT_FOUND(404,"Category not found"),
    EMPTY(405,"Users are not exist yet");

    private final int code;
    private final String message;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
