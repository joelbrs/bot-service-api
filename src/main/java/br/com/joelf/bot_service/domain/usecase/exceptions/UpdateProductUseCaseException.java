package br.com.joelf.bot_service.domain.usecase.exceptions;

import org.springframework.http.HttpStatus;

public class UpdateProductUseCaseException extends UseCaseException {
    public UpdateProductUseCaseException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
