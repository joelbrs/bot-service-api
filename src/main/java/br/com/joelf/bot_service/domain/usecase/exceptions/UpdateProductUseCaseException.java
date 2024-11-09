package br.com.joelf.bot_service.domain.usecase.exceptions;

public class UpdateProductUseCaseException extends RuntimeException {
    public UpdateProductUseCaseException(String message) {
        super(message);
    }
}
