package br.com.joelf.bot_service.domain.entities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Organization {

    public static String NAME;
    public static String CNPJ;

    public Organization(
        @Value("${security.organization.cnpj}") String CNPJ,
        @Value("${security.organization.name}") String NAME
    ) {
        Organization.CNPJ = CNPJ;
        Organization.NAME = NAME;
    }
}