package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.domain.usecase.MountMessageUseCase;
import br.com.joelf.bot_service.domain.usecase.SendMessageUseCase;
import br.com.joelf.bot_service.infraestructure.repositories.clients.meta.MetaFeignClient;
import br.com.joelf.bot_service.infraestructure.repositories.clients.meta.model.request.SendMessageRequest;
import br.com.joelf.bot_service.infraestructure.repositories.clients.meta.model.request.SendMessageTextRequest;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SendMessageUseCaseImpl implements SendMessageUseCase {

    private final String version;
    private final String phoneNumberId;
    private final MetaFeignClient metaFeignClient;
    private final MountMessageUseCase mountMessageUseCase;

    @Override
    public void execute(String receivedMessage, String receiverNumber) {
        String response = mountMessageUseCase.execute(receivedMessage);

        SendMessageRequest request = mountRequest(response, receiverNumber);
        metaFeignClient.postSendMessage(
                request,
                version,
                phoneNumberId
        );
    }

    private SendMessageRequest mountRequest(String response, String recipientPhoneNumber) {
        String messagingProduct = "whatsapp";
        String recipientType = "individual";
        String type = "text";

        SendMessageTextRequest text = SendMessageTextRequest.builder()
                .body(response)
                .previewUrl(Boolean.FALSE)
                .build();

        return SendMessageRequest.builder()
                .messagingProduct(messagingProduct)
                .recipientType(recipientType)
                .to(recipientPhoneNumber)
                .text(text)
                .type(type)
                .build();
    }
}
