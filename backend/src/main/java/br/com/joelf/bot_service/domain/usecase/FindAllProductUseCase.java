package br.com.joelf.bot_service.domain.usecase;

import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindAllProductUseCase {
    Page<Product> execute(Pageable pageable, String name, ProductStatus status);
}
