package br.com.joelf.bot_service.infraestructure.repositories.clients.meta.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class SendMessageRequest {

    @JsonProperty("messaging_product")
    private String messagingProduct;

    @JsonProperty("recipient_type")
    private String recipientType;

    @JsonProperty("to")
    private String to;

    @JsonProperty("type")
    private String type;

    @JsonProperty("text")
    private SendMessageTextRequest text;
}
