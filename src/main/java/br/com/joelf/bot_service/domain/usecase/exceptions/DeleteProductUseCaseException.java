package br.com.joelf.bot_service.domain.usecase.exceptions;

import org.springframework.http.HttpStatus;

public class DeleteProductUseCaseException extends UseCaseException {
    public DeleteProductUseCaseException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
