package org.zhumagulova.exceptions;

public class NewsAlreadyExistException extends Exception {

    private final String message = "news_exist";

    public NewsAlreadyExistException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public NewsAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
