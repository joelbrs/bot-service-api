package br.com.joelf.bot_service.domain.usecase;

import br.com.joelf.bot_service.domain.dtos.user.SignInUserDto;
import jakarta.servlet.http.Cookie;

public interface SignInUserUseCase {
    Cookie execute(SignInUserDto user);
}
