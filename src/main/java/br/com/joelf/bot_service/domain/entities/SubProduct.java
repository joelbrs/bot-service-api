package br.com.joelf.bot_service.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubProduct<T extends Product> {
    @JsonIgnore
    private UUID id;
    private String name;
    private BigDecimal price;
    @JsonIgnore
    private T product;
}
