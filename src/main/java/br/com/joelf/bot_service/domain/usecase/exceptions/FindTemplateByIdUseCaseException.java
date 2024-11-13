package br.com.joelf.bot_service.domain.usecase.exceptions;

import org.springframework.http.HttpStatus;

public class FindTemplateByIdUseCaseException extends UseCaseException {
    public FindTemplateByIdUseCaseException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }
}
