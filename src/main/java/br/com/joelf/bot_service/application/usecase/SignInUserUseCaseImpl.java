package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.UserDataProvider;
import br.com.joelf.bot_service.domain.dtos.user.SignInUserDto;
import br.com.joelf.bot_service.domain.dtos.user.UserTokenDto;
import br.com.joelf.bot_service.domain.entities.User;
import br.com.joelf.bot_service.domain.usecase.SignInUserUseCase;
import br.com.joelf.bot_service.infraestructure.authentication.JwtService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
public class SignInUserUseCaseImpl implements SignInUserUseCase {

    private final UserDataProvider userDataProvider;
    private final JwtService jwtService;

    @Override
    public UserTokenDto execute(SignInUserDto user) {
        User userFound = userDataProvider.findByCpfCnpj(user.getCpfCnpj());
        return UserTokenDto.builder()
                .token(jwtService.generateToken(userFound))
                .expirationTime(jwtService.getExpirationTime())
                .build();
    }
}
