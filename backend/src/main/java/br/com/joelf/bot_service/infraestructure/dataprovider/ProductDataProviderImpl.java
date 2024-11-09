package br.com.joelf.bot_service.infraestructure.dataprovider;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.exceptions.ProductDataProviderException;
import br.com.joelf.bot_service.domain.dtos.product.CreateProductDto;
import br.com.joelf.bot_service.domain.dtos.product.UpdateProductDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.PgProductRepository;

import br.com.joelf.bot_service.infraestructure.repositories.postgres.domain.PgProduct;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@AllArgsConstructor
public class ProductDataProviderImpl implements ProductDataProvider {

    private final ModelMapper modelMapper;
    private final PgProductRepository pgProductRepository;

    @Override
    public Product create(CreateProductDto product) {
        PgProduct pgProduct = modelMapper.map(product, PgProduct.class);
        return modelMapper.map(pgProductRepository.save(pgProduct), Product.class);
    }

    @Override
    public Product update(UUID id, UpdateProductDto product) {
        try {
            PgProduct pgProduct = pgProductRepository.getReferenceById(id);
            BeanUtils.copyProperties(product, pgProduct, "id");

            return modelMapper.map(pgProductRepository.save(pgProduct), Product.class);
        } catch (EntityNotFoundException e) {
            throw new ProductDataProviderException("Product not found, id: " + id);
        }
    }
}
