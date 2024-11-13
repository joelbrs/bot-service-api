package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.domain.usecase.LogoutUseCase;
import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LogoutUseCaseImpl implements LogoutUseCase {

    private String cookieName;

    @Override
    public Cookie execute(Cookie[] cookies) {
        for (Cookie cookie: cookies) {
            if (cookie.getName().equals(cookieName)) {
                cookie.setValue(null);
                cookie.setMaxAge(0);

                return cookie;
            }
        }
        return null;
    }
}
