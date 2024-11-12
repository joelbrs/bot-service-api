package br.com.joelf.bot_service.infraestructure.entrypoint.controllers;

import br.com.joelf.bot_service.domain.dtos.user.SignInUserDto;
import br.com.joelf.bot_service.domain.dtos.user.SignUpUserDto;
import br.com.joelf.bot_service.domain.dtos.user.UserDtoOut;
import br.com.joelf.bot_service.domain.dtos.user.UserTokenDto;
import br.com.joelf.bot_service.domain.entities.User;
import br.com.joelf.bot_service.domain.usecase.SignInUserUseCase;
import br.com.joelf.bot_service.domain.usecase.SignUpUserUseCase;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Value("${security.cookie.name}")
    private String cookieName;

    private final SignInUserUseCase signInUserUseCase;
    private final SignUpUserUseCase signUpUserUseCase;

    public AuthenticationController(SignInUserUseCase signInUserUseCase, SignUpUserUseCase signUpUserUseCase) {
        this.signInUserUseCase = signInUserUseCase;
        this.signUpUserUseCase = signUpUserUseCase;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Void> signIn(@RequestBody @Valid SignInUserDto user, HttpServletResponse response) {
        UserTokenDto token = signInUserUseCase.execute(user);

        Cookie cookie = new Cookie(cookieName, token.getToken());
        cookie.setHttpOnly(Boolean.TRUE);
        cookie.setPath("/");
        cookie.setMaxAge(token.getExpirationTime().intValue());

        response.addCookie(cookie);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/sign-up")
    public ResponseEntity<UserDtoOut> signUp(@RequestBody @Valid SignUpUserDto user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(signUpUserUseCase.execute(user));
    }
}
