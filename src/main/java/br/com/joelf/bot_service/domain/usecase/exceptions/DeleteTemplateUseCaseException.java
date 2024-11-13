package br.com.joelf.bot_service.domain.usecase.exceptions;

import org.springframework.http.HttpStatus;

public class DeleteTemplateUseCaseException extends UseCaseException {
    public DeleteTemplateUseCaseException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
