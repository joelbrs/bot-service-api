package br.com.joelf.bot_service.domain.usecase.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class UseCaseException extends RuntimeException {

    private final HttpStatus httpStatus;

    public UseCaseException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
