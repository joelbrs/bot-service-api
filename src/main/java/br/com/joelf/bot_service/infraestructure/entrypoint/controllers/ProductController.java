package br.com.joelf.bot_service.infraestructure.entrypoint.controllers;

import br.com.joelf.bot_service.domain.dtos.product.CreateProductDto;
import br.com.joelf.bot_service.domain.dtos.product.UpdateProductDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.ProductStatus;
import br.com.joelf.bot_service.domain.usecase.CreateProductUseCase;
import br.com.joelf.bot_service.domain.usecase.DeleteProductUseCase;
import br.com.joelf.bot_service.domain.usecase.FindAllProductUseCase;
import br.com.joelf.bot_service.domain.usecase.UpdateProductUseCase;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {

    private final FindAllProductUseCase findAllProductUseCase;
    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    @GetMapping
    public ResponseEntity<Page<Product>> findAllPaged(
            Pageable pageable,
            @RequestParam(required = false) String name,
            @RequestParam(required = false)ProductStatus status
    ) {
        return ResponseEntity.ok(findAllProductUseCase.execute(pageable, name, status));
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody @Valid CreateProductDto product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(createProductUseCase.execute(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable UUID id, @RequestBody @Valid UpdateProductDto product) {
        return ResponseEntity.ok(updateProductUseCase.execute(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        deleteProductUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
