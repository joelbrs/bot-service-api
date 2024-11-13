package br.com.joelf.bot_service.domain.usecase;

import br.com.joelf.bot_service.domain.dtos.user.SignUpUserDto;
import br.com.joelf.bot_service.domain.dtos.user.UserDtoOut;

public interface SignUpUserUseCase {
    UserDtoOut execute(SignUpUserDto user);
}
