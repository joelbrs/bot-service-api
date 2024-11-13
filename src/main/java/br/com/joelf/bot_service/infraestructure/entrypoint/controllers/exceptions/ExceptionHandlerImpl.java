package br.com.joelf.bot_service.infraestructure.entrypoint.controllers.exceptions;

import br.com.joelf.bot_service.domain.usecase.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
