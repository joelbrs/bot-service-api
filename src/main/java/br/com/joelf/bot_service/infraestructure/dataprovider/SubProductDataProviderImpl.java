package br.com.joelf.bot_service.infraestructure.dataprovider;

import br.com.joelf.bot_service.application.dataprovider.SubProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.exceptions.SubProductDataProviderException;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.SubProduct;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.PgSubProductRepository;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.domain.PgProduct;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.domain.PgSubProduct;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
public class SubProductDataProviderImpl implements SubProductDataProvider {

    private final ModelMapper modelMapper;
    private final PgSubProductRepository pgSubProductRepository;

    @Override
    public SubProduct<Product> create(SubProduct<Product> subProduct) {
        PgProduct pgProduct = modelMapper.map(subProduct.getProduct(), PgProduct.class);
        PgSubProduct pgSubProduct = modelMapper.map(subProduct, PgSubProduct.class);
        pgSubProduct.setProduct(pgProduct);

        return modelMapper.map(pgSubProductRepository.save(pgSubProduct), SubProduct.class);
    }

    @Override
    public List findAllByProductId(UUID id) {
        List<PgSubProduct> subProducts = pgSubProductRepository.findAllByProductId(id);
        return subProducts.stream()
                .map(pgSubProduct -> modelMapper.map(pgSubProduct, SubProduct.class))
                .toList();
    }

    @Override
    public void deleteAllByProductId(UUID productId) {
        try {
            pgSubProductRepository.deleteAllByProductId(productId);
        } catch (DataIntegrityViolationException e) {
            throw new SubProductDataProviderException("SubProducts cannot be deleted");
        }
    }

    @Override
    public void deleteAll() {
        try {
            pgSubProductRepository.deleteAll();
        } catch (DataIntegrityViolationException e) {
            throw new SubProductDataProviderException("SubProducts cannot be deleted");
        }
    }
}
