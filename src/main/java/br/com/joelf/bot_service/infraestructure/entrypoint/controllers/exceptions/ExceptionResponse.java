package br.com.joelf.bot_service.infraestructure.entrypoint.controllers.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder
public class ExceptionResponse {
    private Instant timestamp;
    private Integer status;
    private String error;
    private String path;
}
