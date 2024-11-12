package br.com.joelf.bot_service.domain.usecase.exceptions;

public class SignUpUserUseCaseException extends RuntimeException {
    public SignUpUserUseCaseException(String message) {
        super(message);
    }
}
