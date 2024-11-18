package br.com.joelf.bot_service.domain.usecase;

public interface SendMessageUseCase {
    void execute(String receivedMessage, String receiverNumber);
}
