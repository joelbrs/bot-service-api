package br.com.joelf.bot_service.infraestructure.entrypoint.webhook;

import br.com.joelf.bot_service.domain.usecase.MountMessageUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/webhook")
public class MetaWebhook {

    private final MountMessageUseCase mountMessageUseCase;

    @GetMapping("/whatsapp")
    public ResponseEntity<String> mountMessage(@RequestParam("hub.challenge") String challenge) {
        return ResponseEntity.ok(mountMessageUseCase.execute(challenge));
    }
}
