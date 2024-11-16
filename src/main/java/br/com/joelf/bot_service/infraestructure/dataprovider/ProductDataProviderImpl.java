package br.com.joelf.bot_service.infraestructure.dataprovider;

import br.com.joelf.bot_service.application.commom.ExceptionPhase;
import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.exceptions.ProductDataProviderException;
import br.com.joelf.bot_service.domain.dtos.product.CreateProductDto;
import br.com.joelf.bot_service.domain.dtos.product.UpdateProductDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.ProductStatus;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.PgProductRepository;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.domain.PgProduct;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
public class ProductDataProviderImpl implements ProductDataProvider {

    private final ModelMapper modelMapper;
    private final PgProductRepository pgProductRepository;

    @Override
    public Page<Product> findAllPaged(Pageable pageable, String name, ProductStatus status) {
        Page<PgProduct> products = pgProductRepository.findAllPaged(pageable, name, status);
        return products.map(pgProduct -> modelMapper.map(pgProduct, Product.class));
    }

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
            throw new ProductDataProviderException("Product not found, id: " + id, ExceptionPhase.ENTITY_NOT_FOUND);
        }
    }

    @Override
    public Product findById(UUID id) {
        PgProduct pgProduct = pgProductRepository.findById(id)
                .orElseThrow(() -> new ProductDataProviderException("Product not found, id: " + id, ExceptionPhase.ENTITY_NOT_FOUND));
        return modelMapper.map(pgProduct, Product.class);
    }

    @Override
    public List<Product> findByName(String name) {
        String nameWithoutSpecialCharacters = name.replaceAll("[^a-zA-Z0-9\\s]", "");

        List<String> names = Arrays.stream(nameWithoutSpecialCharacters.split("\\s+"))
                .map(String::toLowerCase)
                .distinct()
                .toList();

        List<PgProduct> products = pgProductRepository.findByName(names);
        return products.stream()
                .map(pgProduct -> modelMapper.map(pgProduct, Product.class))
                .toList();
    }

    @Override
    public void delete(UUID id) throws ProductDataProviderException {
        if (!pgProductRepository.existsById(id)) {
            throw new ProductDataProviderException("Product not found, id: " + id, ExceptionPhase.ENTITY_NOT_FOUND);
        }

        try {
            pgProductRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new ProductDataProviderException("Product cannot be deleted, id: " + id, ExceptionPhase.DATA_INTEGRITY);
        }
    }
}
