package br.com.joelf.bot_service.infraestructure.entrypoint.webhook;

import br.com.joelf.bot_service.domain.usecase.MountMessageUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/webhook")
public class TwilioWebhook {

    private final MountMessageUseCase mountMessageUseCase;

    @PostMapping("/whatsapp")
    public ResponseEntity<String> mountMessage() {
        return ResponseEntity.ok(mountMessageUseCase.execute());
    }
}
