package br.com.joelf.bot_service.domain.usecase.exceptions;

public class DeleteProductUseCaseException extends RuntimeException {
    public DeleteProductUseCaseException(String message) {
        super(message);
    }
}
