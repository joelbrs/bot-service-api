package br.com.joelf.bot_service.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductStatus {
    DISPONIVEL("DISPONIVEL"),
    INDISPONIVEL("INDISPONIVEL");

    private final String status;
}
