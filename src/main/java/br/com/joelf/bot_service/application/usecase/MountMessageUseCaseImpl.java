package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.ProductDataProvider;
import br.com.joelf.bot_service.application.dataprovider.TemplateDataProvider;
import br.com.joelf.bot_service.domain.entities.Template;
import br.com.joelf.bot_service.domain.usecase.MountMessageUseCase;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MountMessageUseCaseImpl implements MountMessageUseCase {

    private final TemplateDataProvider templateDataProvider;
    private final ProductDataProvider productDataProvider;

    @Override
    public String execute() {
        Template template = templateDataProvider.findActiveTemplate();
        return mountXmlBodyResponse(template.getContent());
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
