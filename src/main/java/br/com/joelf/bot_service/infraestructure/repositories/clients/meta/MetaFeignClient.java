package br.com.joelf.bot_service.infraestructure.repositories.clients.meta;

import br.com.joelf.bot_service.infraestructure.repositories.clients.meta.config.MetaConfig;
import br.com.joelf.bot_service.infraestructure.repositories.clients.meta.model.request.SendMessageRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "meta",
        url = "${meta.url}",
        configuration = MetaConfig.class
)
public interface MetaFeignClient {

    @PostMapping("/{version}/{phoneNumberId}/messages")
    void postSendMessage(
            @RequestBody SendMessageRequest request,
            @PathVariable String version,
            @PathVariable String phoneNumberId
    );
}
