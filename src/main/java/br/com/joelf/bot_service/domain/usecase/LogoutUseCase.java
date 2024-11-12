package br.com.joelf.bot_service.domain.usecase;

import jakarta.servlet.http.Cookie;

public interface LogoutUseCase {
    Cookie execute(Cookie[] cookie);
}
