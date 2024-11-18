package br.com.joelf.bot_service.infraestructure.webhook.whatsapp.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Change {
    private Value value;
    private String field;
}
