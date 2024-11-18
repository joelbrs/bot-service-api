package br.com.joelf.bot_service.infraestructure.repositories.clients.meta.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class SendMessageResponse {

    @JsonProperty("messaging_product")
    private String messagingProduct;

    @JsonProperty("contacts")
    private List<ContactResponse> contacts;

    @JsonProperty("messages")
    private List<MessageResponse> messages;
}
