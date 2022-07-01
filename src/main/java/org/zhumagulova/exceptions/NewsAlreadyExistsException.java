package org.zhumagulova.exceptions;

public class NewsAlreadyExistsException extends Exception{

private final String message = "news_exist";

    public NewsAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
