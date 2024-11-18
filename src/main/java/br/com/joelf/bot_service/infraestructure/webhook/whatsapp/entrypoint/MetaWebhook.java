package br.com.joelf.bot_service.infraestructure.webhook.whatsapp.entrypoint;

import br.com.joelf.bot_service.domain.usecase.SendMessageUseCase;
import br.com.joelf.bot_service.infraestructure.webhook.whatsapp.model.dtos.Entrypoint;
import br.com.joelf.bot_service.infraestructure.webhook.whatsapp.model.dtos.Message;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        List<Message> messages = body.getEntry().getFirst().getChanges().getFirst().getValue().getMessages();

        if (messages != null) {
            Message message = messages.getFirst();
            sendMessageUseCase.execute(message.getText().getBody(), message.getFrom());
        }
        return ResponseEntity.ok().build();
    }
}
