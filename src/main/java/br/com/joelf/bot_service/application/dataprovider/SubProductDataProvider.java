package br.com.joelf.bot_service.application.dataprovider;

import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.SubProduct;

import java.util.List;
import java.util.UUID;

public interface SubProductDataProvider {
    SubProduct<?> create(SubProduct<Product> subProduct);
    List<SubProduct<Product>> findAllByProductId(UUID id);
    void deleteAllByProductId(UUID id);
    void deleteAll();
}
