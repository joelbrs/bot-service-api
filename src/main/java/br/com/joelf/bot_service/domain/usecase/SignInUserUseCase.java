package br.com.joelf.bot_service.domain.usecase;

import br.com.joelf.bot_service.domain.dtos.user.SignInUserDto;
import br.com.joelf.bot_service.domain.dtos.user.UserTokenDto;

public interface SignInUserUseCase {
    UserTokenDto execute(SignInUserDto user);
}
