package br.com.joelf.bot_service.infraestructure.webhook.whatsapp.model.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Message {
    private String from;
    private String id;
    private String timestamp;
    private Text text;
    private String type;
}
