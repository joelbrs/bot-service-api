package br.com.joelf.bot_service.domain.dtos.product;

import br.com.joelf.bot_service.domain.entities.ProductStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductDto {
    @NotBlank
    private String name;

    @NotNull
    private ProductStatus status;
}
