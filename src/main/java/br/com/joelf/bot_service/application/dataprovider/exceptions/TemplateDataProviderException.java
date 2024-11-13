package br.com.joelf.bot_service.application.dataprovider.exceptions;

import br.com.joelf.bot_service.application.commom.ExceptionPhase;
import lombok.Getter;

@Getter
public class TemplateDataProviderException extends RuntimeException {

    private ExceptionPhase phase;

    public TemplateDataProviderException(String message, ExceptionPhase phase) {
        super(message);
        this.phase = phase;
    }
}
