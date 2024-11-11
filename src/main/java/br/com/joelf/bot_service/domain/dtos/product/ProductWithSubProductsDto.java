package br.com.joelf.bot_service.domain.dtos.product;

import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.SubProduct;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductWithSubProductsDto extends Product {
    private List<SubProduct<Product>> subProducts;
}
