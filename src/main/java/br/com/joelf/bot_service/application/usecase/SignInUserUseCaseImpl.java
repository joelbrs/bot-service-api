package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.UserDataProvider;
import br.com.joelf.bot_service.domain.dtos.user.SignInUserDto;
import br.com.joelf.bot_service.domain.dtos.user.UserTokenDto;
import br.com.joelf.bot_service.domain.entities.User;
import br.com.joelf.bot_service.domain.usecase.SignInUserUseCase;
import br.com.joelf.bot_service.infraestructure.authentication.JwtService;
import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@AllArgsConstructor
public class SignInUserUseCaseImpl implements SignInUserUseCase {

    private String cookieName;
    private final AuthenticationManager authenticationManager;
    private final UserDataProvider userDataProvider;
    private final JwtService jwtService;

    @Override
    public Cookie execute(SignInUserDto user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getCpfCnpj(), user.getPassword())
        );
        User userFound = userDataProvider.findByCpfCnpj(user.getCpfCnpj());
        UserTokenDto token = UserTokenDto.builder()
                .token(jwtService.generateToken(userFound))
                .expirationTime(jwtService.getExpirationTime())
                .build();

        Cookie cookie = new Cookie(cookieName, token.getToken());
        cookie.setHttpOnly(Boolean.TRUE);
        cookie.setPath("/");
        cookie.setMaxAge(token.getExpirationTime().intValue());
        return cookie;
    }
}
