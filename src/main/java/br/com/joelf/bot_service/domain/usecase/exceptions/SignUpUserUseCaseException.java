package br.com.joelf.bot_service.domain.usecase.exceptions;

import org.springframework.http.HttpStatus;

public class SignUpUserUseCaseException extends UseCaseException {
    public SignUpUserUseCaseException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
