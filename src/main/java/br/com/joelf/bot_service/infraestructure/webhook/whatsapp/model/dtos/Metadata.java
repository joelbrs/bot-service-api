package br.com.joelf.bot_service.infraestructure.webhook.whatsapp.model.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Metadata {

    @JsonProperty("display_phone_number")
    private String displayPhoneNumber;

    @JsonProperty("phone_number_id")
    private String phoneNumberId;
}
