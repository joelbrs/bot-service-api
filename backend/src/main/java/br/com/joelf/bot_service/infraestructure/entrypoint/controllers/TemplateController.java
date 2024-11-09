package br.com.joelf.bot_service.infraestructure.entrypoint.controllers;

import br.com.joelf.bot_service.domain.dtos.template.CreateTemplateDto;
import br.com.joelf.bot_service.domain.dtos.template.UpdateTemplateDto;
import br.com.joelf.bot_service.domain.entities.Template;
import br.com.joelf.bot_service.domain.usecase.CreateTemplateUseCase;
import br.com.joelf.bot_service.domain.usecase.FindAllTemplateUseCase;
import br.com.joelf.bot_service.domain.usecase.UpdateTemplateUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/template")
@AllArgsConstructor
public class TemplateController {

    private final CreateTemplateUseCase createTemplateUseCase;
    private final UpdateTemplateUseCase updateTemplateUseCase;
    private final FindAllTemplateUseCase findAllTemplateUseCase;

    @GetMapping
    public ResponseEntity<Page<Template>> findAll(
            Pageable pageable,
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity.ok(findAllTemplateUseCase.execute(pageable, name));
    }

    @PostMapping
    public ResponseEntity<Template> create(@RequestBody @Valid CreateTemplateDto template) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createTemplateUseCase.execute(template));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Template> update(@PathVariable UUID id, @RequestBody @Valid UpdateTemplateDto template) {
        return ResponseEntity.ok(updateTemplateUseCase.execute(id, template));
    }
}
