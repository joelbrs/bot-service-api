package br.com.joelf.bot_service.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TemplateStatus {
    ATIVO("ATIVO"),
    INATIVO("INATIVO");

    private final String status;
}
