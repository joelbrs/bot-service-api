package br.com.joelf.bot_service.infraestructure.webhook.whatsapp.model.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Contact {
    private Profile profile;

    @JsonProperty("wa_id")
    private String waId;
}
