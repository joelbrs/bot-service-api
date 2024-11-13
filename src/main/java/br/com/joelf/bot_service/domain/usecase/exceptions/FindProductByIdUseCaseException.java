package br.com.joelf.bot_service.domain.usecase.exceptions;

import org.springframework.http.HttpStatus;

public class FindProductByIdUseCaseException extends UseCaseException {
        public FindProductByIdUseCaseException(String message, HttpStatus httpStatus) {
            super(message, httpStatus);
        }
}
