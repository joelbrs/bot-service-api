package br.com.joelf.bot_service.infraestructure.webhook.whatsapp.model.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Value {

    @JsonProperty("messaging_product")
    private String messagingProduct;
    private Metadata metadata;
}
