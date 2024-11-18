package br.com.joelf.bot_service.infraestructure.webhook.whatsapp.entrypoint;

import br.com.joelf.bot_service.domain.usecase.SendMessageUseCase;
import br.com.joelf.bot_service.infraestructure.webhook.whatsapp.model.dtos.Entrypoint;
import br.com.joelf.bot_service.infraestructure.webhook.whatsapp.model.dtos.Message;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/webhook")
public class MetaWebhook {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetaWebhook.class);
    private final SendMessageUseCase sendMessageUseCase;

    @GetMapping("/whatsapp")
    public ResponseEntity<String> mountMessage(@RequestParam("hub.challenge") String challenge) {
        return ResponseEntity.ok(challenge);
    }

    @PostMapping("/whatsapp")
    public ResponseEntity<Void> mountMessage(@RequestBody Entrypoint body) {
        LOGGER.info("Received message: {}", body);
        Message message = body.getEntry().getFirst().getChanges().getFirst().getMessages().getFirst();
        sendMessageUseCase.execute(message.getText().getBody(), message.getFrom());
        return ResponseEntity.ok().build();
    }
}
