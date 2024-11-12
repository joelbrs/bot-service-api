package br.com.joelf.bot_service.infraestructure.entrypoint.controllers;

import br.com.joelf.bot_service.domain.dtos.user.SignInUserDto;
import br.com.joelf.bot_service.domain.dtos.user.SignUpUserDto;
import br.com.joelf.bot_service.domain.dtos.user.UserDtoOut;
import br.com.joelf.bot_service.domain.usecase.LogoutUseCase;
import br.com.joelf.bot_service.domain.usecase.SignInUserUseCase;
import br.com.joelf.bot_service.domain.usecase.SignUpUserUseCase;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final SignInUserUseCase signInUserUseCase;
    private final SignUpUserUseCase signUpUserUseCase;
    private final LogoutUseCase logoutUseCase;

    @PostMapping("/sign-in")
    public ResponseEntity<Void> signIn(@RequestBody @Valid SignInUserDto user, HttpServletResponse response) {
        response.addCookie(signInUserUseCase.execute(user));
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = logoutUseCase.execute(request.getCookies());
        response.addCookie(cookie);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserDtoOut> signUp(@RequestBody @Valid SignUpUserDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpUserUseCase.execute(user));
    }
}
