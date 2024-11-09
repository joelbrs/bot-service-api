package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.SubProductDataProvider;
import br.com.joelf.bot_service.domain.dtos.product.CreateProductDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.usecase.CreateProductUseCase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreateProductUseCaseImpl implements CreateProductUseCase {

    private final ProductDataProvider productDataProvider;
    private final SubProductDataProvider subProductDataProvider;

    @Transactional
    @Override
    public Product execute(CreateProductDto product) {
        Product result = productDataProvider.create(product);
        product.getSubProducts().forEach(subProduct -> {
            subProduct.setProduct(result);
            subProductDataProvider.create(subProduct);
        });

        return result;
    }
}
