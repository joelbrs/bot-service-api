package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.SubProductDataProvider;
import br.com.joelf.bot_service.domain.dtos.product.ProductWithSubProductsDto;
import br.com.joelf.bot_service.domain.usecase.FindProductByIdUseCase;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.UUID;

@AllArgsConstructor
public class FindProductByIdUseCaseImpl implements FindProductByIdUseCase {

    private final ModelMapper modelMapper;
    private final ProductDataProvider productDataProvider;
    private final SubProductDataProvider subProductDataProvider;

    @Override
    public ProductWithSubProductsDto execute(UUID id) {
        ProductWithSubProductsDto product =
                modelMapper.map(productDataProvider.findById(id), ProductWithSubProductsDto.class);
        product.setSubProducts(subProductDataProvider.findAllByProductId(id));

        return product;
    }
}