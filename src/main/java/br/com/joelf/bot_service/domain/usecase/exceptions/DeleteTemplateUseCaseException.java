package br.com.joelf.bot_service.domain.usecase.exceptions;

public class DeleteTemplateUseCaseException extends RuntimeException {
    public DeleteTemplateUseCaseException(String message) {
        super(message);
    }
}
