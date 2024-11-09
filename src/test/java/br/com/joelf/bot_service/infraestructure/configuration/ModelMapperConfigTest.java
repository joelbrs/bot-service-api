package br.com.joelf.bot_service.infraestructure.configuration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

class ModelMapperConfigTest {

    private ModelMapperConfig modelMapperConfig;

    @BeforeEach
    void setUp() {
        modelMapperConfig = new ModelMapperConfig();
    }

    @Test
    void shouldReturnCorrectModelMapper() {
         ModelMapper modelMapper = modelMapperConfig.modelMapper();
         Assertions.assertNotNull(modelMapper, "ModelMapper should not be null");
         Assertions.assertEquals(
                 MatchingStrategies.STRICT,
                 modelMapper.getConfiguration().getMatchingStrategy(),
                 "ModelMapper should have a STRICT matching strategy"
         );
    }
}
