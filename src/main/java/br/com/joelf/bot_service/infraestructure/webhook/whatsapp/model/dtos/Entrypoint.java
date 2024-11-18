package br.com.joelf.bot_service.infraestructure.webhook.whatsapp.model.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Entrypoint {
    private String object;
    private List<Entry> entry;
}
