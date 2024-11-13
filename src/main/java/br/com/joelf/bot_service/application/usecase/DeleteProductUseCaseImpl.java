package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.SubProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.exceptions.ProductDataProviderException;
import br.com.joelf.bot_service.application.dataprovider.exceptions.SubProductDataProviderException;
import br.com.joelf.bot_service.domain.usecase.DeleteProductUseCase;
import br.com.joelf.bot_service.domain.usecase.exceptions.DeleteProductUseCaseException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
public class DeleteProductUseCaseImpl implements DeleteProductUseCase {

    private final ProductDataProvider productDataProvider;
    private final SubProductDataProvider subProductDataProvider;

    @Transactional
    @Override
    public void execute(UUID id) {
        try {
            subProductDataProvider.deleteAll();
            productDataProvider.delete(id);
        } catch (ProductDataProviderException e) {
            throw new DeleteProductUseCaseException(e.getMessage(), e.getPhase().getHttpStatus());
        } catch (SubProductDataProviderException e) {
            throw new DeleteProductUseCaseException(e.getMessage(), e.getPhase().getHttpStatus());
        }
    }
}
