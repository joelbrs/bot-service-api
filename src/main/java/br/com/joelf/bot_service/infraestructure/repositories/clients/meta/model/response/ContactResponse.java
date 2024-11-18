package br.com.joelf.bot_service.infraestructure.repositories.clients.meta.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ContactResponse {

    @JsonProperty("input")
    private String input;

    @JsonProperty("wa_id")
    private String waId;
}
