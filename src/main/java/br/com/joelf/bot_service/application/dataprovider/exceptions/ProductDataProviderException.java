package br.com.joelf.bot_service.application.dataprovider.exceptions;

import br.com.joelf.bot_service.application.commom.ExceptionPhase;
import lombok.Getter;

@Getter
public class ProductDataProviderException extends RuntimeException {

    private ExceptionPhase phase;

    public ProductDataProviderException(String message, ExceptionPhase phase) {
        super(message);
        this.phase = phase;
    }
}
