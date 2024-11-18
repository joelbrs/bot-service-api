package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import br.com.joelf.bot_service.domain.dtos.product.ProductWithSubProductsDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.SubProduct;
import br.com.joelf.bot_service.domain.entities.Template;
import br.com.joelf.bot_service.domain.usecase.FindProductsByNameUseCase;
import br.com.joelf.bot_service.domain.usecase.MountMessageUseCase;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@AllArgsConstructor
public class MountMessageUseCaseImpl implements MountMessageUseCase {

    private final TemplateDataProvider templateDataProvider;
    private final FindProductsByNameUseCase findProductsByNameUseCase;

    @Override
    public String execute(String productName) {
        Template template = templateDataProvider.findActiveTemplate();
        List<ProductWithSubProductsDto> products = findProductsByNameUseCase.execute(productName);

        return productsToString(products) + template.getContent();
    }

    private String productsToString(List<ProductWithSubProductsDto> dto) {
        StringBuilder sb = new StringBuilder();

        for (ProductWithSubProductsDto product : dto) {
            sb.append("\n*")
                    .append(product.getName())
                    .append("*\uD83D\uDC48 \n");

            for (SubProduct<Product> subProduct : product.getSubProducts()) {
                sb.append("- ")
                        .append(subProduct.getName())
                        .append(" - ")
                        .append(toBRL(subProduct.getPrice()))
                        .append("\n");
            }
        }

        if (!dto.isEmpty()) {
            sb.append("__________________________________________\n");
        }
        return sb.toString();
    }

    private String toBRL(BigDecimal valor) {
        NumberFormat moneyFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return moneyFormat.format(valor);
    }
}
