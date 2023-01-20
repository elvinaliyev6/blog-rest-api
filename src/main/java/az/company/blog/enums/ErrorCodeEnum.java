package az.company.blog.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ErrorCodeEnum {

    VALIDATION(410, "Validation error"),
    UNKNOWN(403, "Unknown error"),
    CATEGORY_NOT_FOUND(404, "Category not found"),
    EMPTY(405, "Users are not exist yet"),
    USER_NOT_FOUND(406, "User not found"),
    USERNAME_ALREADY_EXISTS(407, "Username or email already exists!"),
    POST_NOT_FOUND(408,"Post not found!");

    private final int code;
    private final String message;

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }
}
