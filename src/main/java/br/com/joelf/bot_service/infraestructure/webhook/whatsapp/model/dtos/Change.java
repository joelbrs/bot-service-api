package br.com.joelf.bot_service.infraestructure.webhook.whatsapp.model.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Change {
    private Value value;
    private List<Contact> contacts;
    private List<Message> messages;
}
