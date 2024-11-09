package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.ProductStatus;
import br.com.joelf.bot_service.domain.usecase.FindAllProductUseCase;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
public class FindAllProductUseCaseImpl implements FindAllProductUseCase {

    private final ProductDataProvider productDataProvider;

    @Override
    public Page<Product> execute(Pageable pageable, String name, ProductStatus status) {
        return productDataProvider.findAllPaged(pageable, name, status);
    }
}
