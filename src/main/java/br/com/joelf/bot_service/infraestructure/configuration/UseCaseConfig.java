package br.com.joelf.bot_service.infraestructure.configuration;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.SubProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import br.com.joelf.bot_service.application.dataprovider.UserDataProvider;
import br.com.joelf.bot_service.application.usecase.*;
import br.com.joelf.bot_service.domain.usecase.*;
import br.com.joelf.bot_service.infraestructure.authentication.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UseCaseConfig {

    private final String cookieName;

    public UseCaseConfig(
            @Value("${security.cookie.name}") String cookieName
    ) {
        this.cookieName = cookieName;
    }

    @Bean
    public CreateProductUseCase createProductUseCase(
            ProductDataProvider productDataProvider,
            SubProductDataProvider subProductDataProvider
    ) {
        return new CreateProductUseCaseImpl(productDataProvider, subProductDataProvider);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase(
            ProductDataProvider productDataProvider,
            SubProductDataProvider subProductDataProvider
    ) {
        return new UpdateProductUseCaseImpl(productDataProvider, subProductDataProvider);
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase(
            ProductDataProvider productDataProvider,
            SubProductDataProvider subProductDataProvider
    ) {
        return new DeleteProductUseCaseImpl(productDataProvider, subProductDataProvider);
    }

    @Bean
    public FindAllProductUseCase findAllProductUseCase(
            ProductDataProvider productDataProvider
    ) {
        return new FindAllProductUseCaseImpl(productDataProvider);
    }

    @Bean
    public CreateTemplateUseCase createTemplateUseCase(
            TemplateDataProvider templateDataProvider
    ) {
        return new CreateTemplateUseCaseImpl(templateDataProvider);
    }

    @Bean
    public UpdateTemplateUseCase updateTemplateUseCase(
            TemplateDataProvider templateDataProvider
    ) {
        return new UpdateTemplateUseCaseImpl(templateDataProvider);
    }

    @Bean
    public FindAllTemplateUseCase findAllTemplateUseCase(
            TemplateDataProvider templateDataProvider
    ) {
        return new FindAllTemplateUseCaseImpl(templateDataProvider);
    }

    @Bean
    public DeleteTemplateUseCase deleteTemplateUseCase(
            TemplateDataProvider templateDataProvider
    ) {
        return new DeleteTemplateUseCaseImpl(templateDataProvider);
    }

    @Bean
    public FindProductByIdUseCase findProductByIdUseCase(
            ModelMapper modelMapper,
            ProductDataProvider productDataProvider,
            SubProductDataProvider subProductDataProvider
    ) {
        return new FindProductByIdUseCaseImpl(
                modelMapper,
                productDataProvider,
                subProductDataProvider
        );
    }

    @Bean
    public FindTemplateByIdUseCase findTemplateByIdUseCase(
            TemplateDataProvider templateDataProvider
    ) {
        return new FindTemplateByIdUseCaseImpl(templateDataProvider);
    }

    @Bean
    public SignUpUserUseCase signUpUserUseCase(
            PasswordEncoder passwordEncoder,
            ModelMapper modelMapper,
            UserDataProvider userDataProvider
    ) {
        return new SignUpUserUseCaseImpl(modelMapper, passwordEncoder, userDataProvider);
    }

    @Bean
    public SignInUserUseCase signInUserUseCase(
            AuthenticationManager authenticationManager,
            UserDataProvider userDataProvider,
            @Qualifier("JwtServiceImpl") JwtService jwtService
    ) {
        return new SignInUserUseCaseImpl(cookieName, authenticationManager, userDataProvider, jwtService);
    }

    @Bean
    public FindLoggedUserUseCase findLoggedUserUseCase(
            ModelMapper modelMapper
    ) {
        return new FindLoggedUserUseCaseImpl(modelMapper);
    }

    @Bean
    public LogoutUseCase logoutUseCase() {
        return new LogoutUseCaseImpl(cookieName);
    }

    @Bean
    public MountMessageUseCase mountMessageUseCase(
            ProductDataProvider productDataProvider,
            TemplateDataProvider templateDataProvider
    ) {
        return new MountMessageUseCaseImpl(
                templateDataProvider,
                productDataProvider
        );
    }
}
