package br.com.joelf.bot_service.application.dataprovider;

import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.SubProduct;

import java.util.UUID;

public interface SubProductDataProvider {
    SubProduct<Product> create(SubProduct<Product> subProduct);
    void deleteAllByProductId(UUID id);
}
