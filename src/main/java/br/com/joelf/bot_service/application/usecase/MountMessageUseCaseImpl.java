package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import br.com.joelf.bot_service.domain.dtos.product.ProductWithSubProductsDto;
import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.SubProduct;
import br.com.joelf.bot_service.domain.entities.Template;
import br.com.joelf.bot_service.domain.usecase.FindProductsByNameUseCase;
import br.com.joelf.bot_service.domain.usecase.MountMessageUseCase;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class MountMessageUseCaseImpl implements MountMessageUseCase {

    private final TemplateDataProvider templateDataProvider;
    private final FindProductsByNameUseCase findProductsByNameUseCase;

    @Override
    public String execute(String productName) {
        Template template = templateDataProvider.findActiveTemplate();
        List<ProductWithSubProductsDto> products = findProductsByNameUseCase.execute(productName);

        return mountXmlBodyResponse(template.getContent() + productsToString(products));
    }

    private String productsToString(List<ProductWithSubProductsDto> dto) {
        StringBuilder sb = new StringBuilder();
        for (ProductWithSubProductsDto product : dto) {
            sb.append("\n*")
                    .append(product.getName())
                    .append("*\n");

            for (SubProduct<Product> subProduct : product.getSubProducts()) {
                sb.append("    - ");
                sb.append(subProduct.getName())
                        .append(" - ")
                        .append(subProduct.getPrice())
                        .append("\n");
            }
        }
        return sb.toString();
    }

    private String mountXmlBodyResponse(String content) {
        Body body = new Body
                .Builder(content)
                .build();
        Message sms = new Message
                .Builder()
                .body(body)
                .build();
        MessagingResponse twiml = new MessagingResponse
                .Builder()
                .message(sms)
                .build();

        return twiml.toXml();
    }
}
