package br.com.joelf.bot_service.infraestructure.entrypoint.controllers;

import br.com.joelf.bot_service.domain.dtos.template.CreateTemplateDto;
import br.com.joelf.bot_service.domain.entities.Template;
import br.com.joelf.bot_service.domain.usecase.CreateTemplateUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/template")
@AllArgsConstructor
public class TemplateController {

    private final CreateTemplateUseCase createTemplateUseCase;

    @PostMapping
    public ResponseEntity<Template> create(@RequestBody @Valid CreateTemplateDto template) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createTemplateUseCase.execute(template));
    }
}
