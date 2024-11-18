package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.SubProductDataProvider;
import br.com.joelf.bot_service.domain.dtos.product.ProductWithSubProductsDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.usecase.FindProductsByNameUseCase;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
public class FindProductsByNameUseCaseImpl implements FindProductsByNameUseCase {

    private final ModelMapper modelMapper;
    private final ProductDataProvider productDataProvider;
    private final SubProductDataProvider subProductDataProvider;

    @Transactional(readOnly = true)
    @Override
    public List<ProductWithSubProductsDto> execute(String name) {
        List<Product> products = productDataProvider.findByName(name);
        List<ProductWithSubProductsDto> productsWithSubProducts =
                products.stream().map(product -> modelMapper.map(product, ProductWithSubProductsDto.class)).toList();

        productsWithSubProducts.forEach(product -> {
            product.setSubProducts(subProductDataProvider.findAllByProductId(product.getId()));
        });

        return productsWithSubProducts;
    }
}
