package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.UserDataProvider;
import br.com.joelf.bot_service.domain.dtos.user.SignInUserDto;
import br.com.joelf.bot_service.domain.dtos.user.UserTokenDto;
import br.com.joelf.bot_service.domain.entities.User;
import br.com.joelf.bot_service.domain.usecase.SignInUserUseCase;
import br.com.joelf.bot_service.infraestructure.authentication.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@AllArgsConstructor
public class SignInUserUseCaseImpl implements SignInUserUseCase {

    private final AuthenticationManager authenticationManager;
    private final UserDataProvider userDataProvider;
    private final JwtService jwtService;

    @Override
    public UserTokenDto execute(SignInUserDto user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getCpfCnpj(), user.getPassword())
        );
        User userFound = userDataProvider.findByCpfCnpj(user.getCpfCnpj());
        return UserTokenDto.builder()
                .token(jwtService.generateToken(userFound))
                .expirationTime(jwtService.getExpirationTime())
                .build();
    }
}
