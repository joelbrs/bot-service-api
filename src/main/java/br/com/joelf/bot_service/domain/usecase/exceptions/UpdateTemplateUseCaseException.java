package br.com.joelf.bot_service.domain.usecase.exceptions;

import org.springframework.http.HttpStatus;

public class UpdateTemplateUseCaseException extends UseCaseException {
    public UpdateTemplateUseCaseException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
