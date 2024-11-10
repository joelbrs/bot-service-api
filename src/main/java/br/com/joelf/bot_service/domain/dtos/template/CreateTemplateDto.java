package br.com.joelf.bot_service.domain.dtos.template;

import br.com.joelf.bot_service.domain.entities.TemplateStatus;
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
public class CreateTemplateDto {

    @NotBlank
    private String name;
    private String content;

    @NotNull
    private TemplateStatus status;
}
