package br.com.joelf.bot_service.infraestructure.configuration;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.SubProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import br.com.joelf.bot_service.infraestructure.dataprovider.ProductDataProviderImpl;
import br.com.joelf.bot_service.infraestructure.dataprovider.SubProductDataProviderImpl;
import br.com.joelf.bot_service.infraestructure.dataprovider.TemplateDataProviderImpl;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.PgProductRepository;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.PgSubProductRepository;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.PgTemplateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class DataProviderConfigTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private PgProductRepository pgProductRepository;

    @Mock
    private PgTemplateRepository pgTemplateRepository;

    @Mock
    private PgSubProductRepository pgSubProductRepository;

    private DataProviderConfig dataProviderConfig;

    @BeforeEach
    void setUp() {
        dataProviderConfig = new DataProviderConfig();
    }

    @Test
    void shouldReturnCorrectProductDataProvider() {
        ProductDataProvider dataprovider =
                dataProviderConfig.productDataProvider(modelMapper, pgProductRepository);

        Assertions.assertNotNull(dataprovider, "ProductDataProvider should not be null");
        Assertions.assertInstanceOf(
                ProductDataProviderImpl.class,
                dataprovider,
                "ProductDataProvider should be an instance of ProductDataProviderImpl"
        );
    }

    @Test
    void shouldReturnCorrectTemplateDataProvider() {
        TemplateDataProvider dataprovider =
                dataProviderConfig.templateDataProvider(modelMapper, pgTemplateRepository);

        Assertions.assertNotNull(dataprovider, "TemplateDataProvider should not be null");
        Assertions.assertInstanceOf(
                TemplateDataProviderImpl.class,
                dataprovider,
                "TemplateDataProvider should be an instance of TemplateDataProviderImpl"
        );
    }

    @Test
    void shouldReturnCorrectSubProductDataProvider() {
        SubProductDataProvider dataprovider =
                dataProviderConfig.subProductDataProvider(modelMapper, pgSubProductRepository);

        Assertions.assertNotNull(dataprovider, "SubProductDataProvider should not be null");
        Assertions.assertInstanceOf(
                SubProductDataProviderImpl.class,
                dataprovider,
                "SubProductDataProvider should be an instance of SubProductDataProviderImpl"
        );
    }
}
