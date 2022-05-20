package org.skarpushin.currencyconverter.errorhandler;

public class BadRequestException extends RuntimeException {

    public BadRequestException(String s) {
        super(s);
    }

    public BadRequestException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
