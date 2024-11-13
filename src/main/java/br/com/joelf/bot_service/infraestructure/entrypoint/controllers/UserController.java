package br.com.joelf.bot_service.infraestructure.entrypoint.controllers;

import br.com.joelf.bot_service.domain.dtos.user.UserDtoOut;
import br.com.joelf.bot_service.domain.usecase.FindLoggedUserUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final FindLoggedUserUseCase findLoggedUserUseCase;

    @GetMapping("/me")
    public ResponseEntity<UserDtoOut> findLoggedUser() {
        return ResponseEntity.ok(findLoggedUserUseCase.execute());
    }
}
