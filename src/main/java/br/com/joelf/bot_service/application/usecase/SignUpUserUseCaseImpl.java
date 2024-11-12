package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.UserDataProvider;
import br.com.joelf.bot_service.domain.dtos.user.SignUpUserDto;
import br.com.joelf.bot_service.domain.entities.User;
import br.com.joelf.bot_service.domain.usecase.SignUpUserUseCase;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SignUpUserUseCaseImpl implements SignUpUserUseCase {

    private final UserDataProvider userDataProvider;

    @Override
    public User execute(SignUpUserDto user) {
        return userDataProvider.signUp(user);
    }
}
