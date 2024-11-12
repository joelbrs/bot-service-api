package br.com.joelf.bot_service.infraestructure.repositories.postgres.domain;

import br.com.joelf.bot_service.domain.entities.Product;
import br.com.joelf.bot_service.domain.entities.ProductStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_product")
@Getter
@Setter
@NoArgsConstructor
public class PgProduct extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;
}
