package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.SubProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.exceptions.ProductDataProviderException;
import br.com.joelf.bot_service.application.dataprovider.exceptions.SubProductDataProviderException;
import br.com.joelf.bot_service.domain.dtos.product.UpdateProductDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.usecase.UpdateProductUseCase;
import br.com.joelf.bot_service.domain.usecase.exceptions.UpdateProductUseCaseException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class UpdateProductUseCaseImpl implements UpdateProductUseCase {

    private final ProductDataProvider productDataProvider;
    private final SubProductDataProvider subProductDataProvider;

    @Transactional
    @Override
    public Product execute(UUID id, UpdateProductDto product) {
        try {
            Product result = productDataProvider.update(id, product);

            subProductDataProvider.deleteAllByProductId(id);
            product.getProducts().forEach(subProduct -> {
                subProduct.setProduct(result);
                subProductDataProvider.create(subProduct);
            });

            return result;
        } catch (ProductDataProviderException e) {
            throw new UpdateProductUseCaseException(e.getMessage(), e.getPhase().getHttpStatus());
        } catch (SubProductDataProviderException e) {
            throw new UpdateProductUseCaseException(e.getMessage(), e.getPhase().getHttpStatus());
        }
    }
}
