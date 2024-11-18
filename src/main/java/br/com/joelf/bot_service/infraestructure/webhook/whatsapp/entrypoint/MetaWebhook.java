package br.com.joelf.bot_service.infraestructure.webhook.whatsapp.entrypoint;

import br.com.joelf.bot_service.domain.usecase.SendMessageUseCase;
import br.com.joelf.bot_service.infraestructure.webhook.whatsapp.model.dtos.Entrypoint;
import br.com.joelf.bot_service.infraestructure.webhook.whatsapp.model.dtos.Message;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/webhook")
public class MetaWebhook {

    private final SendMessageUseCase sendMessageUseCase;

    @GetMapping("/whatsapp")
    public ResponseEntity<String> mountMessage(@RequestParam("hub.challenge") String challenge) {
        return ResponseEntity.ok(challenge);
    }

    @PostMapping("/whatsapp")
    public ResponseEntity<Void> mountMessage(@RequestBody Entrypoint body) {
        Message message = body.getEntry().getFirst().getChanges().getFirst().getMessages().getFirst();
        sendMessageUseCase.execute(message.getText().getBody(), message.getFrom());
        return ResponseEntity.noContent().build();
    }
}
