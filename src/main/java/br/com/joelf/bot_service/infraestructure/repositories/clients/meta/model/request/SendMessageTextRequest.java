package br.com.joelf.bot_service.infraestructure.repositories.clients.meta.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class SendMessageTextRequest {

    @JsonProperty("preview_url")
    private Boolean previewUrl;
    private String body;
}
