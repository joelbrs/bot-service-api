package br.com.joelf.bot_service.application.commom;

import org.springframework.http.HttpStatus;

public enum ExceptionPhase {
    ENTITY_NOT_FOUND {
        @Override
        public HttpStatus getHttpStatus() {
            return HttpStatus.NOT_FOUND;
        }
    },
    DATA_INTEGRITY {
        @Override
        public HttpStatus getHttpStatus() {
            return HttpStatus.BAD_REQUEST;
        }
    };

    public abstract HttpStatus getHttpStatus();
}
