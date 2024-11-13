package br.com.joelf.bot_service.infraestructure.entrypoint.controllers.exceptions;

import br.com.joelf.bot_service.domain.usecase.exceptions.*;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.time.Instant;

@ControllerAdvice
public class ExceptionHandlerImpl {

    @ExceptionHandler(SignUpUserUseCaseException.class)
    public ResponseEntity<ExceptionResponse> signUpUserUseCaseException(
            HttpServletRequest request, SignUpUserUseCaseException e
    ) {
        return mapResponse(request, e);
    }

    @ExceptionHandler(UpdateProductUseCaseException.class)
    public ResponseEntity<ExceptionResponse> updateProductUseCaseException(
            HttpServletRequest request, UpdateProductUseCaseException e
    ) {
        return mapResponse(request, e);
    }

    @ExceptionHandler(UpdateTemplateUseCaseException.class)
    public ResponseEntity<ExceptionResponse> updateTemplateUseCaseException(
            HttpServletRequest request, UpdateTemplateUseCaseException e
    ) {
        return mapResponse(request, e);
    }

    @ExceptionHandler(DeleteProductUseCaseException.class)
    public ResponseEntity<ExceptionResponse> deleteProductUseCaseException(
            HttpServletRequest request, DeleteProductUseCaseException e
    ) {
        return mapResponse(request, e);
    }

    @ExceptionHandler(DeleteTemplateUseCaseException.class)
    public ResponseEntity<ExceptionResponse> deleteTemplateUseCaseException(
            HttpServletRequest request, DeleteTemplateUseCaseException e
    ) {
        return mapResponse(request, e);
    }

    @ExceptionHandler(FindProductByIdUseCaseException.class)
    public ResponseEntity<ExceptionResponse> findProductByIdUseCaseException(
            HttpServletRequest request, FindProductByIdUseCaseException e
    ) {
        return mapResponse(request, e);
    }

    @ExceptionHandler(FindTemplateByIdUseCaseException.class)
    public ResponseEntity<ExceptionResponse> findTemplateByIdUseCaseException(
            HttpServletRequest request, FindTemplateByIdUseCaseException e
    ) {
        return mapResponse(request, e);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> badCredentialsException(
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String msg = "Credenciais inválidas! Usuário e/ou senha incorretos.";

        return ResponseEntity.status(status).body(
                ExceptionResponse.builder()
                        .timestamp(Instant.now())
                        .status(status.value())
                        .error(msg)
                        .path(request.getRequestURI())
                        .build()
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ExceptionResponse> accessDeniedException(
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.FORBIDDEN;
        String msg = "Acesso negado! Você não tem permissão para acessar este recurso.";

        return ResponseEntity.status(status).body(
                ExceptionResponse.builder()
                        .timestamp(Instant.now())
                        .status(status.value())
                        .error(msg)
                        .path(request.getRequestURI())
                        .build()
        );
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ExceptionResponse> expiredJwtException(
            HttpServletRequest request
    ) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String msg = "Token expirado! Faça login novamente para obter um novo token.";

        return ResponseEntity.status(status).body(
                ExceptionResponse.builder()
                        .timestamp(Instant.now())
                        .status(status.value())
                        .error(msg)
                        .path(request.getRequestURI())
                        .build()
        );
    }

    private ResponseEntity<ExceptionResponse> mapResponse(
            HttpServletRequest request, UseCaseException e
    ) {
        return ResponseEntity.status(e.getHttpStatus()).body(
                ExceptionResponse.builder()
                        .timestamp(Instant.now())
                        .status(e.getHttpStatus().value())
                        .error(e.getMessage())
                        .path(request.getRequestURI())
                        .build()
        );
    }
}
